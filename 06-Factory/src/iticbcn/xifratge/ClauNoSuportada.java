package iticbcn.xifratge;

public class ClauNoSuportada extends Exception{
    //SIN MENSAJE    
    public ClauNoSuportada() {
        super();
    }

    //CON MENSAJE
    public ClauNoSuportada(String message) {
        super(message);
    }
}
