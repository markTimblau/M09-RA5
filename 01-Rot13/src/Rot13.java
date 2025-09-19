//PROGRAMA QUE ENCRIPTA UNSANDO ROT13
public class Rot13 {
    public static void main(String[] args) {
        //ESTOS SON LAS PRUEVAS
        String[] test = {"ABC","XYZ","Hola, Mr. calçot","Perdó, per tu què és?"};
        System.out.println("Xifrat \n ------");
        
        //CODIFICAR
        String[] result = split(test);
        
        //RESULTADO
        //PONER ESTO EN OTRA CLASE PARA NO TENER QUE ESCRIBIRLO OTRA VEZ
        for (int i = 0; i < test.length; i++){
            System.out.println(test[i] + " => " + result[i]);
        }
        //DECODIFICAR
        String[] result2 = split(result);
    }
    //DIVIDIMOS CADA TEST CADA TEST
    public static String[] split(String[] test){
        String resultTemp = "";
        for (int i = 0; i < test.length; i++){
            //METEMOS CADA CHAR DEL TEST DENTRO DE ENCRIPT
            char[] encript = test[i].toCharArray();
            resultTemp += show(encript)  +",";
            //System.out.println(test[i] + " => " + resultTemp);
        }
        //NO USAR COMAS DARÁ ERROR--------------------------
        String[] result = resultTemp.split(",");
        //--------------------------------------------------
        return result;
    }
    //MUESTRA EL RESULTADO ENCRIPTADO
    public static String show(char[] encript){
        //ARRAYS ENCRIPTADOS
        char[] encriptionLower = "ïxàrwéühóaqjzçbèälñgdyskömáuïívpnúeçtòfc".toCharArray();
        char[] encriptionUpper = "ïxàrwéühóaqjzçbèälñgdyskömáuïívpnúeçtòfc".toUpperCase().toCharArray();
        //DONDE GUARDAREMOS EL TEXTO ENCRIPTADO
        String result ="";
        //MIRAMOS CADA CARACTER
        for (int i = 0; i < encript.length; i++){
            //ES UNA LETRA?
            if (Character.isLetter(encript[i])){
                if (Character.isLowerCase(encript[i])){
                    //SI ES MAYUSCULA
                    result+= encript(encript[i],encriptionLower);
                } else {
                    //SI ES MINUSCULA
                    result+= encript(encript[i],encriptionUpper);
                }
            } else {
                //NO ES UNA LETRA
                result+= encript[i];
            }
        }
        return result;
    }
    //ENCRIPTA EL TEXTO
    public static char encript(char letter, char[] type){
        char result = ' ';
        for (int i = 0; i < type.length; i++){
            //  ENCONTRAMOS LA LETRA
            if (letter == type[i]){
                
                if (i + 13 >= type.length){
                    //SOBRESALE DEL CODIGO
                    result = type[i+13-type.length];
                } else {
                    //no SOBRESALE
                    result = type[i+13];
                }
            }
        }
        return result;
    }
}

//REVISAR QUE HACIA MOD PARA LAS DIVISIONES