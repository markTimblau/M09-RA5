
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class Monoalfabetic {
    static final char[] abc = "aàábçcdeèéfghiïíjklmnñopqrstuùúüvwxyz".toUpperCase().toCharArray();
    public static void main(String[] args) {
        //TESTS
        String[] tests = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        //CREAMOS EL CIFRADO
        char[] abcShufled = PermutaAlfabet();
        //CIFRAMOS LOS TESTS
        String[] encriptado = XifraMonoAlfa(abcShufled, tests);
        String[] desencriptado = desxifraMonoAlfa(abcShufled, encriptado);
        System.out.println("---------- \n Tests originales \n ----------");
        for (String test : tests){
            System.out.println(test);
        }
        System.out.println("---------- \n Tests encriptados \n ----------");
        for (String test : encriptado){
            System.out.println(test);
        }
        System.out.println("---------- \n Tests desencriptados \n ----------");
        for (String test : desencriptado){
            System.out.println(test);
        }
    }
    //GENERA UNA PERMUTACIÓN DEL ALFABETO
    public static char[] PermutaAlfabet() {
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
    public static String[] XifraMonoAlfa(char[] abcShufled, String[] tests){
        return operacion(abcShufled, tests, true);
    }
    //DESCIFRAMOS LOS TESTS
    public static String[] desxifraMonoAlfa(char[] abcShufled, String[] tests){
        return operacion(abcShufled, tests, false);
    }
    //USAMOS SHIFRA PARA DISTINGIR ENTRE CIFRAR Y DESCIFRAR
    public static String[] operacion(char[] abcShufled, String[] tests, boolean shifra){
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
                    case 1:
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
                        break;
                    case 2:
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
                        break;
                    default:
                        //NO HACE FALTA CAMBIAR NADA
                        break;
                }
                testDone.append(letter);
            }
            //AÑADIMOS EL TEST CODIFICADO AL RESULTADO
            resultado[testCounter] = testDone.toString();
            //AÑADIMOS 1 A LOS TEST REALIZADOS
            testCounter++;
        }
        return resultado;
    }
}
