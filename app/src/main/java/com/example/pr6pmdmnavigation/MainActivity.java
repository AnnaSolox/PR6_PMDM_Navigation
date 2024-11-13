package com.example.pr6pmdmnavigation;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pr6pmdmnavigation.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController navController;

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

        navController = ((NavHostFragment) Objects.requireNonNull(
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)))
                .getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.options1Fragment, R.id.options2Fragment, R.id.options3Fragment,
                R.id.drawer1Fragment, R.id.drawer2Fragment, R.id.drawer3Fragment,
                R.id.bottom1Fragment, R.id.bottom2Fragment, R.id.bottom3Fragment
        ).setOpenableLayout(binding.main).build();

        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item);
    }

}