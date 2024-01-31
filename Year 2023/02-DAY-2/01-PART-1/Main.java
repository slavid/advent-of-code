import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    public static int max_red = 12;
    public static int max_green = 13;
    public static int max_blue = 14;

    public static int current_game;
    public static int valor_asignar;

    public static int sumatorio;

    public static List<Integer> juegos_validos = new ArrayList<Integer>();
    public static List<Integer> juegos_invalidos = new ArrayList<Integer>();

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
                //System.out.println(current_game);
                if(!juegos_validos.contains(current_game)){
                    juegos_validos.add(current_game);
                }

            }else if(!next.matches("^\\d+$")){
                String next_color = next;
                if(next_color.equals("red,") || next_color.equals("red;") || next_color.equals("red")){
                    if(valor_asignar>max_red){
                        if(!juegos_invalidos.contains(current_game)){
                            juegos_invalidos.add(current_game);
                        }
                    }
                    //System.out.println("Color rojo: " + valor_red);
                }else if(next_color.equals("green,") || next_color.equals("green;") || next_color.equals("green")){
                    if(valor_asignar>max_green){
                        if(!juegos_invalidos.contains(current_game)){
                            juegos_invalidos.add(current_game);
                        }
                    }
                    //System.out.println("Color verde: " + valor_green);
                }else if(next_color.equals("blue,") || next_color.equals("blue;") || next_color.equals("blue")){
                    if(valor_asignar>max_blue){
                        if(!juegos_invalidos.contains(current_game)){
                            juegos_invalidos.add(current_game);
                        }
                    }
                    //System.out.println("Color azul: " + valor_blue);
                }


            }else if(next.matches("^\\d+$")){
                
                valor_asignar=Integer.parseInt(next);

            }




        } // Fin while
        scanner.close();
        
        for(int i=0; i<juegos_invalidos.size(); i++){
            int iter = juegos_invalidos.get(i);
            //System.out.println(iter);
            if(juegos_validos.contains(iter)){
                juegos_validos.remove(juegos_validos.indexOf(iter));
            }
        }
        // sumatorio
        for(int i=0; i<juegos_validos.size(); i++){
            sumatorio += juegos_validos.get(i);
        }
        System.out.println(sumatorio);;
        System.out.println("Validos: "+juegos_validos.toString());
        System.out.println("inValidos: "+juegos_invalidos.toString());
    }
}
