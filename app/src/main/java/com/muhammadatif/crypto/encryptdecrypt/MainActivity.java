package com.muhammadatif.crypto.encryptdecrypt;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.muhammadatif.crypto.encryptdecrypt.security.AndroidConcealHelper;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;

import java.io.IOException;

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