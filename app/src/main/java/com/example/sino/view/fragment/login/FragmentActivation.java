package com.example.sino.view.fragment.login;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.sino.R;
import com.example.sino.model.SuccessActivationBean;
import com.example.sino.model.db.User;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.utils.Util;
import com.example.sino.utils.common.Constant;
import com.example.sino.viewmodel.RegisterViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FragmentActivation extends Fragment {

    private RegisterViewModel viewModel;
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

        if (getArguments() != null) {
            user = getArguments().getParcelable("user");
            System.out.println("1==" + user.getMobileNo());
            System.out.println("1==" + user.getName());
            System.out.println("1==" + user.getFamily());
        }

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        view.findViewById(R.id.btn_active).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activationCode.getText() != null) {
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

                viewModel.getAllUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
                    @Override
                    public void onChanged(List<User> users) {
                        user = users.get(0);
                        user.setName(successActivationBean.getRESULT().getName());
                        user.setServerUserId(successActivationBean.getRESULT().getServerUserId());
                        user.setFamily(successActivationBean.getRESULT().getFamily());
                        user.setUsername(successActivationBean.getRESULT().getUsername());
                        user.setBisPassword(successActivationBean.getRESULT().getBisPassword());
                        user.setCompanyName(successActivationBean.getRESULT().getCompanyName());
                        user.setPictureBytes(successActivationBean.getRESULT().getPictureBytes());
                        createImageUserPath(successActivationBean);
                    }
                });
            }
        });

    }

    public void createImageUserPath(SuccessActivationBean successActivationBean) {
        List<Integer> list = successActivationBean.getRESULT().getPictureBytes();
        Iterator<Integer> iterator = list.iterator();
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

                viewModel.insertUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}