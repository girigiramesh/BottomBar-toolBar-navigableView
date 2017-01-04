package com.bottombar_toolbar_navigable.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ramesh on 1/4/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private ProgressDialog dialog = null;

    public void showProgressDialog(String message) {
        if (dialog == null) dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        try {
            if (!dialog.isShowing()) dialog.show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void dismissProgressDialog() {
        dialog.dismiss();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cn = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cn.getActiveNetworkInfo();
        return nf != null && nf.isConnected();
    }
    /**
     * Displays a simple toast message
     *
     * @param message
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
