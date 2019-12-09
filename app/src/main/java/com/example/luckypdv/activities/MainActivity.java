package com.example.luckypdv.activities;

import android.os.Bundle;

import com.example.luckypdv.R;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.luckypdv.models.User;
import com.example.luckypdv.utilities.CircleTransform;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.luckypdv.utilities.Constants.PROFILE_IMAGE_SIZE;

public class MainActivity extends AppCompatActivity {
    private User user;

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private AppBarConfiguration mAppBarConfiguration;
    private TextView tvFullname, tvEmail;
    private ImageView ivProfileImage;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_shop_list, R.id.nav_shops_map, R.id.nav_sign_out)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        initData();
        initViews();
    }

    private void initViews() {
        View headerView = navigationView.getHeaderView(0);
        tvFullname = headerView.findViewById(R.id.tv_full_name);
        tvEmail = headerView.findViewById(R.id.tv_email);
        ivProfileImage = headerView.findViewById(R.id.iv_profile_image);

        tvFullname.setText(user.getName());
        tvEmail.setText(user.getEmail());

        Picasso.get().
                load(user.getImage()).
                placeholder(R.mipmap.ic_launcher).
                error(R.mipmap.ic_launcher).
                resize(PROFILE_IMAGE_SIZE, PROFILE_IMAGE_SIZE).
                centerCrop().
                transform(new CircleTransform()).
                into(ivProfileImage);

        navigationView.getMenu().findItem(R.id.nav_sign_out).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                finish();
                return false;
            }
        });
    }

    private void initData() {
        user = new User();
        user.fromBundle(getIntent().getExtras());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }

    public void setNavigationView(NavigationView navigationView) {
        this.navigationView = navigationView;
    }
}
