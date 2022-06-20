package com.example.sino.view.activity;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sino.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the navigation host fragment from this Activity
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        // Instantiate the navController using the NavHostFragment
        NavController navController = navHostFragment.getNavController();
        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(this, navController);
    }

}