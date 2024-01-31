import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main{

    public static String steps = "";
    public static Map<Integer, List<Map<Integer, Map<String, Integer>>>> boxes = new HashMap <Integer, List<Map<Integer, Map<String, Integer>>>>();
    public static void main(String[] args) throws IOException{
        try (Scanner scanner = new Scanner(new File("input_small.txt"))){   
            String next;
            while (scanner.hasNextLine()) {
                next = scanner.nextLine();
                steps = next;   
            }
        }
        String[] step = steps.split(",");
        int resultado = 0;
        int contador = 0;

        //System.out.println("Is add?: "+isAdd(step[0]));
        //System.out.println("Is add?: "+isAdd(step[1]));

        //System.out.println(getValueToSubstract(step[1]));

        List<Map<Integer, Map<String, Integer>>> toAddBigMap; //= new ArrayList <Map<Integer, Map<String, Integer>>>();
        Map<Integer, Map<String, Integer>> toAddMapMedium;
        Map<String, Integer> toAddMapSmall; //= new HashMap<Integer, Map<String, Integer>>();

        toAddBigMap = new ArrayList <Map<Integer, Map<String, Integer>>>();

        for (String str : step) {
            toAddBigMap = new ArrayList <Map<Integer, Map<String, Integer>>>();
            toAddMapMedium = new HashMap <Integer, Map<String, Integer>>();
            if(isAdd(str)){
                // Añadimos
                toAddMapSmall = getToAddMapSmall(str);
                toAddMapMedium.put(toAddMapMedium.size(), toAddMapSmall);
                toAddBigMap.add(toAddMapMedium);
                
            }else{
                // Borramos
                Collection<List<Map<Integer, Map<String, Integer>>>> valoresBoxes = boxes.values();
                List<List<Map<Integer, Map<String, Integer>>>> listaValores = valoresBoxes.stream().toList();
                Map<String, Integer> toSearch = new HashMap<String, Integer>();
                listaValores.get(1).get(1).get(toSearch);
            }

            boxes.put(getHashValue(getFirstHalf(str)), toAddBigMap);
        }

        /* Box 0 */
        // toAddBigMap = new ArrayList <Map<Integer, Map<String, Integer>>>();
        // toAddMapMedium = new HashMap <Integer, Map<String, Integer>>();
        // toAddMapSmall = new HashMap<String, Integer>();


        // toAddMapSmall.put("rn", 1);
        // toAddMapMedium.put(0, toAddMapSmall);
        // toAddBigMap.add(toAddMapMedium);

        // //toAddBigMap = new ArrayList <Map<Integer, Map<String, Integer>>>();
        // toAddMapMedium = new HashMap <Integer, Map<String, Integer>>();
        // toAddMapSmall = new HashMap<String, Integer>();

        // toAddMapSmall.put("cm", 2);
        // toAddMapMedium.put(1, toAddMapSmall);
        // toAddBigMap.add(0, toAddMapMedium);

        // boxes.put(0, toAddBigMap);


        // /* Box 3 */
        // toAddBigMap = new ArrayList <Map<Integer, Map<String, Integer>>>();
        // toAddMapMedium = new HashMap <Integer, Map<String, Integer>>();
        // toAddMapSmall = new HashMap<String, Integer>();


        // toAddMapSmall.put("ot", 7);
        // toAddMapMedium.put(0, toAddMapSmall);
        // toAddBigMap.add(toAddMapMedium);

        // //toAddBigMap = new ArrayList <Map<Integer, Map<String, Integer>>>();
        // toAddMapMedium = new HashMap <Integer, Map<String, Integer>>();
        // toAddMapSmall = new HashMap<String, Integer>();

        // toAddMapSmall.put("ab", 5);
        // toAddMapMedium.put(1, toAddMapSmall);
        // toAddBigMap.add(0, toAddMapMedium);

        // toAddMapMedium = new HashMap <Integer, Map<String, Integer>>();
        // toAddMapSmall = new HashMap<String, Integer>();

        // toAddMapSmall.put("pc", 6);
        // toAddMapMedium.put(2, toAddMapSmall);
        // toAddBigMap.add(0, toAddMapMedium);


        // boxes.put(3, toAddBigMap);

        System.out.println(boxes);
        for (String string : step) {
            
            resultado += getHashValue(string);
        }

        System.out.println("Resultado: "+resultado);
    }

    public static int getHashValue(String stepParam){
        int returnNumber = 0;
        char[] stepArray = stepParam.toCharArray();
        for (char c : stepArray) {
            int value = (int) c;
            returnNumber += value;
            returnNumber *= 17;
            returnNumber = returnNumber % 256;
            
        }
        //System.out.println("String: "+stepParam+", valor: "+returnNumber);
        return returnNumber;
    }

    public static boolean isAdd(String str){
        if(str.contains("="))
            return true;
        return false;
    }

    public static Map<String, Integer> getToAddMapSmall(String str){
        Map<String, Integer> result = new HashMap<String, Integer>();
        String[] temp = str.split("=");
        String key = temp[0];
        Integer value = Integer.valueOf(temp[1]);
        result.put(key, value);
        return result;
    }

    public static String getFirstHalf(String str){
        String[] temp = str.split("=");
        return temp[0];
    }

    public static String getValueToSubstract(String str){
        String[] result = str.split("-");
        return result[0];
    }

}