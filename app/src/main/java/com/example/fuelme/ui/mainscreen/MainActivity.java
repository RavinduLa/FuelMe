/*
* IT19014128 - A.M.W.W.R.L. Wataketiya
* Enterprise Application Development - SE4040
* Start screen for FuelMe Application
*
*
* */

package com.example.fuelme.ui.mainscreen;
import com.example.fuelme.ui.mainscreen.adapters.PageAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fuelme.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = "demo";

    //assign views and instances
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager pager;
    TabLayout tabLayout;
    TabItem allStationsItem, favouriteStationsItem;
    PageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the toolbar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        //bind the views
        pager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        allStationsItem = findViewById(R.id.all_item);
        favouriteStationsItem = findViewById(R.id.favourites_item);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        //set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(this);

        //instantiate new toggle with drawerlayout
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open_drawer, R.string.close_drawer);

        //add toggle to drawer layout, set drawer indicator and sync toggle state
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        //set page adapter
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        pager.setAdapter(pagerAdapter);

        //add selector for tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    //method for clicking the drawer button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //method for handling click events of navigation drawer menu items
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navMenu_login:
                //handle login here
                //Log.d(TAG, "Login Clicked");
                Toast.makeText(this, "Login Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navMenu_logout:
                //handle logout here
                Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navMenu_about:
                //handle about here
                Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navMenu_ownerDashboard:
                //navigate to dashboard here
                Toast.makeText(this, "Dashboard clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navMenu_settings:
                //settings here
                Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d(TAG,"Default ran in onNavigationItemSelected");
                break;
        }
        closeDrawer();
        return true;
    }

    //method for closing the drawer
    public void closeDrawer(){
        //check if the drawer is open
        //if open, close the drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //method for handling back press
    @Override
    public void onBackPressed() {
        //if the drawer is open, instead of closing the app, close the drawer
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            //else the default back press is maintained
            super.onBackPressed();
        }
    }
}