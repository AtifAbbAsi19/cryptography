package com.muhammadatif.crypto.encryptdecrypt.security;

import android.content.Context;
import android.util.Base64;

import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.keychain.KeyChain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Muhammad Atif Arif on 8/24/2017.
 */

/*
*
    * To install Conceal on your Android application project, you need to download the following binaries :

    libconceal.jar from https://raw.github.com/facebook/conceal/gh-pages/downloads/libconceal.jar
    conceal_android.jar from https://raw.github.com/facebook/conceal/gh-pages/downloads/conceal_android.jar
    #This is how you can add .jar file in Android Studio
       1) Copy the .jar file.
       2) paste the file in Libs folder.now open File>Project Structure>Dependencies ,then Click on add button
          select second Option jar Dependencies and add jar files to project
          or open build.gradle.
          simply Add both jars as dependencies
          compile files('libs/libconceal.jar')
          compile files('libs/conceal_android.jar') and sync project.!
    Native binaries from https://raw.github.com/facebook/conceal/gh-pages/downloads/libs.zip
    Then, go to App/src/main/ create jniLibs folder.
    Then, unzip libs.zip drop the .so files into a jniLibs/ folder located at src/main/jniLibs.

    *
*
*
* */



public class AndroidConcealHelper {

    private static Crypto crypto;
    private KeyChain keyChain;


    public AndroidConcealHelper(Context context) { // Default Constructor

        // Creates a new Crypto object with default implementations of
       // a key chain as well as native library.
        keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
        crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
    }

         // Check for whether the crypto functionality is available
        // This might fail if Android does not load libraries correctly.
    public static boolean isAvailable() {

        // Check if crypto instance is available
        if (!crypto.isAvailable()) {
            return false;
        }
        return true;
    }


    public static String encrypt(String key, String value) throws KeyChainException,
            CryptoInitializationException, IOException {
        // Check for whether the crypto functionality is available
        // This might fail if Android does not load libraries correctly.

        String result = "Crypto not found";

        if (isAvailable()) {

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            // Creates an output stream which encrypts the data as
            // it is written to it and writes it out to the file.
            OutputStream cryptoStream = crypto.getCipherOutputStream(bout, Entity.create(key));
            cryptoStream.write(value.getBytes("UTF-8"));
            cryptoStream.close();
            result = Base64.encodeToString(bout.toByteArray(), Base64.DEFAULT);
            bout.close();
            return result;

        }

        return result;

    }

    public static String decrypt(String key, String value) throws KeyChainException,
            CryptoInitializationException, IOException {

        // Check for whether the crypto functionality is available
        // This might fail if Android does not load libraries correctly.

        String result= "Crypto not found";

        if (isAvailable()) {
            ByteArrayInputStream bin = new ByteArrayInputStream(Base64.decode(value, Base64.DEFAULT));
            InputStream cryptoStream = crypto.getCipherInputStream(bin, Entity.create(key));
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            int read = 0;
            byte[] buffer = new byte[1024];
            while ((read = cryptoStream.read(buffer)) != -1) {
                bout.write(buffer, 0, read);
            }
            cryptoStream.close();
            result = new String(bout.toByteArray(), "UTF-8");
            bin.close();
            bout.close();
        }

        return result;

    }


}
