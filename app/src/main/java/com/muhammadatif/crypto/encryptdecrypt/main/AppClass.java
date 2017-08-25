package com.muhammadatif.crypto.encryptdecrypt.main;

import android.app.Application;
import android.content.Context;

import com.muhammadatif.crypto.encryptdecrypt.processtime.ProcessTime;
import com.muhammadatif.crypto.encryptdecrypt.security.AndroidConcealHelper;

/**
 * Created by Atif Arif on 8/25/2017.
 */

public class AppClass extends Application {

   public static ProcessTime processTime;
    public static AndroidConcealHelper androidConcealHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        processTime = ProcessTime.getInstance(); //init

        androidConcealHelper= AndroidConcealHelper.getInstance(getApplicationContext()); //init


    }




    public static void startTimeEncryption() {
        processTime.setCallTimeEncryption();
    }

    public static float endTimeEncryption(){
        return processTime.callTimeEncryptionInSec();
    }


    public static void startTimeDecryption() {
        processTime.setCallTimeDecryption();
    }

    public static float endTimeDecryption(){
        return processTime.callTimeDecryptionInSec();
    }



}
