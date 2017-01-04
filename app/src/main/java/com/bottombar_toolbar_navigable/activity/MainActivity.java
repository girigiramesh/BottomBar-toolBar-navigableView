package com.bottombar_toolbar_navigable.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bottombar_toolbar_navigable.R;
import com.bottombar_toolbar_navigable.fragment.CameraFragment;
import com.bottombar_toolbar_navigable.fragment.FragmentGallery;
import com.bottombar_toolbar_navigable.fragment.SendFragment;
import com.bottombar_toolbar_navigable.fragment.SlideFragment;

import java.util.ArrayList;
import java.util.Arrays;

import static com.bottombar_toolbar_navigable.R.menu.main;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, TextWatcher {
    private static final String TAG = "MainActivity";
    private ImageView cameraIV, galleryIV, slideIV, sendIV;
    private TextView cameraTV, galleryTV, slideTV, sendTV;
    private LinearLayout cameraRL, galleryRL, slideRL, sendRL;
    private FrameLayout content_frame;
    private View view;
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView list_view;
    EditText et_search;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

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

        list_view = (ListView) findViewById(R.id.list_view);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.addTextChangedListener(this);
        initList();

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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.toString().equals("")) {
            // reset listview
            initList();
        } else {
            // perform search
            searchItem(charSequence.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.cameraRL:
                deselectAll();
                cameraTV.setTextColor(getResources().getColor(R.color.appColor));
                cameraIV.setImageResource(R.drawable.ic_menu_camera);
                CameraFragment cameraFragment = new CameraFragment();
                fragmentTransaction.add(R.id.content_frame, cameraFragment, "cameraFragment");
                break;
            case R.id.galleryRL:
                deselectAll();
                galleryTV.setTextColor(getResources().getColor(R.color.appColor));
                galleryIV.setImageResource(R.drawable.ic_menu_gallery);
                FragmentGallery fragmentGallery = new FragmentGallery();
                fragmentTransaction.replace(R.id.content_frame, fragmentGallery, "FragmentGallery");
                break;
            case R.id.slideRL:
                deselectAll();
                slideTV.setTextColor(getResources().getColor(R.color.appColor));
                slideIV.setImageResource(R.drawable.ic_menu_slideshow);
                SlideFragment slideFragment = new SlideFragment();
                fragmentTransaction.replace(R.id.content_frame, slideFragment, "SlideFragment");
                break;
            case R.id.sendRL:
                deselectAll();
                sendTV.setTextColor(getResources().getColor(R.color.appColor));
                sendIV.setImageResource(R.drawable.ic_menu_send);
                SendFragment sendFragment = new SendFragment();
                fragmentTransaction.replace(R.id.content_frame, sendFragment, "SendFragment");
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
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


    public void deselectAll() {

        cameraTV.setTextColor(getResources().getColor(R.color.deselectedFont));
        galleryTV.setTextColor(getResources().getColor(R.color.deselectedFont));
        slideTV.setTextColor(getResources().getColor(R.color.deselectedFont));
        sendTV.setTextColor(getResources().getColor(R.color.deselectedFont));

        cameraIV.setImageResource(R.drawable.ic_menu_camera);
        galleryIV.setImageResource(R.drawable.ic_menu_gallery);
        slideIV.setImageResource(R.drawable.ic_menu_slideshow);
        sendIV.setImageResource(R.drawable.ic_menu_send);
    }

    public void initList() {
        items = new String[]{"Canada", "China", "Japan", "USA", "Germany", "Australia"};
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<>(this, R.layout.row_item, R.id.tv_search, listItems);
        list_view.setAdapter(adapter);
    }

    public void searchItem(String textToSearch) {
        for (String item : items) {
            if (!item.contains(textToSearch)) {
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
