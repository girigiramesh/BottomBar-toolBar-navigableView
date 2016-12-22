package com.bottombar_toolbar_navigable;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bottombar_toolbar_navigable.fragment.CameraFragment;
import com.bottombar_toolbar_navigable.fragment.FragmentGallery;
import com.bottombar_toolbar_navigable.fragment.SendFragment;
import com.bottombar_toolbar_navigable.fragment.SlideFragment;

import static com.bottombar_toolbar_navigable.R.menu.main;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ImageView cameraIV, galleryIV, slideIV, sendIV;
    private TextView cameraTV, galleryTV, slideTV, sendTV;
    private LinearLayout cameraRL, galleryRL, slideRL, sendRL;
    private FrameLayout content_frame;
    View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        content_frame = (FrameLayout) findViewById(R.id.content_frame);
        cameraIV = (ImageView) findViewById(R.id.cameraIV);
        galleryIV = (ImageView) findViewById(R.id.galleryIV);
        slideIV = (ImageView) findViewById(R.id.slideIV);
        sendIV = (ImageView) findViewById(R.id.sendIV);

        cameraTV = (TextView) findViewById(R.id.cameraTV);
        galleryTV = (TextView) findViewById(R.id.galleryTV);
        slideTV = (TextView) findViewById(R.id.slideTV);
        sendTV = (TextView) findViewById(R.id.sendTV);

        cameraRL = (LinearLayout) findViewById(R.id.cameraRL);
        cameraRL.setOnClickListener(this);
        galleryRL = (LinearLayout) findViewById(R.id.galleryRL);
        galleryRL.setOnClickListener(this);
        slideRL = (LinearLayout) findViewById(R.id.slideRL);
        slideRL.setOnClickListener(this);
        sendRL = (LinearLayout) findViewById(R.id.sendRL);
        sendRL.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (id) {
            case R.id.cameraRL:
                CameraFragment cameraFragment = new CameraFragment();
                fragmentTransaction.add(R.id.content_frame, cameraFragment, "cameraFragment");
                break;
            case R.id.galleryRL:
                FragmentGallery fragmentGallery = new FragmentGallery();
                fragmentTransaction.replace(R.id.content_frame, fragmentGallery, "FragmentGallery");
                break;
            case R.id.slideRL:
                SlideFragment slideFragment = new SlideFragment();
                fragmentTransaction.replace(R.id.content_frame, slideFragment, "SlideFragment");
                break;
            case R.id.sendRL:
                SendFragment sendFragment = new SendFragment();
                fragmentTransaction.replace(R.id.content_frame, sendFragment, "SendFragment");
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
}
