//PROGRAMA QUE ENCRIPTA UNSANDO ROT13
public class Rot13 {
    public static void main(String[] args) {
        //ESTOS SON LAS PRUEVAS
        String[] test = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        
        System.out.println("Xifrat \n ------");
        //CODIFICAR
        String[] encriptado = split(test, true);
        //MOSTRAR
        show(test, encriptado);

        System.out.println("Desxifrat \n ------");
        //DECODIFICAR
        String[] desencriptado = split(encriptado, false);
        //MOSTRAR
        show(encriptado, desencriptado);
    }
    //DIVIDIMOS CADA TEST CADA TEST
    public static String[] split(String[] test, boolean type){

        String resultat = "";
        for (int i = 0; i < test.length; i++){
            //METEMOS CADA CHAR DEL TEST DENTRO DE ENCRIPT
            char[] encript = test[i].toCharArray();
            //DIVIDIMOS LOS TEST POR '
            resultat += "" + mayOrMin(encript, type) + "'";
        }
        //CONVERTIMOS EL STRING EN UN ARRAY PARA DIVIDIR LOS TESTS
        String[] result = resultat.split("'");
        return result;
    }
    //MUESTRA EL RESULTADO ENCRIPTADO
    public static String mayOrMin(char[] encript, boolean type){
        //ARRAYS ENCRIPTADOS
        char[] encriptionLower = "xàrwétühóaqjzçbèäloñgdysköimáuïívpnúeòfc".toCharArray();
        char[] encriptionUpper = "xàrwétühóaqjzçbèäloñgdysköimáuïívpnúeòfc".toUpperCase().toCharArray();
        //DONDE GUARDAREMOS EL TEXTO ENCRIPTADO
        String result ="";
        //MIRAMOS CADA CARACTER
        for (int i = 0; i < encript.length; i++){
            //ES UNA LETRA?
            if (Character.isLetter(encript[i])){
                if (Character.isLowerCase(encript[i])){
                    //SI ES MINUSCULA
                    result+= encript(encript[i],encriptionLower, type);
                } else {
                    //SI ES MAYUSCULA
                    result+= encript(encript[i],encriptionUpper, type);
                }
            } else {
                //NO ES UNA LETRA
                result+= encript[i];
            }
        }
        return result;
    }
    //ENCRIPTA EL TEXTO
    public static char encript(char letter, char[] mayMin, boolean type){
        char result = ' ';
        for (int i = 0; i < mayMin.length; i++){
            //  ENCONTRAMOS LA LETRA
            if (letter == mayMin[i]){
                //ENCRIPTAMOS O DESENCRIPTAMOS?
                if (type){
                    if (i + 13 >= mayMin.length){
                        //SOBRESALE DEL CODIGO
                        result = mayMin[i+13 - mayMin.length];
                    } else {
                        //no SOBRESALE
                        result = mayMin[i+13];
                    }
                } else {
                    if (i - 13 < 0){
                        //SOBRESALE DEL CODIGO
                        result = mayMin[i-13 + mayMin.length];
                    } else {
                        //no SOBRESALE
                        result = mayMin[i-13];
                    }                    
                }
            }
        }
        return result;
    }
    //MUESTRA EL RESULTADO
    public static void show(String[] start, String[] finish){
       for (int i = 0; i < start.length; i++){
           System.out.println(start[i] + " => " + finish[i]);
       }
    }
}