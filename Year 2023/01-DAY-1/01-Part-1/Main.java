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
                    
                    linea = extractInt(linea);
                    //System.out.println(linea);
                    
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
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^0-9]", ""); // regular expression
 
        // Replace all the consecutive white
        // spaces with a single space
        //str = str.replaceAll(" +", "");
 
        if (str.equals(""))
            return "-1";
 
        return str;
    }
}