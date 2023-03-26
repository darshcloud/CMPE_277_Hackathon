package com.cmpe277.macroeconomicfoodsecurity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Code for bottom navigation bar
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView2);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home,R.id.macro, R.id.agriculture, R.id.trade).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    String gdp = "No";
    String fdi_inflow = "No";
    String fdi_outflow = "No";
    String fdi_netflow = "No";

    public void onCheckboxClicked(@NonNull View view) {
        System.out.println("Before checking - Macro");
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.GDP:
                if (checked) {
                    Log.d("MainActivity", "GDP selected");
                    gdp = "Yes";
                }else{
                    gdp = "No";
                }
                break;
            case R.id.FDI_inflows:
                if (checked) {
                    Log.d("MainActivity", "FDI Inflow selected");
                    fdi_inflow = "Yes";
                }else{
                    fdi_inflow = "No";
                }
                break;
            case R.id.FDI_Outflows:
                if (checked) {
                    Log.d("MainActivity", "FDI OutFlow selected");
                    fdi_outflow = "Yes";
                }else{
                    fdi_outflow = "No";
                }
                break;
            case R.id.IE_Flows:
                if (checked) {
                    Log.d("MainActivity", "IE flow selected");
                    fdi_netflow = "Yes";
                }else{
                    fdi_netflow = "No";
                }
                break;
        }
    }

    String country = "India";
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        switch (pos) {
            case 0:
                Log.d("Macro","China");
                country = "China";
                break;
            case 1:
                Log.d("Macro","India");
                country = "India";
                break;
            case 2:
                Log.d("Macro","USA");
                country = "USA";
                break;

        }
    }


}