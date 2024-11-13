package com.example.pr6pmdmnavigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pr6pmdmnavigation.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController optionsNavController;
    private NavController drawerNavController;
    private AppBarConfiguration appBarDrawerConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);

        //IMPORTANTE CON LA TOOLBAR: si no se modifica el código del padding la barra de estado qeudará superpuesta a la toolbar. En lugar de referenciar el layout referenciamos la toolbar
        ViewCompat.setOnApplyWindowInsetsListener(toolbar, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });


        setSupportActionBar(toolbar);

        NavController bottomNavController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host_bottom_fragment))).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, bottomNavController);

        appBarDrawerConfiguration = new AppBarConfiguration.Builder(
                R.id.drawer1Fragment, R.id.drawer2Fragment
        ).setOpenableLayout(binding.main).build();

        drawerNavController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host_drawer_fragment))).getNavController();
        NavigationUI.setupWithNavController(toolbar, drawerNavController, appBarDrawerConfiguration);

        AppBarConfiguration appbarOptionsConfiguration = new AppBarConfiguration.Builder(
                R.id.options1Fragment, R.id.options2Fragment
        ).build();
        optionsNavController = ((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.nav_host_bottom_fragment))).getNavController();
        NavigationUI.setupWithNavController(toolbar, optionsNavController, appbarOptionsConfiguration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, optionsNavController) ||
                super.onOptionsItemSelected(item);
    }

}