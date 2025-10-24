import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AES {
    
    public static final String ALOGISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];   
    private static final String CLAU = "abooba";

    //CONVIERTE LA CONTRASEÑA EN UNA LISTA DE BYTES UTF-8
    public static SecretKeySpec generaClau(String clau) throws Exception {
        //APLICAMOS EL HASH
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        //LO CONVERTIMOS EN BYTES
        byte[] key = sha.digest(clau.getBytes("UTF-8"));
        //CREAMOS LA CLAVE AES
        return new SecretKeySpec(key, ALOGISME_XIFRAT);
    }
    //CIFRAMOS EL TEXTO
    public static byte[] xifraAES(String msg, String clau) throws Exception{
        //GENERA VALORES ALEATORIOS CIFRADOS SEGUROS
        SecureRandom random = new SecureRandom();
        //LLENA iv DE LOS NUMEROS ALEATORIOS
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKey = generaClau(clau);
        //CREMOS OBJETO AES
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        //LO INICIALIZAMOS CON  NUESTRO CIFRADO Y LA CLAVE IV
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        byte[] encrypted = cipher.doFinal(msg.getBytes("UTF-8"));

        // Concatenar IV + datos cifrados para poder recuperarlo luego
        byte[] result = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
        return result;
    }
    public static String desxifraAES(byte[] bIvIMagXifrat, String clau) throws Exception{
        byte[] iv = new byte[MIDA_IV];
        byte[] encrypted = new byte[bIvIMagXifrat.length - MIDA_IV];

        // Separar IV del mensaje
        System.arraycopy(bIvIMagXifrat, 0, iv, 0, MIDA_IV);
        System.arraycopy(bIvIMagXifrat, MIDA_IV, encrypted, 0, encrypted.length);

        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        SecretKeySpec secretKey = generaClau(clau);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted, "UTF-8");
    }
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                    "Hola Andrés cómo está tu cuñado",
                    "Agora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++){
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " 
                    + e.getLocalizedMessage());
            }
            System.out.println("----------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
