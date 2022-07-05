package com.example.sino.view.chatmessage;

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
                            List<Long> serverGroupIdList = new ArrayList<Long>();
                            for (int i = 0; i < successChatGroupBean.result.chatGroupList.size(); i++) {
                                Long serverGroupId = Long.valueOf(successChatGroupBean.result.chatGroupList.get(i).id);
                                serverGroupIdList.add(serverGroupId);
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
                                }
                                databaseViewModel.updateChatGroupVM(chatGroup);
                            }

                            ChatGroup tmpChatGroupFS = new ChatGroup();
                            tmpChatGroupFS.setNotServerGroupIdList(serverGroupIdList);
                            List<ChatGroup> chatGroupUserRemovedList = databaseViewModel.getChatGroupListByParamVM(serverGroupIdList);
                            for (ChatGroup chatGroupUserRemoved : chatGroupUserRemovedList) {
                                chatGroupUserRemoved.setStatusEn(GeneralStatus.Inactive.ordinal());
                                databaseViewModel.updateChatGroupVM(chatGroupUserRemoved);
                            }
                        }
                    }
                }
            }
        });

        mainViewModel.getChatGroupMemberList().observe(getViewLifecycleOwner(), new Observer<SuccessCarInfoBean>() {
            @Override
            public void onChanged(SuccessCarInfoBean successCarInfoBean) {

            }
        });
    }
}