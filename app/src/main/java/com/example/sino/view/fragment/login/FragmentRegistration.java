package com.example.sino.view.fragment.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.model.SuccessRegisterBean;
import com.example.sino.model.db.User;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.utils.common.Util;
import com.example.sino.viewmodel.MainViewModel;
import com.example.sino.viewmodel.RegisterViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentRegistration extends Fragment {
    private String mobileToGson = "";
    private CircularProgressView progressView;
    private EditText mobileNo;
    private RegisterViewModel viewModel;
    private MainViewModel mainViewModel;


    public FragmentRegistration() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mobileNo = view.findViewById(R.id.mobileNo);
        progressView = view.findViewById(R.id.waitProgress);
        Util.hideProgress(progressView);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobileNo.getText() != null) {
                    mobileToGson = GsonGenerator.mobileNoConfirmationToGson(mobileNo.getText().toString());
                    viewModel.sendPhoneNumber(mobileToGson, progressView, view);
                } else {
                    Log.e("TAG", "onClick: mobileNo Is Null");
                }
            }
        });


        viewModel.getRegisterResult().observe(getViewLifecycleOwner(), new Observer<SuccessRegisterBean>() {
            @Override
            public void onChanged(SuccessRegisterBean successRegisterBean) {

                if (successRegisterBean.getERROR() == null && successRegisterBean.getSUCCESS() != null) {
                    User user = new User();
                    user.setMobileNo(mobileNo.getText().toString());
                    mainViewModel.insertUser(user);
                    SinoApplication.getInstance().setCurrentUser(user);
                    getActivity().recreate();
                    Navigation.findNavController(view).navigate(R.id.fragmentActivation);

                } else {
                    Toast toast = Toast.makeText(getActivity(), successRegisterBean.getERROR(), Toast.LENGTH_LONG);
                    Util.showToast(toast, getActivity());
                    toast.show();
                }
            }
        });
    }
}