import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    public static int current_card;
    public static boolean cambio = false;
    public static int valor_asignar;
    public static Map<Integer,List<Integer>> current_numbers = new HashMap<Integer,List<Integer>>();
    public static Map<Integer,List<Integer>> winning_numbers = new HashMap<Integer,List<Integer>>();

    public static Map<Integer,Integer> num_copias = new HashMap<Integer,Integer>();

    public static List<Integer> current_numbers_array = new ArrayList<Integer>();
    public static List<Integer> winning_numbers_array = new ArrayList<Integer>();

    public static int contador_indice=0;
    public static double contador_global=0;

    public static int scratchcards=0;
    public static int multiplier=1;

    /** Por cada linea capturar:
     * 
     */

     public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(new File("input.txt"));
        
        //Pattern empty_delimiter = scanner.delimiter();


        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equals("Card")){



                next = scanner.next();
                String game_text = next;
                //System.out.println(game_text);

                game_text = game_text.replaceAll(":", ""); // regex


                current_card = Integer.parseInt(game_text);
                //System.out.println(current_card);
                //System.out.println(next);
                cambio=false;

                current_numbers_array = new ArrayList<Integer>();
                winning_numbers_array = new ArrayList<Integer>();

                current_numbers.put(current_card, current_numbers_array);
                winning_numbers.put(current_card, winning_numbers_array);

                next = scanner.next();
            }

            if(next.equals("|")){
                cambio=true;
                next = scanner.next();
            }
            if(!cambio){
                winning_numbers_array.add(Integer.parseInt(next));
                //System.out.println("Card: "+current_card+", adding current number: "+Integer.parseInt(next));
            }else{
                current_numbers_array.add(Integer.parseInt(next));
                //System.out.println("Card: "+current_card+", adding winning number: "+Integer.parseInt(next));
            }   

        } // Fin while
        scanner.close();

        /**
         * Calcular copias: en contador_indice tenemos el numero de cartas siguientes que tendra valor doble.
         */

        //scratchcards = current_card;

        for(int i=1; i<current_numbers.size()+1;i++){
            num_copias.put(i, 1);
        }

        //System.out.println(num_copias.toString());

        // La i representa las 6 iteraciones
        for(int i=1; i<current_numbers.size()+1;i++){
            // La j representa el iterador de current numbers
            for(int j=0; j<current_numbers.get(i).size();j++){
                // La k representa el iterador de winning numbers
                for(int k=0; k<winning_numbers.get(i).size(); k++){
                    if(current_numbers.get(i).get(j).equals(winning_numbers.get(i).get(k))){
                        //System.out.println("Es current numbers de indice "+i+" posicion "+j+" igual a winning numbers de "+i+" posicion "+k);
                        contador_indice++;
                        
                    }
                }
                
                // Salimos de la comparacion de winning y vamos al siguiente de current
                
            }

            //System.out.println("Contador indice "+i+" Ocurrencias: "+contador_indice+" y "+num_copias.get(i)+" copias.");
            if(contador_indice>0){
                for(int x=1; x<=num_copias.get(i);x++){
                    
                    
                    // if(!num_copias.get(i+x).equals(null))
                    
                    
                    for(int z=1; z<=contador_indice;z++){
                        int valor_original = num_copias.get(i+z);
                        //int valor_original_copia = num_copias.get(i+z);
                        valor_original++;
                        //System.out.println("Estamos en el indice "+i+" que tiene "+contador_indice+" ocurrencias, por lo tanto en el indice "+(i+z)+" Añadimos una copia extra, por lo tanto pasa de tener: "+valor_original_copia+" a tener "+valor_original+" copias.");
                        num_copias.put(i+z, valor_original);
                        
                    }
                }

            }

            contador_indice=0;
        }


            //System.out.println("Contador global: "+contador_global);
            //multiplier = contador_indice;
            
            
            // Salimos current asi que isremos al siguiente indice de i
            //System.out.println(i);
    


        //System.out.println("current_numbers:");
        //System.out.println(current_numbers.toString());
        //System.out.println("winning_numbers:");
        //System.out.println(winning_numbers.toString());
        //System.out.println("Winning card 5 posicion 3: "+winning_numbers.get(5,3));
        //System.out.println(num_copias.toString());

        for(int i=1; i<num_copias.size()+1;i++){
            scratchcards += num_copias.get(i);
        }

        System.out.println(scratchcards);

    }
}