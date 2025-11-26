import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public int npass = 0;

    private final String charset = "abcdefABCDEF1234567890!";
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());         // añadir salt
            byte[] bytes = md.digest(pw.getBytes());
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (Exception e) {
            return null;
        }
    }
    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                    pw.toCharArray(),
                    salt.getBytes(),
                    50000,          
                    128             
            );
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] bytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    public String forcaBruta(String alg, String hash, String salt) {

        int maxLen = 6;
        int n = charset.length();
        char[] pwd = new char[maxLen];

        for (int len = 1; len <= maxLen; len++) {
            if (len == 1) {
                for (int a = 0; a < n; a++) {
                    pwd[0] = charset.charAt(a);
                    npass++;

                    String intento = new String(pwd, 0, len);
                    String htest = alg.equals("SHA-512") ?
                            getSHA512AmbSalt(intento, salt) :
                            getPBKDF2AmbSalt(intento, salt);

                    if (htest.equals(hash)) return intento;
                }
            }
            if (len == 2) {
                for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++) {

                    pwd[0] = charset.charAt(a);
                    pwd[1] = charset.charAt(b);
                    npass++;

                    String intento = new String(pwd, 0, len);
                    String htest = alg.equals("SHA-512") ?
                            getSHA512AmbSalt(intento, salt) :
                            getPBKDF2AmbSalt(intento, salt);

                    if (htest.equals(hash)) return intento;
                }
            }
            if (len == 3) {
                for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++)
                for (int c = 0; c < n; c++) {

                    pwd[0] = charset.charAt(a);
                    pwd[1] = charset.charAt(b);
                    pwd[2] = charset.charAt(c);
                    npass++;

                    String intento = new String(pwd, 0, len);
                    String htest = alg.equals("SHA-512") ?
                            getSHA512AmbSalt(intento, salt) :
                            getPBKDF2AmbSalt(intento, salt);

                    if (htest.equals(hash)) return intento;
                }
            }
            if (len == 4) {
                for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++)
                for (int c = 0; c < n; c++)
                for (int d = 0; d < n; d++) {

                    pwd[0] = charset.charAt(a);
                    pwd[1] = charset.charAt(b);
                    pwd[2] = charset.charAt(c);
                    pwd[3] = charset.charAt(d);
                    npass++;

                    String intento = new String(pwd, 0, len);
                    String htest = alg.equals("SHA-512") ?
                            getSHA512AmbSalt(intento, salt) :
                            getPBKDF2AmbSalt(intento, salt);

                    if (htest.equals(hash)) return intento;
                }
            }
            if (len == 5) {
                for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++)
                for (int c = 0; c < n; c++)
                for (int d = 0; d < n; d++)
                for (int e = 0; e < n; e++) {

                    pwd[0] = charset.charAt(a);
                    pwd[1] = charset.charAt(b);
                    pwd[2] = charset.charAt(c);
                    pwd[3] = charset.charAt(d);
                    pwd[4] = charset.charAt(e);
                    npass++;

                    String intento = new String(pwd, 0, len);
                    String htest = alg.equals("SHA-512") ?
                            getSHA512AmbSalt(intento, salt) :
                            getPBKDF2AmbSalt(intento, salt);

                    if (htest.equals(hash)) return intento;
                }
            }
            if (len == 6) {
                for (int a = 0; a < n; a++)
                for (int b = 0; b < n; b++)
                for (int c = 0; c < n; c++)
                for (int d = 0; d < n; d++)
                for (int e = 0; e < n; e++)
                for (int f = 0; f < n; f++) {

                    pwd[0] = charset.charAt(a);
                    pwd[1] = charset.charAt(b);
                    pwd[2] = charset.charAt(c);
                    pwd[3] = charset.charAt(d);
                    pwd[4] = charset.charAt(e);
                    pwd[5] = charset.charAt(f);
                    npass++;

                    String intento = new String(pwd, 0, len);
                    String htest = alg.equals("SHA-512") ?
                            getSHA512AmbSalt(intento, salt) :
                            getPBKDF2AmbSalt(intento, salt);

                    if (htest.equals(hash)) return intento;
                }
            }
        }

        return null;
    }

    public String getInterval(long t1, long t2) {
        long ms = t2 - t1;

        long secs = ms / 1000;
        long mins = secs / 60;
        long hours = mins / 60;

        secs %= 60;
        mins %= 60;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                0, hours, mins, secs, ms);
    }
    public static void main(String[] args) throws Exception {

        String salt = "qpowieruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt) };

        String pwTrobat = null;
        String[] algoritmes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {

            System.out.printf("=================================\n");
            System.out.printf("Algorisme: %s\n", algoritmes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("-------------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algoritmes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass   : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps  : %s\n", h.getInterval(t1, t2));
            System.out.printf("-------------------------------\n\n");
        }
    }
}
