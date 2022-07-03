package com.example.sino.view.fragment.login;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.model.SuccessActivationBean;
import com.example.sino.model.db.User;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.utils.common.Util;
import com.example.sino.utils.common.Constant;
import com.example.sino.viewmodel.MainViewModel;
import com.example.sino.viewmodel.RegisterViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentActivation extends Fragment {

    private RegisterViewModel viewModel;
    private MainViewModel mainViewModel;
    private EditText activationCode;
    private CircularProgressView progressView;
    private String code = "";
    private User user;

    public FragmentActivation() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        activationCode = view.findViewById(R.id.activationCode);
        progressView = view.findViewById(R.id.waitProgress);
        Util.hideProgress(progressView);
        activationCode.setText("");

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        user = SinoApplication.getInstance().getCurrentUser();
        view.findViewById(R.id.btn_active).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activationCode.getText() != null) {
                    System.out.println("getMobileNo=====" + user.getMobileNo());
                    if (user.getMobileNo() != null) {
                        code = GsonGenerator.sendActivationCodeToGson(user.getMobileNo(), activationCode.getText().toString());
                        if (code != null) {
                            viewModel.sendActivationCode(code, progressView);
                        } else {
                            Log.e("TAG", "onClick: Code Is Null");
                        }
                    }
                }
            }
        });

        viewModel.getActivationResult().observe(getViewLifecycleOwner(), new Observer<SuccessActivationBean>() {
            @Override
            public void onChanged(SuccessActivationBean successActivationBean) {

                if (successActivationBean.getERROR() == null && successActivationBean.getSUCCESS() != null) {
                    user.setName(successActivationBean.getRESULT().getName());
                    user.setServerUserId(successActivationBean.getRESULT().getServerUserId());
                    user.setFamily(successActivationBean.getRESULT().getFamily());
                    user.setUsername(successActivationBean.getRESULT().getUsername());
                    user.setBisPassword(successActivationBean.getRESULT().getBisPassword());
                    user.setCompanyName(successActivationBean.getRESULT().getCompanyName());
                    user.setPictureBytes(successActivationBean.getRESULT().getPictureBytes());
                    user.setLoginIs(true);
                    mainViewModel.insertUser(user);
                    SinoApplication.getInstance().setCurrentUser(user);
                    //createImageUserPath(successActivationBean);
                    getActivity().recreate();
                    Navigation.findNavController(view).navigate(R.id.createPasswordFragment);
                } else {
                    Toast toast = Toast.makeText(getActivity(), successActivationBean.getERROR(), Toast.LENGTH_LONG);
                    Util.showToast(toast, getActivity());
                    toast.show();
                }

            }
        });

    }

    public void createImageUserPath(SuccessActivationBean successActivationBean) {
        if (successActivationBean.getRESULT().getPictureBytes() != null) {
            byte[] bytes = new byte[successActivationBean.getRESULT().getPictureBytes().size()];
            for (int i = 0; i < successActivationBean.getRESULT().getPictureBytes().size(); i++) {
                bytes[i] = successActivationBean.getRESULT().getPictureBytes().get(i).byteValue();
            }
            String path = Environment.getExternalStorageDirectory().toString() + Constant.DEFAULT_OUT_PUT_DIR + Constant.DEFAULT_USER_IMG_OUT_PUT_DIR;
            File dir = new File(path);
            int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                String picturePathUrl = path + "/user-pic.jpg";
                OutputStream outputStream = null;
                File file = new File(picturePathUrl); // the File to save to
                try {
                    outputStream = new FileOutputStream(file);
                    outputStream.write(bytes);
                    user.setPicturePathUrl(picturePathUrl);

                    mainViewModel.insertUser(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}