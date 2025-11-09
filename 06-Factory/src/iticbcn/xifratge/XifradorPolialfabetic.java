package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class XifradorPolialfabetic implements Xifrador{
    static final char[] abc = "aàábçcdeèéfghiïíjklmnñoòópqrstuùúüvwxyz".toUpperCase().toCharArray();
    private static long clauSecreta = 987654321;
    private static Random rnd = null;
    private static char[] abcShufled = new char[abc.length];
    //GENERA UNA PERMUTACIÓN DEL AFABETO Y LA GUARDA EN UNA VARIABLE GLOBAL
    public void permutaAlfabet(){
        List<Character> permuta = new ArrayList<>();
        //AÑADIMOS TODO EL ABC EN LA LISTA
        for (char letter : abc){
            permuta.add(letter);
        }
        //LA MEZCLAMOS USANDO EL rnd
        Collections.shuffle(permuta,rnd);
        //LO VOLVEMOS A METER TODO EN UN ARRAY
        for (int i = 0; i < permuta.size(); i++){
            abcShufled[i] = permuta.get(i);
        }
    }
    //CIFRA LA CADENA msg
    public String xifraPoliAlfa(String msg){
        return operacion(msg, true);  
    }
    //DESCIFRA LA CADENA msgXifrat
    public String desxifraPoliAlfa(String msgXifrat){
        
        return operacion(msgXifrat, false);    
    }
    //DA LOS NUMEROS RANDOM
    public void initRandom(long clauSecreta){
        rnd = new Random(clauSecreta);
    }
    //HACE LOS CALCULOS
    public String operacion (String test, boolean shifra){
        permutaAlfabet();
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
        return testDone.toString();
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
         try {
        clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            System.err.println("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        String textXifrat = xifraPoliAlfa(msg);
        return new TextXifrat(textXifrat);
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            clauSecreta = Long.parseLong(clau);
            initRandom(clauSecreta);
        } catch (NumberFormatException e) {
            System.err.println("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        return desxifraPoliAlfa(xifrat.toString());
    }
}
