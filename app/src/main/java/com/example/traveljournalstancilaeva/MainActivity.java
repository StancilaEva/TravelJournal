package com.example.traveljournalstancilaeva;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.traveljournalstancilaeva.fragments.AboutUsFragment;
import com.example.traveljournalstancilaeva.fragments.ContactUsFragment;
import com.example.traveljournalstancilaeva.fragments.HomeFragment;
import com.example.traveljournalstancilaeva.util.DateConverter;
import com.example.traveljournalstancilaeva.util.Trip;
import com.example.traveljournalstancilaeva.util.TripType;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.traveljournalstancilaeva.databinding.ActivityMainBinding;

import java.io.File;
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
        currentFragment = HomeFragment.newInstance(tripList);
        openFragment();

    }

    private NavigationView.OnNavigationItemSelectedListener getNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    currentFragment = HomeFragment.newInstance(tripList);
                }
                else
                    if(item.getItemId()==R.id.nav_contact_us){
                        currentFragment = new ContactUsFragment();
                    }
                    else
                        if(item.getItemId()==R.id.nav_about_us){
                            currentFragment = new AboutUsFragment();
                        }
                        else if(item.getItemId()==R.id.nav_share){
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            String Body = getString(R.string.download_travel_journal_app);
                            String Sub = "http://play.google.com";
                            intent.putExtra(Intent.EXTRA_TEXT,Body);
                            intent.putExtra(Intent.EXTRA_TEXT,Sub);
                            startActivity(Intent.createChooser(intent,getString(R.string.share_travel_journal_app)));
                            return true;
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