package com.example.sino.view.chatmessage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.enumtype.GeneralStatus;
import com.example.sino.model.carinfo.SuccessCarInfoBean;
import com.example.sino.model.chatgroup.ChatGroup;
import com.example.sino.model.chatgroup.SuccessChatGroupBean;
import com.example.sino.model.chatgroupmember.ChatGroupMember;
import com.example.sino.model.chatgroupmember.SuccessChatGroupMemberBean;
import com.example.sino.model.db.AppUser;
import com.example.sino.model.db.User;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.viewmodel.DatabaseViewModel;
import com.example.sino.viewmodel.MainViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChatGroupFragment extends Fragment {

    private MainViewModel mainViewModel;
    private String inputParam = "";
    private CircularProgressView progress_circular;
    private User user;
    private DatabaseViewModel databaseViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caht_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = SinoApplication.getInstance().getCurrentUser();

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        databaseViewModel = new ViewModelProvider(this).get(DatabaseViewModel.class);
        progress_circular = view.findViewById(R.id.progress_circular);

        inputParam = GsonGenerator.getUserPermissionList(user.getUsername(), user.getBisPassword());
        mainViewModel.getUserChatGroupList(inputParam, progress_circular);
        mainViewModel.getUserChatGroupMemberList(inputParam);

        mainViewModel.getUserChatGroupList().observe(getViewLifecycleOwner(), new Observer<SuccessChatGroupBean>() {
            @Override
            public void onChanged(SuccessChatGroupBean successChatGroupBean) {
                if (successChatGroupBean.success != null) {
                    if (successChatGroupBean.result != null) {
                        if (successChatGroupBean.result.chatGroupList != null) {
                            for (int i = 0; i < successChatGroupBean.result.chatGroupList.size(); i++) {
                                Long serverGroupId = Long.valueOf(successChatGroupBean.result.chatGroupList.get(i).id);
                                ChatGroup tmpChatGroupFS = new ChatGroup();
                                tmpChatGroupFS.setServerGroupId(serverGroupId);

                                ChatGroup chatGroup = databaseViewModel.getChatGroupByServerGroupIdVM(tmpChatGroupFS.getServerGroupId());
                                if (chatGroup == null) {
                                    chatGroup = new ChatGroup();
                                    chatGroup.setServerGroupId(serverGroupId);
                                }

                                chatGroup.setName(successChatGroupBean.result.chatGroupList.get(i).name);
                                chatGroup.setPrivateIs(successChatGroupBean.result.chatGroupList.get(i).getPrivateIs());
                                chatGroup.setMaxMember(successChatGroupBean.result.chatGroupList.get(i).getMaxMember());
                                chatGroup.setNotifyAct(successChatGroupBean.result.chatGroupList.get(i).getNotifyAct());
                                chatGroup.setStatus(successChatGroupBean.result.chatGroupList.get(i).getStatus());

                                if (chatGroup.getId() == null) {
                                    databaseViewModel.insertChatGroupVM(chatGroup);
                                } else {
                                    databaseViewModel.updateChatGroupVM(chatGroup);
                                }

                                ChatGroup chatGroupUserRemoved = databaseViewModel.getChatGroupListByParamVM(serverGroupId);
                                if (chatGroupUserRemoved != null){
                                    chatGroupUserRemoved.setStatusEn(GeneralStatus.Inactive.ordinal());
                                    databaseViewModel.updateChatGroupVM(chatGroupUserRemoved);
                                }
                            }
                        }
                    }
                }
            }
        });

        mainViewModel.getChatGroupMemberList().observe(getViewLifecycleOwner(), new Observer<SuccessChatGroupMemberBean>() {
            @Override
            public void onChanged(SuccessChatGroupMemberBean successChatGroupMemberBean) {
                if (successChatGroupMemberBean.success != null) {
                    if (successChatGroupMemberBean.result != null) {

                        List<Long> serverGroupIdList = new ArrayList<Long>();

                        if (successChatGroupMemberBean.result.chatGroupMemberList != null) {
                            for (int i = 0; i < successChatGroupMemberBean.result.chatGroupMemberList.size(); i++) {
                                Long serverGroupId = (long) successChatGroupMemberBean.result.chatGroupMemberList.get(i).getId();
                                serverGroupIdList.add(serverGroupId);
                                ChatGroup tmpChatGroupFS = new ChatGroup();
                                tmpChatGroupFS.setServerGroupId(serverGroupId);
                                ChatGroup chatGroup = databaseViewModel.getChatGroupByServerGroupIdVM(tmpChatGroupFS.getServerGroupId());
                                if (chatGroup == null) {
                                    chatGroup = new ChatGroup();
                                    chatGroup.setServerGroupId(serverGroupId);
                                }
                                chatGroup.setName(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getName());
                                chatGroup.setMaxMember(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getMaxMember());
                                chatGroup.setNotifyAct(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getNotifyAct());
                                chatGroup.setStatus(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getStatus());

                                if (chatGroup.getId() == null) {
                                    databaseViewModel.insertChatGroupVM(chatGroup);
                                } else {
                                    databaseViewModel.updateChatGroupVM(chatGroup);
                                }

                                System.out.println("i=============" + i);
                                List<Long> userIdList = new ArrayList<Long>();
                                if (successChatGroupMemberBean.result.chatGroupMemberList.get(i).getChatGroupMembers() != null) {

                                    for (int j = 0; j < successChatGroupMemberBean.result.chatGroupMemberList.get(i).getChatGroupMembers().size(); j++) {
                                        System.out.println("j=============" + j);
                                        Long userId = Long.valueOf(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getChatGroupMembers().get(j).userId);
                                        userIdList.add(userId);
                                        ChatGroupMember tmpChatGroupMemberFS = new ChatGroupMember();
                                        tmpChatGroupMemberFS.setAppUserId(userId);
                                        tmpChatGroupMemberFS.setChatGroupId(Long.valueOf(chatGroup.getId()));
                                        ChatGroupMember chatGroupMember = databaseViewModel.getChatGroupMemberByUserAndGroupVM(userId, Long.valueOf(chatGroup.getId()));
                                        if (chatGroupMember == null) {
                                            chatGroupMember = new ChatGroupMember();
                                            chatGroupMember.setAppUserId(userId);
                                            chatGroupMember.setChatGroupId(Long.valueOf(chatGroup.getId()));
                                        }
                                        chatGroupMember.setPrivilegeTypeEn(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getChatGroupMembers().get(j).privilegeTypeEn);
                                        chatGroupMember.setAdminIs(successChatGroupMemberBean.result.chatGroupMemberList.get(i).getChatGroupMembers().get(j).getAdminIs());
                                        if (chatGroupMember.getId() == null) {
                                            databaseViewModel.insertChatGroupMemberVM(chatGroupMember);
                                        } else {
                                            databaseViewModel.updateChatGroupMemberVM(chatGroupMember);
                                        }

                                        AppUser appUser = mainViewModel.getAppUserByIdVM(chatGroupMember.getAppUserId());
                                        if (appUser == null) {
                                            appUser = new AppUser();

                                            getUserById(getContext(), user, chatGroupMember.getAppUserId(), appUser, false);
                                        } else if (appUser.getName() == null || appUser.getFamily() == null) {
                                            getUserById(getContext(), user, chatGroupMember.getAppUserId(), appUser, true);
                                        }
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        mainViewModel.getUserInfoById().observe(getViewLifecycleOwner(), new Observer<SuccessCarInfoBean>() {
            @Override
            public void onChanged(SuccessCarInfoBean successCarInfoBean) {

            }
        });
    }

    private void getUserById(Context context, User user, Long appUserId, AppUser appUser, boolean b) {
        inputParam = GsonGenerator.getUserInfoById(user.getUsername(), user.getBisPassword(),appUserId);
        mainViewModel.getUserInfoById(inputParam);
    }
}