package com.example.sino.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.model.SuccessPermissionBean;
import com.example.sino.model.db.User;
import com.example.sino.model.db.UserPermission;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.utils.Util;
import com.example.sino.utils.adapter.HomeAdapterRV;
import com.example.sino.viewmodel.MainViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private User user;
    private MainViewModel mainViewModel;
    private String inputParam = "";
    private CompositeDisposable compositeDisposable;
    private SuccessPermissionBean successPermissionBean;
    private RecyclerView recyclerViewPermission;
    private List<String> permissionList = new ArrayList<>();
    private CircularProgressView progressView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initVeiw(view);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        user = SinoApplication.getInstance().getCurrentUser();
        compositeDisposable = new CompositeDisposable();
        inputParam = GsonGenerator.getUserPermissionList(user.getUsername(), user.getBisPassword());
        callApiRequest();
        return view;
    }

    private void setupRecyclerView() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3) {
            @Override
            protected boolean isLayoutRTL() {
                return true;
            }
        };
        recyclerViewPermission.setLayoutManager(mLayoutManager);

        List<UserPermission> userPermissionList = mainViewModel.getUserPermission(user.getId());
        for (UserPermission s : userPermissionList) {
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_DRIVER_VIEW_LIST")) {
                permissionList.add("ROLE_APP_INSPECTION_DRIVER_VIEW_LIST");
            }
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_CAR_VIEW_LIST")) {
                permissionList.add("ROLE_APP_INSPECTION_CAR_VIEW_LIST");
            }
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_LINE_VIEW_LIST")) {
                permissionList.add("ROLE_APP_INSPECTION_LINE_VIEW_LIST");
            }
            if (s.getPermissionName().equals("ROLE_APP_GET_ADVERTISEMENT_VIEW")) {
                permissionList.add("ROLE_APP_GET_ADVERTISEMENT_VIEW");

            }
            if (s.getPermissionName().equals("ROLE_APP_GET_MNG_FLEET_VIEW")) {
                permissionList.add("ROLE_APP_GET_MNG_FLEET_VIEW");

            }
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_ENTITY_FORM_VIEW_LIST")) {
                permissionList.add("ROLE_APP_INSPECTION_ENTITY_FORM_VIEW_LIST1");

            }
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_ENTITY_FORM_VIEW_LIST")) {
                permissionList.add("ROLE_APP_INSPECTION_ENTITY_FORM_VIEW_LIST2");

            }
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_CREATE_COMPLAINT_REPORT")) {
                permissionList.add("ROLE_APP_INSPECTION_CREATE_COMPLAINT_REPORT");

            }
            if (s.getPermissionName().equals("ROLE_APP_INSPECTION_READ_NOTIFICATION_MESSAGE_LIST") ||
                    s.getPermissionName().equals("ROLE_APP_INSPECTION_WRITE_NOTIFICATION_MESSAGE")) {
                permissionList.add("ROLE_APP_INSPECTION_WRITE_NOTIFICATION_MESSAGE");
            }
        }
        recyclerViewPermission.setVisibility(View.VISIBLE);
        recyclerViewPermission.setAdapter(new HomeAdapterRV(permissionList));
    }

    private void initVeiw(View view) {
        progressView = view.findViewById(R.id.waitProgress);
        recyclerViewPermission = view.findViewById(R.id.recyclerViewPermission);
        Util.showProgress(progressView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




                /*mainViewModel.getUserPermissionListResult().observe(getViewLifecycleOwner(), new Observer<SuccessPermissionBean>() {
            @Override
            public void onChanged(SuccessPermissionBean permissionBean) {

                if (permissionBean.getRESULT() != null) {
                    if (permissionBean.getRESULT().getUserPermissionList().size() > 0) {
                        List<String> userPermissionList = permissionBean.getRESULT().getUserPermissionList();
                        for (String p : userPermissionList) {
                            UserPermission userPermission = new UserPermission();
                            userPermission.setUserId(user.getId());
                            userPermission.setPermissionName(p);
                            mainViewModel.insertPermission(userPermission);
                        }

                        System.out.println("userPermissionList====" + userPermissionList.size());
                    }
                }

                System.out.println("permissionBean====" + permissionBean.getSUCCESS());
                System.out.println("permissionBean====" + permissionBean.getRESULT());
                System.out.println("permissionBean====" + permissionBean.getERROR());
            }
        });*/

    }

    private void callApiRequest() {

        mainViewModel.getUserPermissionListVM(inputParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SuccessPermissionBean>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull SuccessPermissionBean permissionBean) {
                        successPermissionBean = permissionBean;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e("TAG", "onError: " + e.getLocalizedMessage());
                        Util.hideProgress(progressView);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "onComplete: jsoup done");

                        if (successPermissionBean.getRESULT() != null) {
                            if (successPermissionBean.getRESULT().getUserPermissionList().size() > 0) {
                                System.out.println("size===" + successPermissionBean.getRESULT().getUserPermissionList().size());
                                mainViewModel.deletePermissionByUserId(user.getId());
                                List<String> userPermissionList = successPermissionBean.getRESULT().getUserPermissionList();
                                for (String p : userPermissionList) {
                                    UserPermission userPermission = new UserPermission();
                                    userPermission.setUserId(user.getId());
                                    userPermission.setPermissionName(p);
                                    mainViewModel.insertPermission(userPermission);
                                    System.out.println("insertPermission===" + userPermission);
                                }
                                Util.hideProgress(progressView);
                                setupRecyclerView();
                            }
                        }
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}