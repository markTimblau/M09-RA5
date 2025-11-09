package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TextXifrat {

    private final byte[] textBytes;

    // Constructor que recibe un array de bytes (el texto cifrado)
    public TextXifrat(byte[] textBytes) {
        this.textBytes = Arrays.copyOf(textBytes, textBytes.length);
    }

    // (Opcional) Constructor que recibe una cadena (texto plano, por compatibilidad)
    public TextXifrat(String par) {
        this.textBytes = par.getBytes(StandardCharsets.UTF_8);
    }

    @Override 
    public String toString() {
        return new String(textBytes, StandardCharsets.UTF_8);
    }

    public byte[] getBytes() {
        return Arrays.copyOf(textBytes, textBytes.length);
    }
}
