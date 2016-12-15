package com.laotel.tpv.boualy.ltctraining;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Boualy on 14/12/2016.
 */

public class MyAlert {
    private Context context;
    private String titleString, messageString;
    private int anInt;

    public MyAlert(Context context, String titleString, String messageString, int anInt) {
        this.context = context;
        this.titleString = titleString;
        this.messageString = messageString;
        this.anInt = anInt;
    }

    public void myDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);  /// kod undo bor hai show
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messageString);

        // Ctrl + space === create new listener onclick
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        builder.show();
    }
}


