package com.example.vsujayreddy.encryption;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
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
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
//import android.util.Base64;
//import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;

//import static com.example.vsujayreddy.encryption.EncryptFragment.Encrypt.encodedBytes;


/**
 * A simple {@link Fragment} subclass.
 */
public class EncryptFragment extends Fragment {


    public EncryptFragment() {
        // Required empty public constructor
    }

 public static class encrypt{
      public static byte[] encodedBytes=null ;

      public static Key s;
      public static byte[] m;
      public static Key gets(Key m){
          s=m;
          return m;
      }
      public static byte[] getm(byte[] k){
          m=k;
          return k;
      }

      public static byte[] getec() {
          try {
              Cipher c = Cipher.getInstance("RSA");
              c.init(Cipher.ENCRYPT_MODE, s);
              encodedBytes = c.doFinal(m);
          } catch (Exception e) {
              // Log.e(TAG, "RSA encryption error");
          }
          return encodedBytes;
      }
  }



    TextView cipher;
    String ext = "";
    String str1 = "";
    //String str2 = "";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final encrypt encrypt=new encrypt();


        View v = inflater.inflate(R.layout.fragment_encrypt, container, false);
        cipher = (TextView) v.findViewById(R.id.textView5);
        final EditText e = (EditText) v.findViewById(R.id.textView4);
        final EditText ext1 = (EditText) v.findViewById(R.id.textView7);
        Button button2 = (Button) v.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Handler et = new Handler();
                ext = e.getText().toString();
              final byte[] m=ext.getBytes();
                str1=ext1.getText().toString();

                byte publicKeyData[] = Base64.decode(str1, Base64.DEFAULT);
                X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyData);
                try{
                KeyFactory kf = KeyFactory.getInstance("RSA");
                PublicKey k = kf.generatePublic(spec);
                    Key p=encrypt.gets(k);
                }
                catch(Exception e){}
                final byte[] encrypted = encrypt.getm(m);
                et.post(new Runnable() {
                    @Override
                    public void run() {
                       try{
                        String decoded = new String(encrypt.getec(), "ISO-8859-1");
                            cipher.setText(""+decoded);}
                catch(Exception e){}
                    }
                });

            }
        });
        return v;
    }
}


