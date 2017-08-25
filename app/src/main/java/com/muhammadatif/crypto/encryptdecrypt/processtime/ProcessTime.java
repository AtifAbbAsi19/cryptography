package com.muhammadatif.crypto.encryptdecrypt.processtime;

import java.util.concurrent.TimeUnit;

/**
 * Created by Atif Arif on 8/25/2017.
 */

public class ProcessTime {

    private long callTimeEncryption;  //starting time
    private long elapsedTimeEncryption;//ending time

    private long callTimeDecryption;  //starting time
    private long elapsedTimeDecryption;//ending time



    private static ProcessTime timeAnalyticsInstance = null;




    public static ProcessTime getInstance() {  //Singleton Pattren
        if (timeAnalyticsInstance == null) timeAnalyticsInstance = new ProcessTime();
        return timeAnalyticsInstance;
    }


    public long getCallTimeEncryption() {
        return callTimeEncryption;
    }

    public void setCallTimeEncryption() {
        this.callTimeEncryption=0;
        this.callTimeEncryption  = System.currentTimeMillis();
    }

    public long getElapsedTimeEncryption() {
        return elapsedTimeEncryption;
    }

    public void setElapsedTimeEncryption() {
        this.elapsedTimeEncryption =0;
        this.elapsedTimeEncryption= System.currentTimeMillis();
    }

    public long getCallTimeDecryption() {
        return callTimeDecryption;
    }

    public void setCallTimeDecryption() {
        this.callTimeDecryption =0;
        this.callTimeDecryption = System.currentTimeMillis();
    }

    public long getElapsedTimeDecryption() {
        return elapsedTimeDecryption;
    }

    public void setElapsedTimeDecryption() {
        this.elapsedTimeDecryption = System.currentTimeMillis();
    }






    public float callTimeEncryptionInSec() {
        setElapsedTimeEncryption();
        long totalCallTime = getElapsedTimeEncryption() - getCallTimeEncryption();
        float secs = TimeUnit.MILLISECONDS.toSeconds(totalCallTime);
        return secs == 0 ? totalCallTime / 1000f : secs;
    }


    public float callTimeDecryptionInSec() {

        setElapsedTimeDecryption();
        long totalCallTime = 0;
        //Total=Final -Initial
        totalCallTime = getElapsedTimeDecryption() - getCallTimeDecryption();
        float secs = TimeUnit.MILLISECONDS.toSeconds(totalCallTime);
        return secs == 0 ? totalCallTime / 1000f : secs;
    }


}
