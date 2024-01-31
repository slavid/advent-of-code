import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static Map<Integer,Map<List<Integer>,Integer>> highCards = new HashMap<>();
    //static List<List<Integer>> highCardsNumbers = new ArrayList<>();
    static Map<Integer,Map<List<Integer>,Integer>> onePair = new HashMap<>();
    //static List<List<Integer>> onePairNumbers = new ArrayList<>();
    static Map<Integer,Map<List<Integer>,Integer>> twoPair = new HashMap<>();
    //static List<List<Integer>> twoPairNumbers = new ArrayList<>();
    static Map<Integer,Map<List<Integer>,Integer>> threeKind = new HashMap<>();
    //static List<List<Integer>> threeKindNumbers = new ArrayList<>();
    static Map<Integer,Map<List<Integer>,Integer>> fullHouse = new HashMap<>();
   // static List<List<Integer>> fullHouseNumbers = new ArrayList<>();
    static Map<Integer,Map<List<Integer>,Integer>> fourKind = new HashMap<>();
    //static List<List<Integer>> fourKindNumbers = new ArrayList<>();
    static Map<Integer,Map<List<Integer>,Integer>> fiveKind = new HashMap<>();
    //static List<List<Integer>> fiveKindNumbers = new ArrayList<>();
    static int A = 14;
    static int K = 13;
    static int Q = 12;
    static int J = 11;
    static int T = 10;

    static int multiplier = 1;
    static long sumatorio = 0;
    
    public static void main(String[] args) {


        try {
            Map<Integer,Map<String,Integer>> values = readValues("input.txt");
            
            calculateType(values);

            fiveKind = ordenarMapa(fiveKind);
            highCards = ordenarMapa(highCards);
            fourKind = ordenarMapa(fourKind);
            fullHouse = ordenarMapa(fullHouse);
            threeKind = ordenarMapa(threeKind);
            twoPair = ordenarMapa(twoPair);
            onePair = ordenarMapa(onePair);

            getScore(highCards);
            getScore(onePair);
            getScore(twoPair);
            getScore(threeKind);
            getScore(fullHouse);
            getScore(fourKind);
            getScore(fiveKind);

            System.out.println("Resultado: "+sumatorio);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   

    static Map<Integer,Map<String,Integer>> readValues(String fileName) throws IOException {
        Map<Integer,Map<String,Integer>> valuesReturn = new HashMap<>();
        Map<String,Integer> values = new HashMap<>();
        int contador = 0;
        try (Scanner scanner = new Scanner(new File(fileName))){   
            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                String[] line = next.split(" ");                    
                //System.out.println("line[0]: "+line[0]+" line[1]: "+line[1]);
                values.put(line[0], Integer.parseInt(line[1]));
                valuesReturn.put(contador, values);
                values = new HashMap<>();
                contador++;
            }
        }
        return valuesReturn;
    }
    /**
     * Cada linea del input es evaluada: se sacan los valores del las cartas y el bid
     * @param values
     */
    static void calculateType(Map<Integer,Map<String,Integer>> values){
        //List<Integer> numbers = new ArrayList<>();
        for (Map.Entry<Integer,Map<String,Integer>> mapa : values.entrySet()) {
            Map<String,Integer> map = mapa.getValue();
            Collection<String> cards = map.keySet();
            extractCardNumbers(cards,map);
        }

    }

    static List<Integer> extractCardNumbers(Collection<String> cards, Map<String,Integer> map){
        String carta = cards.toString();
        char[] caracteres = carta.toCharArray();
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i < caracteres.length-1; i++) {
            if(caracteres[i]=='A'){
                numbers.add(A);
            }else if(caracteres[i]=='K'){
                numbers.add(K);
            }else if(caracteres[i]=='Q'){
                numbers.add(Q);
            }else if(caracteres[i]=='J'){
                numbers.add(J);
            }else if(caracteres[i]=='T'){
                numbers.add(T);
            }else{
                numbers.add((Character.getNumericValue(caracteres[i])));
            }
        }

        List<Integer> numbersCopy = new ArrayList<Integer>(numbers);
        Collections.sort(numbers);
        List<Integer> unicos = numbers.stream().distinct().collect(Collectors.toList());
        
        String handType = getHandType(numbers, unicos);
        /**
         * Introducir en fiveKind, a parte de la posicion, la lista de numeros y el valor del bid
         */

         Collection<Integer> bids = map.values();
         List<Integer> bid = bids.stream().collect(Collectors.toList());
         //System.out.println("Bids: "+bid.get(0));
         Map<List<Integer>, Integer> toAdd = new HashMap<>();
         toAdd.put(numbersCopy, bid.get(0));
        
        if(handType.equals("fiveKind")){
            fiveKind.put(fiveKind.size(), toAdd);
            //fiveKindNumbers.add(numbersCopy);
        }else if(handType.equals("highCards")){
            highCards.put(highCards.size(), toAdd);
            //highCardsNumbers.add(numbersCopy);
        }else if(handType.equals("fourKind")){
            fourKind.put(fourKind.size(), toAdd);
            //fourKindNumbers.add(numbersCopy);
        }else if(handType.equals("fullHouse")){
            fullHouse.put(fullHouse.size(), toAdd);
            //fullHouseNumbers.add(numbersCopy);
        }else if(handType.equals("threeKind")){
            threeKind.put(threeKind.size(), toAdd);
            //threeKindNumbers.add(numbersCopy);
        }else if(handType.equals("twoPair")){
            twoPair.put(twoPair.size(), toAdd);
            //twoPairNumbers.add(numbersCopy);
        }else if(handType.equals("onePair")){
            onePair.put(onePair.size(), toAdd);
            //onePairNumbers.add(numbersCopy);
        }
        
        return numbersCopy;
    }

    static String getHandType(List<Integer> todos, List<Integer> unicos){
        if(unicos.size()==1){
            return "fiveKind";
        }else if(unicos.size()==todos.size()){
            return "highCards";
        }else if(unicos.size()==2){
            // full house(3+2), four of a kind(4+1)
            //System.out.println(unicos.toString());
            int contador1 = 0;
            int contador2 = 0;

            for(int i=0;i<unicos.size();i++){
                int iterador = unicos.get(i);
                //System.out.println("Iterador: "+iterador);
                for(int j=0;j<todos.size();j++){
                    if(iterador==todos.get(j)){
                        //System.out.println("Valor todos: "+todos.get(j));
                        if(i==0){
                            //System.out.println("contador1 aumenta");
                            contador1++;
                        }else{
                            contador2++;
                            //System.out.println("contador2 aumenta");
                        }
                    }
                }
            }
            if(contador1==4 || contador2==4){
                return "fourKind";
            }else{
                return "fullHouse";
            }
        }
        else if(unicos.size()==3){
            // dobles parejas(2+2+1), trio(3+1+1)
            //System.out.println("Size 3: "+unicos.toString());

            int contador1 = 0;
            int contador2 = 0;
            int contador3 = 0;

            for(int i=0;i<unicos.size();i++){
                int iterador = unicos.get(i);
                //System.out.println("Iterador: "+iterador);
                for(int j=0;j<todos.size();j++){
                    if(iterador==todos.get(j)){
                        //System.out.println("Valor todos: "+todos.get(j));
                        if(i==0){
                            //System.out.println("contador1 aumenta");
                            contador1++;
                        }else if(i==1){
                            contador2++;
                            //System.out.println("contador2 aumenta");
                        }else{
                            //System.out.println("contador3 aumenta");
                            contador3++;
                        }
                    }
                }
            }
            if(contador1==3 || contador2==3 || contador3==3){
                return "threeKind";
            }else{
                return "twoPair";
            }
        }else if(unicos.size()==4){
            // pareja (2+1+1+1)
            return "onePair";
        }
        return "";
    }

    static Map<Integer, Map<List<Integer>, Integer>> ordenarMapa(
            Map<Integer, Map<List<Integer>, Integer>> inputMap) {
        List<Map.Entry<Integer, Map<List<Integer>, Integer>>> entryList = new ArrayList<>(inputMap.entrySet());
        entryList.sort(Comparator.comparing(entry -> entry.getValue().keySet().iterator().next(),
                                            Main::compararListas));

        Map<Integer, Map<List<Integer>, Integer>> mapaOrdenado = new LinkedHashMap<>();
        int contador = 0;
        for (Map.Entry<Integer, Map<List<Integer>, Integer>> entry : entryList) {
            //mapaOrdenado.put(entry.getKey(), entry.getValue());
            mapaOrdenado.put(contador, entry.getValue());
            contador++;
        }
        return mapaOrdenado;
    }

    static int compararListas(List<Integer> lista1, List<Integer> lista2) {
        for (int i = 0; i < lista1.size(); i++) {
            int comparacion = Integer.compare(lista1.get(i), lista2.get(i));
            if (comparacion != 0) {
                return comparacion;
            }
        }
        return 0;
    }

    static public void getScore(Map<Integer,Map<List<Integer>,Integer>> listaCartas){
        for(Map.Entry<Integer, Map<List<Integer>, Integer>> mapa : listaCartas.entrySet()){
            Map<List<Integer>,Integer> valores = mapa.getValue();
            Collection<Integer> bids = valores.values();
            String bid = bids.toString().replace("[","").replace("]", "");
            int bidValue = Integer.parseInt(bid);

            sumatorio += bidValue*multiplier;
            multiplier++;
        }
    }
}
