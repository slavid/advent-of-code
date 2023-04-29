import java.io.*;
import java.util.*;

public class Day6_part1 {

    public static String lineaTmp = "";
    public static String linea = "";
    public static char[] characters;
    public static int consecutives = 4;
    public static int contador = 0;
    public static int posicion = 0;
    public static boolean contenido = false;

    public static LinkedList<String> tempBuffer = new LinkedList<String>();

    public static void main(String[] args) throws IOException {

        try {
            Reader inputText = new FileReader("input.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            lineaTmp = myReader.readLine();
            while (lineaTmp != null) {
                if (!lineaTmp.isEmpty()) {
                    // System.out.println(linea);
                    linea = lineaTmp;
                    lineaTmp = myReader.readLine();

                }

            }

            // System.out.println(linea);

            myReader.close();

            System.out.println(returnIndex());

            // System.out.println("Borramos primer caracter:");
            // tempBuffer.remove();
            // System.out.println(tempBuffer);

        } catch (

        FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int returnIndex() {
        characters = linea.toCharArray();
        // System.out.println(characters[0]);

        // Aqui ya tenemos todos los caracteres en characters[]

        for (int i = 0; i < consecutives; i++) {
            tempBuffer.add(Character.toString(characters[i]));
            // System.out.println(tempBuffer);
            contador++;
        }
        System.out.println("Mostramos tempBuffer antes del loop " + tempBuffer.toString());

        while (contador < characters.length) {
            // tempBuffer.size();
            sameCharChecker: for (int i = 0; i < tempBuffer.size(); i++) {
                // System.out.println("Comparamos i: " + tempBuffer.get(i));
                for (int j = tempBuffer.size() - 1; j > i; j--) {
                    // System.out.println("Comparamos j: " + tempBuffer.get(j));
                    if (tempBuffer.get(i).equals(tempBuffer.get(j))) {
                        contenido = true;
                        tempBuffer.remove();
                        // System.out.println("Iteracion numero: " + contador);
                        // System.out.print("Boolean Contenido = ");
                        // System.out.println(contenido);
                        // System.out.println("Mostramos tempBuffer despues de borrar head: " +
                        // tempBuffer.toString());
                        break sameCharChecker;
                    }
                }
            }

            if (!contenido) {
                // System.out.println(contador);
                System.out.println("Mostramos tempBuffer al retornar contador " + tempBuffer.toString());
                return contador;
            }

            contenido = false;

            tempBuffer.add(Character.toString(characters[contador]));
            // System.out.println("Mostramos tempBuffer al aniadir siguiente elemento: " +
            // tempBuffer.toString());
            contador++;
        }

        if (!contenido) {
            System.out.println(contador);

        }
        return 0;
    }

}
