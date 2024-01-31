import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    public static int current_game;
    public static int valor_asignar;
    public static int sum;

    public static List<Integer> valores_red = new ArrayList<Integer>();
    public static List<Integer> valores_green = new ArrayList<Integer>();
    public static List<Integer> valores_blue = new ArrayList<Integer>();

    public static Map<Integer,Integer> juego_max_red = new HashMap<Integer,Integer>();
    public static Map<Integer,Integer> juego_max_green = new HashMap<Integer,Integer>();
    public static Map<Integer,Integer> juego_max_blue = new HashMap<Integer,Integer>();

    public static List<Integer> sumatorio = new ArrayList<Integer>();


    /** Por cada linea capturar:
     * - El nº del juego
     * - Grupos antes del punto y coma
     * - Por cada grupo leer el color y asignarle el numero
     * - Comparar si ese numero es mayor que el limite
     * - Si es mayor ignorar la linea
     * - Si es menor, seguir al siguiente numero
     * - Si ninguno es mayor entonces añadir el nº del juego al sumatorio.
     */

     public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(new File("input.txt"));
        
        //Pattern empty_delimiter = scanner.delimiter();
        

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equals("Game")){
                next = scanner.next();
                String game_text = next;
                //System.out.println(game_text);

                game_text = game_text.replaceAll(":", ""); // regex
                current_game = Integer.parseInt(game_text);

                juego_max_red.put(current_game,0);
                juego_max_green.put(current_game,0);
                juego_max_blue.put(current_game,0);


            }else if(!next.matches("^\\d+$")){
                String next_color = next;
                if(next_color.equals("red,") || next_color.equals("red;") || next_color.equals("red")){
                    if(juego_max_red.get(current_game)<valor_asignar)
                        juego_max_red.put(current_game, valor_asignar);

                    //System.out.println("Color rojo: " + valor_red);
                }else if(next_color.equals("green,") || next_color.equals("green;") || next_color.equals("green")){
                    if(juego_max_green.get(current_game)<valor_asignar)
                        juego_max_green.put(current_game, valor_asignar);

                    //System.out.println("Color verde: " + valor_green);
                }else if(next_color.equals("blue,") || next_color.equals("blue;") || next_color.equals("blue")){
                    if(juego_max_blue.get(current_game)<valor_asignar)
                        juego_max_blue.put(current_game, valor_asignar);

                    //System.out.println("Color azul: " + valor_blue);
                }


            }else if(next.matches("^\\d+$")){
                
                valor_asignar=Integer.parseInt(next);

            }




        } // Fin while
        scanner.close();

        // Sumatorio

        for(int i=1; i<=current_game;i++){
            int valor_red = juego_max_red.get(i);
            int valor_green = juego_max_green.get(i);
            int valor_blue = juego_max_blue.get(i);
            sumatorio.add(valor_red*valor_green*valor_blue);
            sum += sumatorio.getLast();
        }

        System.out.println("Total: "+sum);
        // System.out.println(juego_max_red.toString());
        // System.out.println(juego_max_green.toString());
        // System.out.println(juego_max_blue.toString());

    }
}
