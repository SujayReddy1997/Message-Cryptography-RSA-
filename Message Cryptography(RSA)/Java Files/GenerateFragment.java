package com.example.vsujayreddy.encryption;


import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.util.Random;

import static com.example.vsujayreddy.encryption.R.id.button1;
import static com.example.vsujayreddy.encryption.R.id.textView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenerateFragment extends Fragment {


    public GenerateFragment() {
        // Required empty public constructor
    }


    TextView Publickeye;
   public static class generate {
       public static Key publicKey = null;
       public static Key privateKey = null;
       public static String pubKeyStr=null;
       public static String priKeyStr=null;
       public static String getp() {
           try{
               KeyPairGenerator kpg=KeyPairGenerator.getInstance("RSA");
               kpg.initialize(256);
               KeyPair kp=kpg.genKeyPair();
               publicKey=kp.getPublic();
               privateKey=kp.getPrivate();

               byte[] pubByte = publicKey.getEncoded();
               pubKeyStr = new String(Base64.encodeToString(pubByte,Base64.DEFAULT));

              byte[] priByte = privateKey.getEncoded();
               priKeyStr = new String(Base64.encodeToString(priByte,Base64.DEFAULT));

           }
           catch (Exception e){

           }
           return  pubKeyStr;

       }
       public static Key getpr(){
           return privateKey;
       }



   }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_generate3, container, false);
        Publickeye = (TextView) v.findViewById(R.id.textview9);
        final Button button1 = (Button) v.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 button1.setEnabled(false);
                final Handler mt = new Handler();
                final generate g=new generate();
                mt.post(new Runnable() {
                    @Override
                    public void run() {
                       Publickeye.setText(""+g.getp());

                    }
                });

            }
        });


        return v;


    }
}