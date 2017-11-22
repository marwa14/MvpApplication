package com.example.hp.mvpapplication.Tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AlertDialog;

import com.example.hp.mvpapplication.R;

/**
 * Created by HP on 17/11/2017.
 */

public class Utils {

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    public static boolean CheckNetworkConnection(Context context) {

        ConnectivityManager check = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = check.getActiveNetworkInfo();

        if (info != null && info.isConnectedOrConnecting())
            return true;
        return false;
    }

    public static void showAlertDialog(final Activity activity) {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(activity, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(activity);
        }
        builder.setTitle(R.string.info)
                .setMessage(R.string.msg)
                .setNegativeButton(R.string.close, getListener())
                .show();
    }

    @NonNull
    private static DialogInterface.OnClickListener getListener() {
        return new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
    }
}
