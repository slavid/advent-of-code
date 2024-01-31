import java.io.*;
import java.util.*;

public class Day1_part2 {
    // calories: variable para ir sumando calorias por elfo
    static int calories = 0;
    // highestCalories: las calorias mas altas
    static int highestCalories = 0;
    // localGroupHighCalories: la suma de las calorias de un bloque de alimentos de
    // un elfo.
    static int localGroupHighCalories = 0;
    static String linea = "";
    static int elve = 1;
    static int highestElve = 0;
    static int top3Elves = 0;
    static ArrayList<Integer> listaCalorias = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        try {
            Reader inputText = new FileReader("input_day1.txt");
            BufferedReader myReader = new BufferedReader(inputText);
            linea = myReader.readLine();
            while (linea != null) {
                // Cuando llegamos a una linea vacia es porque hemos terminado el inventario de
                // un elfo y vamos a pasar al siguiente por lo que hay que comprobar si la suma
                // de sus calorias es mayor que highestCalories
                if (linea.isEmpty()) {
                    // System.out.println(calories);
                    // System.out.println("linea vacia");

                    // System.out.println("Elfo: " + elve + " lleva " + calories + " calorias");
                    elve++;
                    listaCalorias.add(calories);

                    if (calories > highestCalories) {
                        highestCalories = calories;
                        highestElve = elve;

                    }
                    // Como pasamos al siguiente elfo reseteamos el contador de calorias a 0
                    calories = 0;
                    // en caso de no ser una linea vacia, se realiza la suma de las calorias con el
                    // siguiente numero
                } else {
                    calories += Integer.parseInt(linea);
                }
                // Siguiente linea
                linea = myReader.readLine();
            }
            myReader.close();
            elve++;

            // Se hace un checkeo para el ultimo elfo ya que para el ultimo bloque no se
            // cumple del todo el loop al ser la siguiente linea=null
            listaCalorias.add(calories);
            if (calories > highestCalories) {
                highestCalories = calories;
                highestElve = elve;

            }
            // System.out.println("Lista sin ordenar");
            // for (int i : listaCalorias) {
            // System.out.println(i);
            // }

            // System.out.println(listaCalorias.size());
            Collections.sort(listaCalorias);
            Collections.reverse(listaCalorias);

            // System.out.println("Lista ordenada");
            // for (int i : listaCalorias) {
            // System.out.println(i);
            // }

            top3Elves = listaCalorias.get(0) + listaCalorias.get(1) + listaCalorias.get(2);

            // System.out.println("Calorias mas altas: " + highestCalories + " por parte del
            // elfo " + highestElve);
            System.out.println("Total top 3 elfos: " + top3Elves);

        } catch (

        FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
