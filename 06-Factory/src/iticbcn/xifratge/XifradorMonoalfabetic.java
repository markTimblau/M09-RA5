package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class XifradorMonoalfabetic implements Xifrador{
    static final char[] abc = "aàábçcdeèéfghiïíjklmnñopqrstuùúüvwxyz".toUpperCase().toCharArray();
    static char[] permutacio = null;

    public  XifradorMonoalfabetic (){
        permutacio = permutaAlfabet();
    }
    //GENERA UNA PERMUTACIÓN DEL ALFABETO


    public char[] permutaAlfabet() {
        List<Character> permuta = new ArrayList<>();
        //AÑADIMOS TODO EL ABC EN LA LISTA
        for (char letter : abc){
            permuta.add(letter);
        }
        //LA MEZCLAMOS
        Collections.shuffle(permuta);
        char[] abcShufled = new char[permuta.size()];
        //LO VOLVEMOS A METER TODO EN UN ARRAY
        for (int i = 0; i < permuta.size(); i++){
            abcShufled[i] = permuta.get(i);
        }
        return abcShufled;
    }
    //CIFRAMOS LOS TESTS
    public String[] xifraMonoAlfa(char[] abcShufled, String[] tests){
        return operacion(abcShufled, tests, true);
    }
    //DESCIFRAMOS LOS TESTS
    public String[] desxifraMonoAlfa(char[] abcShufled, String[] tests){
        return operacion(abcShufled, tests, false);
    }
    //USAMOS SHIFRA PARA DISTINGIR ENTRE CIFRAR Y DESCIFRAR
    public String[] operacion(char[] abcShufled, String[] tests, boolean shifra){
        String[] resultado = new String[tests.length];
        //CONTAMOS CUANTOS TESTS LLEVAMOS
        int testCounter = 0;
        for (String test : tests){
            StringBuilder testDone = new StringBuilder();
            for (int i = 0; i < test.length(); i++){
                //GUARDAREMOS EL CARACTER POR CODIFICAR AQUÍ
                char letter = test.charAt(i);
                //0 = NO LETTER
                int type = 0;
                if (Character.isUpperCase(letter)){
                    //1 = MAYUS
                    type = 1;
                } else if (Character.isLowerCase(letter)){
                    //2 = MINUS
                    type = 2;
                }
                switch (type) {
                    case 1 -> {
                        if (shifra){
                            for (int j = 0; j < abc.length; j++){
                                if (abc[j] == test.charAt(i)){
                                    letter = abcShufled[j];
                                }
                            }
                        } else {
                            for (int j = 0; j < abcShufled.length; j++){
                                if (abcShufled[j] == test.charAt(i)){
                                    letter = abc[j];
                                }
                            }
                        }
                    }
                    case 2 -> {
                        if (shifra){
                            for (int j = 0; j < abc.length; j++){
                                if (abc[j] == Character.toUpperCase(test.charAt(i))){
                                    letter = Character.toLowerCase(abcShufled[j]);
                                }
                            }
                        } else {
                            for (int j = 0; j < abcShufled.length; j++){
                                if (abcShufled[j] == Character.toUpperCase(test.charAt(i))){
                                    letter = Character.toLowerCase(abc[j]);
                                }
                            }
                        }
                    }
                    default -> {
                    }
                }
                //NO HACE FALTA CAMBIAR NADA
                testDone.append(letter);
            }
            //AÑADIMOS EL TEST CODIFICADO AL RESULTADO
            resultado[testCounter] = testDone.toString();
            //AÑADIMOS 1 A LOS TEST REALIZADOS
            testCounter++;
        }
        return resultado;
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        //CONVERTIMOS EL TEXTO EN UN ARRAY
        String[] text = {msg};
        //GUARDAMOS EL RESTULADO COMO UN ARRAY
        String[] cifrado = xifraMonoAlfa(permutacio, text);
        //DEVOLVEMOS UN RESULTADO DE TIPO TextXifrat
        return new TextXifrat(cifrado[0]);
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null){
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        //VOLVEMOS LA ENTRADA EN UNA STRING
        String textCifrat = xifrat.toString();
        //CONVERTIMOS EL TEXTO EN UN ARRAY
        String[] text = {textCifrat};
        //GUARDAMOS EL RESTULADO COMO UN ARRAY
        String[] descifrado = desxifraMonoAlfa(permutacio, text);
        //DEVOLVEMOS UN RESULTADO DE TIPO STRING
        return descifrado[0];
    }
}
