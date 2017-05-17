package com.example.liaiwei.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by liaiwei on 5/17/17.
 */

public class DisplayMess extends DialogFragment{


    public DisplayMess() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        android.app.FragmentManager fm = getFragmentManager();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("hi")//please put some string here)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onDestroyView();
                        return;
                    }
                });
        show(fm, "died");
        return builder.create();
    }

}

