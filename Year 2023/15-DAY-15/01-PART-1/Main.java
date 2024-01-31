import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main{

    public static String steps = "";
    public static void main(String[] args) throws IOException{
        try (Scanner scanner = new Scanner(new File("input_small.txt"))){   
            String next;
            while (scanner.hasNextLine()) {
                next = scanner.nextLine();
                steps = next;   
            }
        }
        String[] step = steps.split(",");
        int resultado = 0;
        for (String string : step) {
            resultado += getHashValue(string);
        }

        System.out.println("Resultado: "+resultado);
    }

    public static int getHashValue(String stepParam){
        int returnNumber = 0;
        char[] stepArray = stepParam.toCharArray();
        for (char c : stepArray) {
            int value = (int) c;
            returnNumber += value;
            returnNumber *= 17;
            returnNumber = returnNumber % 256;
            
        }
        //System.out.println("String: "+stepParam+", valor: "+returnNumber);
        return returnNumber;
    }
}