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
import com.example.sino.model.SuccessRegisterBean;
import com.example.sino.model.db.User;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.utils.Util;
import com.example.sino.viewmodel.RegisterViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentRegistration extends Fragment {
    private RegisterViewModel viewModel;
    private String mobileToGson = "";
    private CircularProgressView progressView;
    private EditText mobileNo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        mobileNo = view.findViewById(R.id.mobileNo);


        progressView = view.findViewById(R.id.waitProgress);
        Util.hideProgress(progressView);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobileToGson = GsonGenerator.mobileNoConfirmationToGson(mobileNo.getText().toString());
                if (mobileToGson != null) {
                    viewModel.sendPhoneNumber(mobileToGson, progressView);
                } else {
                    Log.e("TAG", "onClick: mobileNo Is Null");
                }
            }
        });


        viewModel.getRegisterResult().observe(getViewLifecycleOwner(), new Observer<SuccessRegisterBean>() {
            @Override
            public void onChanged(SuccessRegisterBean successRegisterBean) {

                if (successRegisterBean.getERROR() == null && successRegisterBean.getSUCCESS() != null) {
                    User user = viewModel.getUserByMobileNo(mobileNo.getText().toString());
                    if (user == null){
                        user = new User();
                        user.setMobileNo(mobileNo.getText().toString());
                        viewModel.insertUser(user);
                    }

                    System.out.println("===========" + user.getName());
                    System.out.println("===========" + user.getFamily());
                    System.out.println("===========" + user.getBisPassword());
                    System.out.println("===========" + user.getMobileNo());
                    System.out.println("===========" + user.getPictureBytes());

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("user", user);
                    Navigation.findNavController(view).navigate(R.id.action_fragmentRegistration_to_fragmentActivation,bundle);
                } else {
                    Toast toast = Toast.makeText(getActivity(), successRegisterBean.getERROR(), Toast.LENGTH_LONG);
                    Util.showToast(toast, getActivity());
                    toast.show();
                }
            }
        });
    }
}