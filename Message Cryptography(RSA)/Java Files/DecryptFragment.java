package com.example.vsujayreddy.encryption;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Key;
import java.util.Random;
import java.io.*;


import java.math.BigInteger;

import javax.crypto.Cipher;


/**
 * A simple {@link Fragment} subclass.
 */
public class DecryptFragment extends Fragment {


    public DecryptFragment() {
        // Required empty public constructor
    }
    public static Key privateKey=GenerateFragment.generate.getpr();

   public static class decrypt {
        public static byte[] decodedBytes = null;
        public static byte[] s;
        public static byte[] getb(byte [] b){
            s=b;
            return b;

        }
        public static byte[] getc()

        {
            try {
                Cipher c = Cipher.getInstance("RSA");
                c.init(Cipher.DECRYPT_MODE, privateKey);
                decodedBytes = c.doFinal(s);
            } catch (Exception e) {
            }
            return decodedBytes;
        }
    }

    String s1="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final decrypt dec=new decrypt();
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_decrypt, container, false);
        final TextView t=(TextView) v.findViewById(R.id.textView2);
        final EditText e=(EditText) v.findViewById(R.id.editText);
        final Button btn=(Button) v.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Handler mt=new Handler();

               String s1=e.getText().toString();
                try{
                byte[] encoded = s1.getBytes("ISO-8859-1");
                byte [] b=dec.getb(encoded);}
                catch(Exception e){}
               final String s2 = new String(dec.getc());


                mt.post(new Runnable() {
                    @Override
                    public void run() {
                       t.setText(""+s2);
                    }
                });
            }
        });
        return v;

    }

}
