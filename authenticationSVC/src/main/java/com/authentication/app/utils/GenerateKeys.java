package com.authentication.app.utils;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKeys {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
         var keyPair =keyPairGenerator.generateKeyPair();

         byte[] publicKey =keyPair.getPublic().getEncoded();
         byte[] privateKey =keyPair.getPrivate().getEncoded();

        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("public.pem")));
        PemObject pemObject =new PemObject("PUBLIC KEY",publicKey);
        pemWriter.writeObject(pemObject);
        pemWriter.close();


        PemWriter pemWriter2=new PemWriter(new OutputStreamWriter(new FileOutputStream("private.pem")));
        PemObject pemObject2=new PemObject("PRIVATE KEY",privateKey);
        pemWriter2.writeObject(
                pemObject2
        );
        pemWriter2.close();


    }


}
