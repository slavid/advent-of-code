import java.util.*;
import java.io.*;

public class Day5_part1 {
    public static Stack<String> stack1 = new Stack<String>();
    public static Stack<String> stack2 = new Stack<String>();
    public static Stack<String> stack3 = new Stack<String>();
    public static Stack<String> stack4 = new Stack<String>();
    public static Stack<String> stack5 = new Stack<String>();
    public static Stack<String> stack6 = new Stack<String>();
    public static Stack<String> stack7 = new Stack<String>();
    public static Stack<String> stack8 = new Stack<String>();
    public static Stack<String> stack9 = new Stack<String>();

    public static String linea = "";
    public static int r;
    public static char c;
    public static String c1 = "";
    public static int separator = 4;
    public static int maxLoop = 8 * 1;
    // public static int salto = 34;

    public static void readFile() throws IOException {
        try {
            FileInputStream myReader = new FileInputStream("input_small.txt");
            for (int i = 0; i <= maxLoop; i++) {
                for (int j = 0; j < separator; j++) {

                    r = myReader.read();

                    c = (char) r;
                    c1 = Character.toString(c);
                    if (!c1.equals("\r\r")) {
                        if (!c1.isBlank()) {
                            stack1.push(c1);
                            System.out.println("Metemos: " + stack1.peek());
                        }
                    }
                }
            }

            myReader.close();
        } catch (

        FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        readFile();
        // System.out.println(maxLoop * 4);
    }
}
