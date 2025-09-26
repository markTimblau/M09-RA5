//PROGRAMA QUE ENCRIPTA Y DESENCRIPTA USANDO RotX
public class RotX {
    static final char[] abcMin = "aàábçcdeèéfghiïíjklmnñopqrstuùúüvwxyz".toCharArray();
    static final char[] abcMay = "aàábçcdeèéfghiïíjklmnñopqrstuùúüvwxyz".toUpperCase().toCharArray();

    public static void main(String[] args) {
        String[] test = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        int[] displaceTest ={0,2,4,6};
        System.out.println("Xifrat \n ------");
        //CIFRAR CADA TEST
        String[] encriptado = xifraRotX(test, displaceTest);

        //MOSTRAR CIFRADO
        for (int i = 0; i < encriptado.length; i++){
            System.out.println(test[i] + " => " + encriptado[i]);
        }
        System.out.println("Desxifrat \n ------");
        //DESCIFRAR CADA TEST
        String[] desencriptado = desxifraRotX(encriptado, displaceTest);
        for (int i = 0; i < desencriptado.length; i++){
            System.out.println(encriptado[i] + " => " + desencriptado[i]);
        }
        System.out.println("\n Missatge xifrat: "+test[3] +"\n ------");
        forcaBrutaRotX(encriptado[3]);
    }
    //CIFRA UNA CADENA DE TEXTO DESPLAZANDOLO X POSICIONES
    public static String[] xifraRotX (String[] cadena, int[] displace){
        //CREAMOS EL ARRAY DONDE GUARDAREMOS TEMPORALMENTE LOS RESULTADOS
        String[] resultado = new String[cadena.length];
        int testCounter = 0;
        //DIVIDIMOS LOS TESTS
        for (String test : cadena){
            StringBuilder testDone = new StringBuilder();
            for (int i = 0; i < test.length(); i++){
                //GUARDAREMOS EL CARACTER CODIFICADO AQUÍ
                char letter;
                //POSICION EN EL DICCIONARIO
                int charinDic = 0;
                //MINUSCULA?
                if (isMinus(test.charAt(i))){
                    //BUSCAMOS EN EL DICCIONARIO MIN
                    for (int j = 0; j < abcMin.length; j++){
                        if (abcMin[j] == test.charAt(i)){
                            charinDic = j;
                        }
                    }
                    if (charinDic + displace[testCounter] >= abcMin.length){
                        //SOBRESALE DEL CODIGO
                        letter = abcMin[charinDic+displace[testCounter] - abcMin.length];
                    } else {
                        //no SOBRESALE
                        letter = abcMin[charinDic+displace[testCounter]];
                    }
                //MAYUSCULA?
                } else if (isMayus(test.charAt(i))){
                    //BUSCAMOS EN EL DICCIONARIO MAY
                    for (int j = 0; j < abcMay.length; j++){
                        if (abcMay[j] == test.charAt(i)){
                            charinDic = j;
                        }
                    }
                    if (charinDic + displace[testCounter] >= abcMay.length){
                        //SOBRESALE DEL CODIGO
                        letter = abcMay[charinDic+displace[testCounter] - abcMay.length];
                    } else {
                        //no SOBRESALE
                        letter = abcMay[charinDic+displace[testCounter]];
                    }
                //NO ES UNA LETRA
                } else {
                    letter = test.charAt(i);
                }
                //AÑADIMOS EL CARACTER AL TEST CODIFICADO
                testDone.append(letter);
            }
            //AÑADIMOS EL TEST CODIFICADO AL RESULTADO
            resultado[testCounter] = testDone.toString();
            //AÑADIMOS 1 A LOS TEST REALIZADOS
            testCounter++;
        }
        return resultado;
    }
    //DESCIFRA UNA CADENA DE TEXTO DESPLAZANDOLO X POSICIONES
    public static String[] desxifraRotX (String[] cadena, int[] displace){
//CREAMOS EL ARRAY DONDE GUARDAREMOS TEMPORALMENTE LOS RESULTADOS
        String[] resultado = new String[cadena.length];
        int testCounter = 0;
        //DIVIDIMOS LOS TESTS
        for (String test : cadena){
            StringBuilder testDone = new StringBuilder("");
            for (int i = 0; i < test.length(); i++){
                //GUARDAREMOS EL CARACTER CODIFICADO AQUÍ
                char letter;
                //POSICION EN EL DICCIONARIO
                int charinDic = 0;
                //MINUSCULA?
                if (isMinus(test.charAt(i))){
                    //BUSCAMOS EN EL DICCIONARIO MIN
                    for (int j = 0; j < abcMin.length; j++){
                        if (abcMin[j] == test.charAt(i)){
                            charinDic = j;
                        }
                    }
                     if (charinDic - displace[testCounter] < 0){
                        //SOBRESALE DEL CODIGO
                        letter = abcMin[charinDic-displace[testCounter] + abcMin.length];
                    } else {
                        //no SOBRESALE
                        letter = abcMin[charinDic-displace[testCounter]];
                    }
                //MAYUSCULA?
                } else if (isMayus(test.charAt(i))){
                    //BUSCAMOS EN EL DICCIONARIO MAY
                    for (int j = 0; j < abcMay.length; j++){
                        if (abcMay[j] == test.charAt(i)){
                            charinDic = j;
                        }
                    }
                    if (charinDic - displace[testCounter] < 0){
                        //SOBRESALE DEL CODIGO
                        letter = abcMay[charinDic-displace[testCounter] + abcMay.length];
                    } else {
                        //no SOBRESALE
                        letter = abcMay[charinDic-displace[testCounter]];
                    }
                //NO ES UNA LETRA
                } else {
                    letter = test.charAt(i);
                }
                //AÑADIMOS EL CARACTER AL TEST CODIFICADO
                testDone.append(letter);
            }
            //AÑADIMOS EL TEST CODIFICADO AL RESULTADO
            resultado[testCounter] = testDone.toString();
            //AÑADIMOS 1 A LOS TEST REALIZADOS
            testCounter++;
        }
        return resultado;
    }
    //DESCIFRA UN TEXTO PROBANDO CADA POSIBLE PERMUTACIÓN
    public static void forcaBrutaRotX(String cadenaXifrada){
        //BUSCAMOS CADA LETRA
        for (int k = 0; k < abcMin.length; k++){
            StringBuilder testDone = new StringBuilder("");
            for (int i = 0; i < cadenaXifrada.length(); i++){
                //GUARDAREMOS EL CARACTER CODIFICADO AQUÍ
                char letter;
                //POSICION EN EL DICCIONARIO
                int charinDic = 0;
                //MINUSCULA?
                if (isMinus(cadenaXifrada.charAt(i))){
                    //BUSCAMOS EN EL DICCIONARIO MIN
                    for (int j = 0; j < abcMin.length; j++){
                        if (abcMin[j] == cadenaXifrada.charAt(i)){
                            charinDic = j;
                        }
                    }
                     if (charinDic - k < 0){
                        //SOBRESALE DEL CODIGO
                        letter = abcMin[charinDic-k + abcMin.length];
                    } else {
                        //no SOBRESALE
                        letter = abcMin[charinDic-k];
                    }
                //MAYUSCULA?
                } else if (isMayus(cadenaXifrada.charAt(i))){
                    //BUSCAMOS EN EL DICCIONARIO MAY
                    for (int j = 0; j < abcMay.length; j++){
                        if (abcMay[j] == cadenaXifrada.charAt(i)){
                            charinDic = j;
                        }
                    }
                    if (charinDic - k < 0){
                        //SOBRESALE DEL CODIGO
                        letter = abcMay[charinDic-k + abcMay.length];
                    } else {
                        //no SOBRESALE
                        letter = abcMay[charinDic-k];
                    }
                //NO ES UNA LETRA
                } else {
                    letter = cadenaXifrada.charAt(i);
                }
                //AÑADIMOS EL CARACTER AL TEST CODIFICADO
                testDone.append(letter);
            }
            //IMPRIME EL RESULTADO
            System.out.println("("+k+")"+testDone);
        }        
    }
        
    //BUSCA SI ES UNA MINUSCULA
    public static boolean isMinus(char letter){
        for (int i = 0; i < abcMin.length; i++){
            if (letter == abcMin[i]){return true;}
        }
        return false;
    }
    //BUSCA SI ES UNA MAYUSCULA
    public static boolean isMayus(char letter){
        for (int i = 0; i < abcMay.length; i++){
            if (letter == abcMay[i]){return true;}
        }
        return false;
    }    
    
}