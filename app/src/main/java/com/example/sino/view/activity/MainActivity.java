package com.example.sino.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sino.R;
import com.example.sino.SinoApplication;
import com.example.sino.model.db.User;
import com.example.sino.viewmodel.MainViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();


        viewModel.getAllUser().observeForever(new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users == null || users.size() == 0) {
                    navController.navigate(R.id.fragmentRegistration);
                } else {
                    user = users.get(0);
                    SinoApplication.getInstance().setCurrentUser(user);

                    if (!user.getLoginIs()) {
                        navController.navigate(R.id.fragmentActivation);
                        return;
                    }

                    if (!user.getAutoLogin()) {
                        navController.navigate(R.id.createPasswordFragment);
                        return;
                    }

                    if (user.getAutoLogin() && user.getLoginIs()) {
                        navController.navigate(R.id.homeFragment);
                    }
                }
            }
        });

    }
}