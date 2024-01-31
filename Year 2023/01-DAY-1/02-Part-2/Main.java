import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    static String linea = "";
    static int sumatorio;


    public static void main(String[] args) throws IOException {
        try {
            Reader inputText = new FileReader("input.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            linea = myReader.readLine();
            while (linea != null) {
                if (!linea.isEmpty()) {

                    linea = convertToInt(linea);
                    linea = extractInt(linea);

                    char[] charArray = linea.toCharArray();


                    int num1 = Character.getNumericValue(charArray[0]);
                    int num2 = Character.getNumericValue(charArray[charArray.length-1]);
                    int concat = Integer.parseInt(num1 + "" + num2);

                    sumatorio += concat;

                    
                    linea = myReader.readLine();
                }
            }
            // Termina bucle
            myReader.close();

            System.out.println(sumatorio);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    static String extractInt(String str)
    {
        // Borrar caracteres que no sean un numero.
        str = str.replaceAll("[^0-9]", ""); // regex
 
        if (str.equals(""))
            return "-1";
 
        return str;
    }

        static String convertToInt(String str)
    {
        // Sustituir texto por su valor numerico
        str = str.replaceAll("one", "one1one"); // regex
        str = str.replaceAll("two", "two2two"); // regex
        str = str.replaceAll("three", "three3three"); // regex
        str = str.replaceAll("four", "four4four"); // regex
        str = str.replaceAll("five", "five5five"); // regex
        str = str.replaceAll("six", "six6six"); // regex
        str = str.replaceAll("seven", "seven7seven"); // regex
        str = str.replaceAll("eight", "eight8eight"); // regex
        str = str.replaceAll("nine", "nine9nine"); // regex
 
        if (str.equals(""))
            return "-1";
 
        return str;
    }
}