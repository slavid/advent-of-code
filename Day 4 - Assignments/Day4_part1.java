import java.io.*;

public class Day4_part1 {

    static String linea = "";

    static String elvesTask[];

    static String elveTask_1[];
    static String elveTask_2[];

    static int elveInit_1;
    static int elveEnd_1;

    static int elveInit_2;
    static int elveEnd_2;

    static int overlapped = 0;

    public static void main(String[] args) throws IOException {
        try {
            Reader inputText = new FileReader("input.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            linea = myReader.readLine();
            while (linea != null) {
                if (!linea.isEmpty()) {
                    // System.out.println(linea);
                    elvesTask = linea.split(",");

                    elveTask_1 = elvesTask[0].split("-");
                    elveTask_2 = elvesTask[1].split("-");

                    elveInit_1 = Integer.parseInt(elveTask_1[0]);
                    elveEnd_1 = Integer.parseInt(elveTask_1[1]);

                    elveInit_2 = Integer.parseInt(elveTask_2[0]);
                    elveEnd_2 = Integer.parseInt(elveTask_2[1]);

                    // System.out.print(elveInit_1);
                    // System.out.print("-");
                    // System.out.print(elveEnd_1);
                    // System.out.print(",");
                    // System.out.print(elveInit_2);
                    // System.out.print("-");
                    // System.out.println(elveEnd_2);

                    /**
                     * Comprobar si las tareas se overlapean. Si elveInit_1 <= elveInit_2 y
                     * elveEnd_1 >= elveEnd_2
                     */

                    if ((elveInit_1 <= elveInit_2 && elveEnd_1 >= elveEnd_2)
                            || elveInit_2 <= elveInit_1 && elveEnd_2 >= elveEnd_1) {
                        overlapped++;
                    }

                    linea = myReader.readLine();

                }
            }

            System.out.println(overlapped);

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}