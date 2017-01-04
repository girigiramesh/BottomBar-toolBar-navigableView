package com.bottombar_toolbar_navigable.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bottombar_toolbar_navigable.R;

/**
 * Created by Ramesh on 12/21/16.
 */

public class CameraActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CameraActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }
}
