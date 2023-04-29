import java.io.*;
import java.util.regex.*;

public class Day3_part1 {

    static int convertLowerC = -96;
    static int convertUpperC = -38;

    static int score = 0;
    static int charValue = 0;

    static String linea = "";
    static String left_compartiment = "";
    static String right_compartiment = "";

    static char sameChar;

    public static void main(String[] args) throws IOException {
        try {
            Reader inputText = new FileReader("input.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            linea = myReader.readLine();
            while (linea != null) {
                if (!linea.isEmpty()) {
                    // System.out.println(linea);
                    left_compartiment = linea.substring(0, linea.length() / 2);
                    right_compartiment = linea.substring((linea.length() / 2), linea.length());

                    // Realizar comparacion entre ambos strings para sacar el caracter en comun

                    char[] first = left_compartiment.toCharArray();
                    char[] second = right_compartiment.toCharArray();

                    for (int i = 0; i < linea.length() / 2; i++) {
                        for (int j = 0; j < linea.length() / 2; j++) {
                            // System.out.println(first[i]);
                            if (first[i] == second[j]) {
                                sameChar = first[i];
                            }

                        }
                    }
                    // System.out.println(sameChar);

                    String stringToMatch = Character.toString(sameChar);

                    // Saber si son mayusculas o minusculas

                    String regexLowerC = "[a-z]+";
                    // String regexUpperC = "[A-Z]+";
                    boolean resultLowerC;
                    // boolean resultUpperC;

                    Pattern patternLowerC = Pattern.compile(regexLowerC);
                    Matcher matcherLowerC = patternLowerC.matcher(stringToMatch);
                    resultLowerC = matcherLowerC.matches();

                    // Pattern patternUpperC = Pattern.compile(regexUpperC);
                    // Matcher matcherUpperC = patternUpperC.matcher(stringToMatch);
                    // resultUpperC = matcherUpperC.matches();

                    // System.out.println(resultLowerC);
                    // System.out.println(resultUpperC);

                    // Darle el valor adecuado
                    if (resultLowerC) {
                        charValue = (int) sameChar + convertLowerC;
                        score += charValue;
                    } else {
                        charValue = (int) sameChar + convertUpperC;
                        score += charValue;
                    }

                    // System.out.println(left_compartiment);
                    // System.out.println(right_compartiment);

                    linea = myReader.readLine();
                }
            }

            myReader.close();

            System.out.println(score);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
