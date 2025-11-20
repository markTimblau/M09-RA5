package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;

public class ClauPublica{
        public KeyPair generaParellClausRSA() throws Exception{
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(2048, new SecureRandom());
        return gen.generateKeyPair();
        }
        public byte[] xifraRSA(String msg, PublicKey clauPublica)throws Exception{
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes("UTF-8"));
        }
        public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada){
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
                byte[] msgDesxifrat = cipher.doFinal(msgXifrat);
                return new String(msgDesxifrat, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
