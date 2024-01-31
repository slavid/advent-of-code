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
    static int steps = 0;
    static List<Integer> instruccionesNum = new ArrayList<Integer>();
    static Map<String,List<String>> direcciones = new HashMap<>();
           
     public static void main(String[] args) {
        try {
            direcciones = readValues("input.txt");
            instruccionesNum = convertInstructionsToNumbers(instrucciones);
            String salto = "AAA";
            int posicion = 0;
            while(!arrived){
                salto = jumpTo(salto, instruccionesNum.get(posicion));
                if(salto.equals("ZZZ")){
                    arrived=true;
                }
                posicion++;
                if(posicion>=instruccionesNum.size()){
                    posicion=0;
                }
                steps++;
            }
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
        return dirSalto;
    }
}
