package com.example.sino.view.fragment.platedetection;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.model.db.User;
import com.example.sino.model.db.carinfo.SuccessCarInfoBean;
import com.example.sino.utils.GsonGenerator;
import com.example.sino.utils.common.Util;
import com.example.sino.viewmodel.MainViewModel;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


@AndroidEntryPoint
public class DetectPlateFragment extends Fragment {

    private static final String TAG = "ANPR_Demo";
    private TextView txt_three;
    private TextView txt_four;
    private String plateText, chassis;
    private Bitmap bitmap;
    private String plateText_str, vinNo_str, chassis_str;
    private boolean chassisImportant = false;
    private MainViewModel viewModel;
    private String inputParam = "";
    private User user;
    private SuccessCarInfoBean successCarInfoBeanResult;
    private CircularProgressView circularProgressView;
    private CompositeDisposable compositeDisposable;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detect_plate, container, false);

        // Asking for permissions
        String[] accessPermissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE
        };

        ArrayList<String> permissionNeededList = new ArrayList<String>();
        for (String access : accessPermissions) {
            int curPermission = ActivityCompat.checkSelfPermission(getActivity(), access);
            if (curPermission != PackageManager.PERMISSION_GRANTED) {
                permissionNeededList.add(access);
            }
        }

        if (permissionNeededList.size() > 0) {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    permissionNeededList.toArray(new String[permissionNeededList.size()]),
                    1);
        }

        return view;
    }

    private void initView(View view) {

        compositeDisposable = new CompositeDisposable();

        Button btnActivate = (Button) view.findViewById(R.id.btn_activate);
        btnActivate.setOnClickListener(onActivateClicked);

        Button btnCameraDemo = (Button) view.findViewById(R.id.btn_camera_demo);
        btnCameraDemo.setOnClickListener(onCameraDemoClicked);

        Button btn_custom = view.findViewById(R.id.btn_custom);
        btn_custom.setOnClickListener(openDialog);
    }

    private View.OnClickListener onActivateClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener onCameraDemoClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

        user = SinoApplication.getInstance().getCurrentUser();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private View.OnClickListener openDialog = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

            Dialog dialog = new Dialog(getActivity());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_enter_car_plate);
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(layoutParams);
            dialog.show();

            EditText txt_shasi = dialog.findViewById(R.id.txt_shasi);
            EditText txt_one = dialog.findViewById(R.id.txt_one);
            EditText txt_two = dialog.findViewById(R.id.txt_two);
            circularProgressView = dialog.findViewById(R.id.waitProgress);
            txt_three = dialog.findViewById(R.id.txt_three);
            txt_four = dialog.findViewById(R.id.txt_four);
            Button btn_cancel = dialog.findViewById(R.id.btn_cancel);
            Button btn_confirm = dialog.findViewById(R.id.btn_confirm);

            txt_one.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txt_one.getText().length() == 2) {
                        txt_two.requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            txt_two.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (txt_two.getText().length() == 3) {
                        txt_three.performClick();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            txt_three.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showDialogWords();
                }
            });

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            btn_confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (txt_shasi.getText().toString().isEmpty() || txt_shasi.getText().toString().trim().equals("")) {
                        Toast toast = Toast.makeText(getActivity(), "Shasi Is Null", Toast.LENGTH_LONG);
                        Util.showToast(toast, getActivity());
                        toast.show();
                        return;
                    }

                    if (txt_one.getText().toString().isEmpty() || txt_one.getText().toString().trim().equals("")) {
                        Toast toast = Toast.makeText(getActivity(), "شماره پلاک را صحرح وارد کنید", Toast.LENGTH_LONG);
                        Util.showToast(toast, getActivity());
                        toast.show();
                        return;
                    }

                    if (txt_two.getText().toString().isEmpty() || txt_two.getText().toString().trim().equals("")) {
                        Toast toast = Toast.makeText(getActivity(), "شماره پلاک را صحرح وارد کنید", Toast.LENGTH_LONG);
                        Util.showToast(toast, getActivity());
                        toast.show();
                        return;
                    }

                    if (txt_four.getText().toString().isEmpty() || txt_four.getText().toString().trim().equals("")) {
                        Toast toast = Toast.makeText(getActivity(), "شماره پلاک را صحرح وارد کنید", Toast.LENGTH_LONG);
                        Util.showToast(toast, getActivity());
                        toast.show();
                        return;
                    }

                    chassisImportant = true;
                    chassis = Util.farsiNumberReplacement(txt_shasi.getText().toString());
                    plateText = " ایران " + txt_one.getText().toString() + "-" + txt_two.getText().toString() + "" + txt_three.getText().toString() + "" + txt_four.getText().toString();
                    System.out.println("plateText======" + plateText);
                    inputParam = GsonGenerator.getCarInfo(user.getUsername(), user.getBisPassword(), plateText, chassis, true);

                    Util.showProgress(circularProgressView);

                    viewModel.getCarInfoVM(inputParam).subscribeOn(Schedulers.io())
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<SuccessCarInfoBean>() {
                                @Override
                                public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                    compositeDisposable.add(d);
                                }

                                @Override
                                public void onNext(@io.reactivex.rxjava3.annotations.NonNull SuccessCarInfoBean successCarInfoBean) {
                                    successCarInfoBeanResult = successCarInfoBean;
                                }

                                @Override
                                public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                    System.out.println(e.getLocalizedMessage());
                                }

                                @Override
                                public void onComplete() {
                                    Util.showProgress(circularProgressView);
                                    if (successCarInfoBeanResult.success != null) {
                                        if (successCarInfoBeanResult.result != null) {
                                            List<Integer> list = successCarInfoBeanResult.result.car.pictureAF.pictureBytes;
                                            byte[] bytes = new byte[list.size()];
                                            for (int i = 0; i < list.size(); i++) {
                                                bytes[i] = list.get(i).byteValue();
                                            }
                                            bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                                            plateText_str = successCarInfoBeanResult.result.car.plateText;
                                            vinNo_str = successCarInfoBeanResult.result.car.vinNo;
                                            chassis_str = successCarInfoBeanResult.result.car.chassis;

                                            dialog.dismiss();
                                            detailDialog(plateText_str, vinNo_str, chassis_str);

                                        } else {
                                            Toast toast = Toast.makeText(getActivity(), successCarInfoBeanResult.error, Toast.LENGTH_LONG);
                                            Util.showToast(toast, getActivity());
                                            toast.show();
                                        }
                                    }
                                }
                            });
                }
            });
        }
    };

    public void showDialogWords() {
        final String[] strName = new String[1];
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getActivity());

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item);
        arrayAdapter.add("الف");
        arrayAdapter.add("ج");
        arrayAdapter.add("ع");
        arrayAdapter.add("ب");
        arrayAdapter.add("ی");
        arrayAdapter.add("ل");
        arrayAdapter.add("ا");
        arrayAdapter.add("ت");
        arrayAdapter.add("ن");
        arrayAdapter.add("م");
        arrayAdapter.add("ه");
        arrayAdapter.add("ص");
        arrayAdapter.add("ط");


        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                strName[0] = arrayAdapter.getItem(which);
                System.out.println("strName=====" + strName[0]);
                txt_three.setText(strName[0]);
                txt_four.requestFocus();
            }
        });
        builderSingle.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (data != null) {
                plateText = data.getStringExtra("plateText");
                chassis = data.getStringExtra("chassis");
                System.out.println("plateText=====" + plateText);
                System.out.println("chassis=====" + chassis);

                if (plateText != null) {

                }

            }

        }
    }

    public void detailDialog(String plateText_str, String vinNo_str, String chassis_str) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detail_read_plate);
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.show();

        TextView txt_plateText = dialog.findViewById(R.id.txt_plateText);
        TextView txt_vinNo = dialog.findViewById(R.id.txt_vinNo);
        TextView txt_chassis = dialog.findViewById(R.id.txt_chassis);
        ImageView img_car = dialog.findViewById(R.id.img_car);
        if (bitmap != null) {
            img_car.setImageBitmap(bitmap);
        }
        txt_plateText.setText(" پلاک : " + plateText_str);
        txt_vinNo.setText(vinNo_str + " : vin ");
        txt_chassis.setText(" شاسی : " + chassis_str);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}