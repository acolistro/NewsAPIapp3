package com.example.newsapiapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Dialogues {
    private Dialogues() {
        //Empty
    }
    public static ProgressDialog pDialogue;


    /**
     * Showing Dialog
     * */
    public static void showDialogue(final Context context, String msg){
        // we set this to 0
        pDialogue = new ProgressDialog(context);
        pDialogue.setMessage(msg);
        pDialogue.setIndeterminate(false);
        pDialogue.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pDialogue.setCancelable(false);
        pDialogue.show();

    }

    /**
     * Dismiss Dialog
     * */
    public static void dismissDialogue()
    {
        if(pDialogue!=null){
            pDialogue.dismiss();
        }
    }
}
