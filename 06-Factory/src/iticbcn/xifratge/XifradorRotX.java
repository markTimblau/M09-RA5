package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    static final char[] abcMin = "aàábçcdeèéfghiïíjklmnñopqrstuùúüvwxyz".toCharArray();
    static final char[] abcMay = "aàábçcdeèéfghiïíjklmnñopqrstuùúüvwxyz".toUpperCase().toCharArray();

    // Cifra una cadena de text desplaçant-la X posicions
    public String[] xifraRotX(String[] cadena, int[] displace) {
        String[] resultado = new String[cadena.length];
        int testCounter = 0;

        for (String test : cadena) {
            StringBuilder testDone = new StringBuilder();
            for (int i = 0; i < test.length(); i++) {
                char letter = test.charAt(i);
                int charinDic = 0;

                if (isMinus(letter)) {
                    for (int j = 0; j < abcMin.length; j++) {
                        if (abcMin[j] == letter) charinDic = j;
                    }
                    letter = abcMin[(charinDic + displace[testCounter]) % abcMin.length];
                } else if (isMayus(letter)) {
                    for (int j = 0; j < abcMay.length; j++) {
                        if (abcMay[j] == letter) charinDic = j;
                    }
                    letter = abcMay[(charinDic + displace[testCounter]) % abcMay.length];
                }

                testDone.append(letter);
            }
            resultado[testCounter++] = testDone.toString();
        }
        return resultado;
    }

    // Desxifra
    public String[] desxifraRotX(String[] cadena, int[] displace) {
        String[] resultado = new String[cadena.length];
        int testCounter = 0;

        for (String test : cadena) {
            StringBuilder testDone = new StringBuilder();
            for (int i = 0; i < test.length(); i++) {
                char letter = test.charAt(i);
                int charinDic = 0;

                if (isMinus(letter)) {
                    for (int j = 0; j < abcMin.length; j++) {
                        if (abcMin[j] == letter) charinDic = j;
                    }
                    letter = abcMin[(charinDic - displace[testCounter] + abcMin.length) % abcMin.length];
                } else if (isMayus(letter)) {
                    for (int j = 0; j < abcMay.length; j++) {
                        if (abcMay[j] == letter) charinDic = j;
                    }
                    letter = abcMay[(charinDic - displace[testCounter] + abcMay.length) % abcMay.length];
                }

                testDone.append(letter);
            }
            resultado[testCounter++] = testDone.toString();
        }
        return resultado;
    }

    // Comprova si és minúscula
    public boolean isMinus(char letter) {
        for (char c : abcMin) {
            if (letter == c) return true;
        }
        return false;
    }

    // Comprova si és majúscula
    public boolean isMayus(char letter) {
        for (char c : abcMay) {
            if (letter == c) return true;
        }
        return false;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int rota = 0;
        try {
            rota = Integer.parseInt(clau);
            if (rota < 0 || rota > 40) {
                throw new NumberFormatException("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            System.err.println("Clau de RotX ha de ser un sencer de 0 a 40");
        }

        String[] msgArray = new String[]{msg};
        String[] textXifratArray = xifraRotX(msgArray, new int[]{rota});
        String textXifrat = textXifratArray[0];
        return new TextXifrat(textXifrat);
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int rota = 0;
        try {
            rota = Integer.parseInt(clau);
            if (rota < 0 || rota > 40) {
                throw new NumberFormatException("Clau de RotX ha de ser un sencer de 0 a 40");
            }
        } catch (NumberFormatException e) {
            System.err.println("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        String[] textArray = new String[]{xifrat.toString()};
        String[] desxifratArray = desxifraRotX(textArray, new int[]{rota});
        return desxifratArray[0];
    }
}
