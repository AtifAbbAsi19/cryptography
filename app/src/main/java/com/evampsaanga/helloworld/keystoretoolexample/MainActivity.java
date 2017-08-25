package com.evampsaanga.helloworld.keystoretoolexample;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.evampsaanga.helloworld.keystoretoolexample.Security.AndroidConcealHelper;
import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.keychain.KeyChain;
import com.facebook.crypto.util.SystemNativeCryptoLibrary;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

public class MainActivity extends AppCompatActivity {


    EditText inputDataTextField,createKeyStore;

    TextView initialTextView,encryptedText,decryptedText;

    Button encryptBtn,createKey,decryptBtn;




    Context context=MainActivity.this;

//    static  Crypto crypto;


    AndroidConcealHelper androidConcealHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        createKeyStore.setText("key");

        androidConcealHelper =new AndroidConcealHelper(context);

//        KeyChain keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
//        crypto = AndroidConceal.get().createDefaultCrypto(keyChain);






        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                inputDataTextField.getText().toString();

                try {
                    encryptedText.setText(androidConcealHelper.encrypt(createKeyStore.getText().toString(),inputDataTextField.getText().toString()));
                } catch (KeyChainException e) {
                    e.printStackTrace();
                } catch (CryptoInitializationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });


        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                inputDataTextField.getText().toString();

                try {
                    decryptedText.setText(androidConcealHelper.decrypt(createKeyStore.getText().toString(),encryptedText.getText().toString()));
                } catch (KeyChainException e) {
                    e.printStackTrace();
                } catch (CryptoInitializationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        //decryptedText





    }//end of onCreate


        private void init() {

        inputDataTextField=(EditText)findViewById(R.id.editTextField);
        createKeyStore=(EditText)findViewById(R.id.createKeyStore);

        initialTextView=(TextView)findViewById(R.id.initial_text);
        encryptedText=(TextView)findViewById(R.id.encrypted);
        decryptedText=(TextView)findViewById(R.id.decrypted);

        encryptBtn=(Button)findViewById(R.id.submitBtn);


        createKey=(Button)findViewById(R.id.addKey);

        decryptBtn=(Button)findViewById(R.id.decryptKey);
    }








//    public static String encrypt(String key, String value) throws KeyChainException, CryptoInitializationException, IOException {
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        OutputStream cryptoStream = crypto.getCipherOutputStream(bout, Entity.create(key));
//        cryptoStream.write(value.getBytes("UTF-8"));
//        cryptoStream.close();
//        String result = Base64.encodeToString(bout.toByteArray(), Base64.DEFAULT);
//        bout.close();
//        return result;
//    }
//
//    public static String decrypt(String key, String value) throws KeyChainException, CryptoInitializationException, IOException {
//        ByteArrayInputStream bin = new ByteArrayInputStream(Base64.decode(value, Base64.DEFAULT));
//        InputStream cryptoStream = crypto.getCipherInputStream(bin, Entity.create(key));
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        int read = 0;
//        byte[] buffer = new byte[1024];
//        while ((read = cryptoStream.read(buffer)) != -1) {
//            bout.write(buffer, 0, read);
//        }
//        cryptoStream.close();
//        String result = new String(bout.toByteArray(), "UTF-8");
//        bin.close();
//        bout.close();
//        return result;
//    }
//
//









//
//    public String encryption(String strNormalText){
//        String seedValue = "YourSecKey";
//        String normalTextEnc="";
//        try {
//            normalTextEnc = AESHelper.encrypt(seedValue, strNormalText);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return normalTextEnc;
//    }
//    public String decryption(String strEncryptedText){
//        String seedValue = "YourSecKey";
//        String strDecryptedText="";
//        try {
//            strDecryptedText = AESHelper.decrypt(seedValue, strEncryptedText);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return strDecryptedText;
//    }
//



}//End of class