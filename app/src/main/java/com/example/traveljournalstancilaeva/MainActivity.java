package com.example.traveljournalstancilaeva;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.traveljournalstancilaeva.fragments.AboutUsFragment;
import com.example.traveljournalstancilaeva.fragments.ContactUsFragment;
import com.example.traveljournalstancilaeva.fragments.HomeFragment;
import com.example.traveljournalstancilaeva.util.Trip;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournalstancilaeva.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Fragment currentFragment;
    ArrayList<Trip> tripList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToggle();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(getNavigationItemSelectedListener());
        tripList.add(new Trip("offf","mama ei",1000,1.4));
    }

    private NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    currentFragment = HomeFragment.newInstance(tripList); //newInstance daca avem parametrii si o sa ai
                    //deci sa nu uiti sa schimbi
                }
                else
                    if(item.getItemId()==R.id.nav_contact_us){
                        currentFragment = new ContactUsFragment();
                    }
                    else
                        if(item.getItemId()==R.id.nav_about_us){
                            currentFragment = new AboutUsFragment();
                        }
                openFragment();
                    drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_mainActivity_fragment,currentFragment)
                .commit();
    }

    private void initToggle() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
          this,
          drawerLayout,
          toolbar,
          R.string.navigation_drawer_open,
          R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }



}