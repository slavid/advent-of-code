import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    /**
     * Recorrer el input y guardarlo en un char[][]
     * Encontrar S.
     * Encontrar salida y meta
     * Sacar el circuito y almacenarlo en un array de char
     * distancia = tamaño/2
     * 
     * | array[i-1][j] a array[i+1][j] o inverso
     * - array[i][j-1] a array[i][j+1] o inverso
     * L array[i-1][j] a array[i][j+1] o inverso
     * J array[i-1][j] a array[i][j-1] o inverso
     * 7 array[i-1][j-1] a array[i+1][j] o inverso
     * F array[i+1][j] a array[i][j+1] o inverso
     */

    static char[][] array;
    static int arrayLength;

    static char[] west_east = {'-', 'J', '7'};
    static char[] east_west = {'-', 'L', 'F'};
    static char[] north_south = {'|', 'L', 'J'};
    static char[] south_north = {'|', '7', 'F'};

    static int current_i = 0;
    static int current_j = 0;

    static List<Character> camino = new ArrayList<Character>();
    static Map<String, Map<String, Integer[]>> firstDirections;

    static List<String[]> destinos_v_pipe = new ArrayList<String[]>();
    static List<String[]> destinos_h_pipe = new ArrayList<String[]>();
    static List<String[]> destinos_L_pipe = new ArrayList<String[]>();
    static List<String[]> destinos_J_pipe = new ArrayList<String[]>();
    static List<String[]> destinos_seven_pipe = new ArrayList<String[]>();
    static List<String[]> destinos_F_pipe = new ArrayList<String[]>();

    static Map<String,Map<String, String>> destinosCharacters = new HashMap<String,Map<String, String>>();
    

    public static void main(String[] args){
        try {
            String filename = "input.txt";
            arrayLength = getInputSize(filename);
            array = new char[arrayLength][arrayLength];
            array = readValues(filename);
            initializeDestinations();
            for (char[] c : array) {
                for (char caracter : c) {
                    System.out.print(Character.toString(caracter));
                }
                System.out.println();
            }

            int[] startingPosition = getStartingPosition(array);
            System.out.println(startingPosition[0]+", "+startingPosition[1]);

            firstDirections = getFirstMoves(array, startingPosition);

            /**
             *   Tenemos la direccion del primer salto en la key de firstDirections. Teniendo direccion de salto y posicion actual deberiamos poder obtener el siguiente caracter
             * y cambiar la posicion actual y saber la siguiente direccion con translate
             * 
            */


            char nextChar = ' ';
            String nextDirection = firstDirections.keySet().iterator().next();
            //String nextDirection = "toSouth";
            //Integer[] next_coordinates = firstDirections.values().iterator().next().get(0);
            while(nextChar != 'S'){
                //System.out.println("Next Char: "+nextChar);
                nextChar = getCharNextMove(nextDirection, current_i, current_j);
                nextDirection = translateToNextDirection(nextDirection, current_i, current_j);
                addToPath(nextChar);
            }

            /**
             * La logica del programa es:
             * 1 - Sacar la starting position, set los valores current_i y current_j y añadir el caracter al camino.
             * 2 - Para cada uno de los posibles caminos (2) foreach a getCharNextMove(direccion, int i, int j)
             * 3 - Conseguir el char del siguiente movimiento y llamar a translateToNextDirection(prevDirection, next_i, next_j)
             * 4 - Añadir ese char a camino
             * 5 - Repetir hasta que char = 'S'
             */

            // Probamos con 1 ruta:
            System.out.println(camino);
            System.out.println("Camino: "+camino.size()/2);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int getInputSize(String fileName) throws IOException{
        int length = 0;
        try (Scanner scanner = new Scanner(new File(fileName))){
            
            String next = scanner.nextLine();
            char[] characters = next.toCharArray();
            length = characters.length;
        }
        return length;
    }

    static char[][] readValues(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(new File(fileName))){   
            String next;
            int linea = 0;
            while (scanner.hasNextLine()) {
                next = scanner.nextLine();
                char[] characters = next.toCharArray();
                int contador = 0;
                for (char c : characters) {
                    array[linea][contador]=c;
                    contador++;
                }
                linea++;   
            }
        }
        return array;
    }

    static int[] getStartingPosition(char[][] searchArray){
        int i = 0;
        int j = 0;
        int[] resultado = new int[2];
        for (char[] c : searchArray) {
                for (char caracter : c) {
                    if(caracter=='S'){
                        resultado[0]=i;
                        resultado[1]=j;
                        current_i=i;
                        current_j=j;
                        //addToPath(getPositionChar(i, j));
                        return resultado;
                    }
                    j++;
                }
            i++;
            j=0;
            }
        return resultado;
    }

    static Map<String, Map<String, Integer[]>> getFirstMoves(char[][] searchArray, int[]startPos){
        int start_i=startPos[0];
        int start_j=startPos[1];
        Integer[] nextPos= new Integer[2];
        char[] adjacents = new char[4];
        Map<String, Map<String, Integer[]>> firstDirection = new HashMap<String, Map<String, Integer[]>>();
        Map<String, Integer[]> toAdd;
        /*
         * 0 -East
         * 1 -North
         * 2 -West
         * 3 -South
         */

        // East
        if(start_j+1 <= arrayLength){
            adjacents[0] = searchArray[start_i][start_j+1];
            for(int i=0;i<west_east.length;i++){
                if(west_east[i]==adjacents[0]){
                    toAdd = new HashMap<String, Integer[]>();
                    nextPos= new Integer[2];
                    nextPos[0]=start_i;
                    nextPos[1]=start_j+1;
                    toAdd.put(Character.toString(adjacents[0]), nextPos);
                    firstDirection.put("toEast", toAdd);
                    
                }
            }
        }
        // North
        if(start_i-1>=0){
            adjacents[1] = searchArray[start_i-1][start_j];
            for(int i=0;i<south_north.length;i++){
                if(south_north[i]==adjacents[1]){
                    toAdd = new HashMap<String, Integer[]>();
                    nextPos= new Integer[2];
                    nextPos[0]=start_i-1;
                    nextPos[1]=start_j;
                    toAdd.put(Character.toString(adjacents[1]), nextPos);
                    firstDirection.put("toNorth", toAdd);
                    
                }
            }
        }
            
        // West
        if(start_j-1>=0){
            adjacents[2] = searchArray[start_i][start_j-1];
            for(int i=0;i<east_west.length;i++){
                if(east_west[i]==adjacents[2]){
                    toAdd = new HashMap<String, Integer[]>();
                    nextPos= new Integer[2];
                    nextPos[0]=start_i;
                    nextPos[1]=start_j-1;
                    toAdd.put(Character.toString(adjacents[2]), nextPos);
                    firstDirection.put("toWest", toAdd);
                    
                }
            }
        }
            
        // South
        if(start_i+1 <= arrayLength){
            adjacents[3] = searchArray[start_i+1][start_j];
            for(int i=0;i<east_west.length;i++){
                if(north_south[i]==adjacents[3]){
                    toAdd = new HashMap<String, Integer[]>();
                    nextPos= new Integer[2];
                    nextPos[0]=start_i+1;
                    nextPos[1]=start_j;
                    toAdd.put(Character.toString(adjacents[3]), nextPos);
                    firstDirection.put("toSouth", toAdd);
                    
                }
            }
        }
            
        // for(int i=0; i<4; i++){
        //     System.out.print(Character.toString(adjacents[i]));
        // }

        return firstDirection;
    }

    static char getCharNextMove(String direccion, int pos_i, int pos_j){
        if(direccion.equals("toEast")){
            int[] position = setCurrentPosition(pos_i, pos_j+1);
            return getPositionChar(position[0], position[1]);
        }
        if(direccion.equals("toNorth")){
            //setCurrentPosition(pos_i+1, pos_j);
            int[] position = setCurrentPosition(pos_i-1, pos_j);
            return getPositionChar(position[0], position[1]);
        }
        if(direccion.equals("toWest")){
            //setCurrentPosition(pos_i, pos_j-1);
            int[] position = setCurrentPosition(pos_i, pos_j-1);
            return getPositionChar(position[0], position[1]);
        }
        if(direccion.equals("toSouth")){
            //setCurrentPosition(pos_i+1, pos_j);
            int[] position = setCurrentPosition(pos_i+1, pos_j);
            return getPositionChar(position[0], position[1]);
        }

        return 0;
    }

    static char getPositionChar(int i, int j){
        return array[i][j];
    }

    static int[] setCurrentPosition(int pos_i, int pos_j){
        int[] retornar = new int[2];
        current_i=pos_i;
        current_j=pos_j;
        retornar[0]=pos_i;
        retornar[1]=pos_j;
        return retornar;
    }

    static void addToPath(char c){
        camino.add(c);
    }

    static String translateToNextDirection(String prevDirection, int next_i, int next_j){
        char nextChar = getPositionChar(next_i, next_j);
        String nextDirection = "";
        // Horizontal, J y 7
        if(prevDirection.equals("toEast")){
            if(nextChar=='-'){
                nextDirection = destinosCharacters.get("destinos_H_pipe").get(prevDirection);
            }
            if(nextChar=='J'){
                nextDirection = destinosCharacters.get("destinos_J_pipe").get(prevDirection);
            }
            if(nextChar=='7'){
                nextDirection = destinosCharacters.get("destinos_7_pipe").get(prevDirection);
            }
        }
        if(prevDirection.equals("toNorth")){
            // Vertical, 7, F
            if(nextChar=='|'){
                nextDirection = destinosCharacters.get("destinos_V_pipe").get(prevDirection);
            }
            if(nextChar=='7'){
                nextDirection = destinosCharacters.get("destinos_7_pipe").get(prevDirection);
            }
            if(nextChar=='F'){
                nextDirection = destinosCharacters.get("destinos_F_pipe").get(prevDirection);
            }
        }
        if(prevDirection.equals("toWest")){
            // Horizontal, L, F
            if(nextChar=='-'){
                nextDirection = destinosCharacters.get("destinos_H_pipe").get(prevDirection);
            }
            if(nextChar=='L'){
                nextDirection = destinosCharacters.get("destinos_L_pipe").get(prevDirection);
            }
            if(nextChar=='F'){
                nextDirection = destinosCharacters.get("destinos_F_pipe").get(prevDirection);
            }
        }
        
        if(prevDirection.equals("toSouth")){
            // Vertical, L, J
            if(nextChar=='|'){
                nextDirection = destinosCharacters.get("destinos_V_pipe").get(prevDirection);
            }
            if(nextChar=='L'){
                nextDirection = destinosCharacters.get("destinos_L_pipe").get(prevDirection);
            }
            if(nextChar=='J'){
                nextDirection = destinosCharacters.get("destinos_J_pipe").get(prevDirection);
            }
        }

        return nextDirection;
    }

    static void initializeDestinations(){
        // V pipe
        Map<String, String> toAdd = new HashMap<String, String>();
        toAdd.put("toNorth", "toNorth");
        toAdd.put("toSouth", "toSouth");
        destinosCharacters.put("destinos_V_pipe", toAdd);

        // H pipe
        toAdd = new HashMap<String, String>();
        toAdd.put("toEast", "toEast");
        toAdd.put("toWest", "toWest");
        destinosCharacters.put("destinos_H_pipe", toAdd);

        // L pipe
        toAdd = new HashMap<String, String>();
        toAdd.put("toSouth", "toEast");
        toAdd.put("toWest", "toNorth");
        destinosCharacters.put("destinos_L_pipe", toAdd);

        // J pipe
        toAdd = new HashMap<String, String>();
        toAdd.put("toSouth", "toWest");
        toAdd.put("toEast", "toNorth");
        destinosCharacters.put("destinos_J_pipe", toAdd);

        // 7 pipe
        toAdd = new HashMap<String, String>();
        toAdd.put("toNorth", "toWest");
        toAdd.put("toEast", "toSouth");
        destinosCharacters.put("destinos_7_pipe", toAdd);

        // F pipe
        toAdd = new HashMap<String, String>();
        toAdd.put("toNorth", "toEast");
        toAdd.put("toWest", "toSouth");
        destinosCharacters.put("destinos_F_pipe", toAdd);
    }

    public int[] getNextPosCoordinates(String direccion, int pos_i, int pos_j){
        // Sacamos el siguiente caracter, lo metemos en path y actualizamos current_i y current_j que ademas
        // se devuelven como resultado.
        char next = getCharNextMove(direccion, pos_i, pos_j);
        addToPath(next);
        int[] toReturn = new int[2];
        toReturn[0]=current_i;
        toReturn[1]=current_j;
        return toReturn;
    }
}
