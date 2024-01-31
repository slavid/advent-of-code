import java.io.*;
import java.util.regex.*;

public class Day3_part2 {

    static int convertLowerC = -96;
    static int convertUpperC = -38;

    static int score = 0;
    static int charValue = 0;

    static String linea = "";
    static String left_compartiment = "";
    static String right_compartiment = "";

    static String linea2 = "";
    static String linea3 = "";

    static boolean hasSameChar = false;

    static char sameChar;
    static char defSameChar;

    public static void main(String[] args) throws IOException {
        try {
            Reader inputText = new FileReader("input.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            linea = myReader.readLine();
            while (linea != null) {
                if (!linea.isEmpty()) {

                    linea2 = myReader.readLine();
                    linea3 = myReader.readLine();

                    // System.out.println(linea);
                    // System.out.println(linea2);
                    // System.out.println(linea3);

                    // Realizar comparacion entre ambos strings para sacar el caracter en comun

                    char[] first = linea.toCharArray();
                    char[] second = linea2.toCharArray();
                    char[] third = linea3.toCharArray();

                    outerLoop: for (int i = 0; i < linea.length(); i++) {
                        for (int j = 0; j < linea2.length(); j++) {
                            // System.out.println(first[i]);
                            if (first[i] == second[j]) {
                                sameChar = first[i];
                                // System.out.println(sameChar);
                                for (int k = 0; k < linea3.length(); k++) {
                                    if (third[k] == sameChar) {
                                        hasSameChar = true;
                                        defSameChar = sameChar;
                                        break outerLoop;
                                        // System.out.println(sameChar);
                                    }
                                }
                            }

                        }
                    }

                    // System.out.println(hasSameChar);
                    if (hasSameChar == true) {
                        // System.out.println(defSameChar);

                        // System.out.println(sameChar);

                        String stringToMatch = Character.toString(defSameChar);

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
                            charValue = (int) defSameChar + convertLowerC;
                            score += charValue;
                        } else {
                            charValue = (int) defSameChar + convertUpperC;
                            score += charValue;
                        }

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
