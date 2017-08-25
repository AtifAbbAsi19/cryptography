package com.muhammadatif.crypto.encryptdecrypt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.muhammadatif.crypto.encryptdecrypt.main.AppClass;
import com.muhammadatif.crypto.encryptdecrypt.security.AndroidConcealHelper;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    EditText inputDataTextField,createKeyStore;

    TextView encryptedText,decryptedText;

    Button encryptBtn,createKey,decryptBtn;

    Context context=MainActivity.this;

    AndroidConcealHelper androidConcealHelper;

    String temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        createKeyStore.setText("key");

        androidConcealHelper =new AndroidConcealHelper(context);


        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    AppClass.startTimeEncryption();

                    temp=androidConcealHelper.encrypt(createKeyStore.getText().toString(),inputDataTextField.getText().toString());
                    float sec=AppClass.endTimeEncryption();
                    encryptedText.setText(temp);
                    temp="";

//                    encryptedText.setText(androidConcealHelper.encrypt(createKeyStore.getText().toString(),inputDataTextField.getText().toString()));
//
//
//                    float sec=AppClass.endTimeEncryption();
                  Toast.makeText(getApplicationContext(), "SEC::"+ sec+"s", Toast.LENGTH_SHORT).show();
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

                try {
                    AppClass.startTimeDecryption();

                    temp=androidConcealHelper.decrypt(createKeyStore.getText().toString(),encryptedText.getText().toString());

                    float sec=AppClass.endTimeDecryption();

                    decryptedText.setText(temp);

                    Toast.makeText(getApplicationContext(), ""+sec+"s", Toast.LENGTH_SHORT).show();
                } catch (KeyChainException e) {
                    e.printStackTrace();
                } catch (CryptoInitializationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }//end of onCreate

        private void init() {

        inputDataTextField=(EditText)findViewById(R.id.inputEditTextField);
        createKeyStore=(EditText)findViewById(R.id.createKeyStore);
        encryptedText=(TextView)findViewById(R.id.encrypted);
        decryptedText=(TextView)findViewById(R.id.decrypted);
        encryptBtn=(Button)findViewById(R.id.submitBtn);
        createKey=(Button)findViewById(R.id.addKey);
        decryptBtn=(Button)findViewById(R.id.decryptKey);
    }//End of initialization

}//End of class