import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    /**
     * En la primera linea del input tenemos RL's (R sera 1 y L sera 0). Crear un array de integers 0 y 1. Si hemos llegado al final y aun no estamos en el destino (arrived=false)
     * entonces se vuelve a iterar pero continuando la posicion de donde se esta en el mapa.
     * 
     * Se parsea los datos en un Mapa <String,Mapa<String, String>> la clave sera lo que esta antes del igual (posicion en la que estamos o a la que ir) y el Mapa<String, String> la posicion 0 y 1 respectivamente.
     * En cada salto comprobaremos si la clave en la que estamos es igual a ZZZ (destino). Si es asi, entonces arrived=true, salimos del bucle y devolvemos el contador de saltos.
     * Si no estamos en ZZZ, entonces leemos la siguiente posicion de instrucciones. 
     * - Si hemos llegado al final de instrucciones, se vuelve a comenzar de 0 (mover el iter a la posicion inicial).
     */

    static String instrucciones; // RLRRLLLR
    static boolean arrived = false;
    static boolean allArrived = false;
    static long steps = 0;
    static long numCiclos = 0;
    static List<Integer> instruccionesNum = new ArrayList<Integer>();
    static Map<String, Boolean> startingKeys = new HashMap<String, Boolean>();
    //static List<Boolean> listOfBooleans;
    static Map<String, Boolean> newList = new HashMap<String, Boolean>();
    static Map<String,List<String>> direcciones = new HashMap<>();
           
     public static void main(String[] args) {
        try {
            direcciones = readValues("input.txt");
            System.out.println(startingKeys);
            instruccionesNum = convertInstructionsToNumbers(instrucciones);
            
            newList = getToDestination(startingKeys);
            numCiclos++;
            //System.out.println(newList);
            allArrived = areAllArrived(newList);

            while(!allArrived){
                newList = getToDestination(newList);
                numCiclos++;
                allArrived = areAllArrived(newList);
            }


            //System.out.println(areArrived());

            /**
             * Para cada uno de los startingKeys ejecutar jumpTo() 
             * Si el salto finaliza en Z entonces a ese startingKey ponerle el valor true.
             * Fuera del bucle comprobar si areArrived() si verdadero entonces devolver steps
             * Si falso entonces reemplazar startingKey con el nuevo valor (sustituirStartingKey(old, new)) 
             * y ejecutar jumpTo() con ese nuevo valor
             * Cuando ponemos posicion=0 hay que llamar a setAllToFalse()
             */
            steps = numCiclos*instruccionesNum.size();
            System.out.println("Pasos: "+steps);

        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }

    static Map<String,List<String>> readValues(String fileName) throws IOException {
        try (Scanner scanner = new Scanner(new File(fileName))){   
            String next = scanner.nextLine();
            instrucciones = next;
            //System.out.println(instrucciones);
            while (scanner.hasNextLine()) {
                next = scanner.nextLine();
                if(next.isEmpty()){
                    next = scanner.nextLine();
                }
                String[] line = next.split(" ");
                line[2] = line[2].replace("(", "").replace(",", "");
                line[3] = line[3].replace(")", "");
                List<String> toAdd = new ArrayList<>();
                toAdd.add(line[2]);
                toAdd.add(line[3]);
                direcciones.put(line[0], toAdd);
                if(line[0].endsWith("A")){
                    //System.out.println("Añadimos: "+line[0]);
                    startingKeys.put(line[0], false);
                }
            }
        }
        return direcciones;
    }

    static List<Integer> convertInstructionsToNumbers(String instrucciones){
        for (char caracter : instrucciones.toCharArray()) {
            if(caracter=='R'){
                instruccionesNum.add(1);
            }else{
                instruccionesNum.add(0);
            }
        }
        return instruccionesNum;
    }

    static String jumpTo(String key, int direccion){
        List<String> saltos = direcciones.get(key);
        String dirSalto = saltos.get(direccion);
        //System.out.println("Saltamos a : "+dirSalto);
        return dirSalto;
    }

    static Map<String, Boolean> getToDestination(Map<String, Boolean> listaSaltos){
        //String salto = "22A";
        Map<String, Boolean> newDst = new HashMap<String, Boolean>();
        String dest = "";
        for (String salto : listaSaltos.keySet()) {
            dest = salto;
            for(int i=0; i< instruccionesNum.size();i++){
                dest = jumpTo(dest, instruccionesNum.get(i));
                
            }
            
            //steps++;
            //System.out.println(steps);

            if(dest.endsWith("Z")){
                newDst.put(dest, true);
            }else{
                newDst.put(dest, false);
            }
        }
        return newDst;
    }

    static boolean areAllArrived(Map<String, Boolean> listOfBooleans){
        int cuentaTrues=0;
        //System.out.println("El tamaño de los booleans es: "+listOfBooleans.size());
        for (Boolean check : listOfBooleans.values()) {
            //System.out.println(start.getValue());
            if(check){
                //System.out.println(start.getValue());
                //System.out.println("Sumamos 1 true");
                cuentaTrues++;
            }
        }
        //System.out.println("El numero de trues es: "+cuentaTrues);
        if(cuentaTrues==listOfBooleans.size()){
            //System.out.println("Todos trues");
            return true;
        }else{
            //System.out.println("Todos false");
            return false;
        }
    }
}