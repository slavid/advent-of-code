import java.io.File;
import java.io.IOException;
import java.util.*;


public class Main {

    public static List<Double> seeds = new ArrayList<Double>();
    public static List<Double> distances = new ArrayList<Double>();
    public static Map<Double,List<Double>> seed_to_soil = new HashMap<Double,List<Double>>();
    public static Map<Double,List<Double>> soil_to_fertilizer = new HashMap<Double,List<Double>>();
    public static Map<Double,List<Double>> fertilizer_to_water = new HashMap<Double,List<Double>>();
    public static Map<Double,List<Double>> water_to_light = new HashMap<Double,List<Double>>();
    public static Map<Double,List<Double>> light_to_temperature = new HashMap<Double,List<Double>>();
    public static Map<Double,List<Double>> temperature_to_humidity = new HashMap<Double,List<Double>>();
    public static Map<Double,List<Double>> humidity_to_location = new HashMap<Double,List<Double>>();

    public static List<Double> seed_to_soil_numbers = new ArrayList<Double>();

    public static int destination_range_start = 0;
    public static int source_range_start = 1;
    public static int range_lenght = 2;
    public static double seed;
    public static double lowest;

    public static Map<Double,Double> converted_seed_to_soil = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_soil_to_fertilizer = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_fertilizer_to_water = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_water_to_light = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_light_to_temperature = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_temperature_to_humidity = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_humidity_to_location = new HashMap<Double,Double>();

     public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(new File("input.txt"));
        

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equals("seeds:")){
                next = scanner.next();

                // Añadimos seeds
                while(next.matches("^\\d+$")){
                    seeds.add(Double.parseDouble(next));
                    
                    next = scanner.next();
                }
            }

            if (next.equals("seed-to-soil")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$")){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        next = scanner.next();
                    }
                    seed_to_soil.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Seed to soil map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }


            if (next.equals("soil-to-fertilizer")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$")){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        next = scanner.next();
                    }
                    soil_to_fertilizer.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }

            if (next.equals("fertilizer-to-water")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$")){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        next = scanner.next();
                    }
                    fertilizer_to_water.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }

            if (next.equals("water-to-light")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$")){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        next = scanner.next();
                    }
                    water_to_light.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }

            if (next.equals("light-to-temperature")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$")){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        next = scanner.next();
                    }
                    light_to_temperature.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }

            if (next.equals("temperature-to-humidity")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$")){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        next = scanner.next();
                    }
                    temperature_to_humidity.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }

            if (next.equals("humidity-to-location")){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$") && scanner.hasNext()){
                    for(int i=0;i<3;i++){
                        seed_to_soil_numbers.add(Double.parseDouble(next));
                        if(scanner.hasNext())
                            next = scanner.next();
                    }
                    humidity_to_location.put(iterator, seed_to_soil_numbers);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    seed_to_soil_numbers = new ArrayList<Double>();
                    iterator++;
                }
            }

        } // Fin while
        scanner.close();

        // System.out.println("Seeds: "+seeds.toString());
        // System.out.println("Seed to soil map: "+seed_to_soil.toString());
        // System.out.println("Soil to fertilizer map: "+soil_to_fertilizer.toString());
        // System.out.println("fertilizer to water map: "+fertilizer_to_water.toString());
        // System.out.println("water to light map: "+water_to_light.toString());
        // System.out.println("light to temperature map: "+light_to_temperature.toString());
        // System.out.println("temperature to humidity map: "+temperature_to_humidity.toString());
        // System.out.println("humidity to location map: "+humidity_to_location.toString());


        /**
         * Coger seed y ver en que rango entra:
         * - valor absoluto de la resta entre source y destination
         * - ver si el seed esta dentro de ese valor+rango
         * - - si lo esta entonces es esa posicion
         * - - si no lo esta entonces probar en la siguente linea de traduccion
         * - - si no aparece entonces el destino es el mismo que el origen
         */
        
        for(int i=0;i<seeds.size();i++){
            double destino=-1;
            seed = seeds.get(i);
            for(double j=0;j<seed_to_soil.size();j++){
                double range_start = seed_to_soil.get(j).get(source_range_start); // 98
                double dst_start = seed_to_soil.get(j).get(destination_range_start); // 50
                double range = seed_to_soil.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_seed_to_soil.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_seed_to_soil.put(seed, destino);
            }

            //System.out.println("Seed: "+seed+" destino: "+destino);
            
        }

        for(int i=0;i<converted_seed_to_soil.size();i++){
            double destino=-1;
            seed = seeds.get(i);
            seed = converted_seed_to_soil.get(seed);
            for(double j=0;j<soil_to_fertilizer.size();j++){
                double range_start = soil_to_fertilizer.get(j).get(source_range_start); // 98
                double dst_start = soil_to_fertilizer.get(j).get(destination_range_start); // 50
                double range = soil_to_fertilizer.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_soil_to_fertilizer.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_soil_to_fertilizer.put(seed, destino);
            }

            //System.out.println("Seed: "+seed+" destino: "+destino);
            
        }

        for(int i=0;i<converted_soil_to_fertilizer.size();i++){
            double destino=-1;
            //double iter=converted_soil_to_fertilizer.get(seed);
            seed = seeds.get(i);
            double previous_jump = converted_seed_to_soil.get(seed);
            seed = converted_soil_to_fertilizer.get(previous_jump);
            //seed = converted_soil_to_fertilizer.get(seed);
            for(double j=0;j<fertilizer_to_water.size();j++){
                double range_start = fertilizer_to_water.get(j).get(source_range_start); // 98
                double dst_start = fertilizer_to_water.get(j).get(destination_range_start); // 50
                double range = fertilizer_to_water.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_fertilizer_to_water.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_fertilizer_to_water.put(seed, destino);
            }

            //System.out.println("Seed: "+seed+" destino: "+destino);
            
        }

        for(int i=0;i<converted_fertilizer_to_water.size();i++){
            double destino=-1;
            //double iter=converted_fertilizer_to_water.get(seed);
            seed = seeds.get(i);
            double previous_jump = converted_soil_to_fertilizer.get(converted_seed_to_soil.get(seed));
            seed = converted_fertilizer_to_water.get(previous_jump);
            //seed = converted_fertilizer_to_water.get(seed);
            for(double j=0;j<water_to_light.size();j++){
                double range_start = water_to_light.get(j).get(source_range_start); // 98
                double dst_start = water_to_light.get(j).get(destination_range_start); // 50
                double range = water_to_light.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_water_to_light.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_water_to_light.put(seed, destino);
            }
        }

        for(int i=0;i<converted_water_to_light.size();i++){
            double destino=-1;
            //double iter=converted_fertilizer_to_water.get(seed);
            seed = seeds.get(i);
            double previous_jump = converted_fertilizer_to_water.get(converted_soil_to_fertilizer.get(converted_seed_to_soil.get(seed)));
            seed = converted_water_to_light.get(previous_jump);
            //seed = converted_fertilizer_to_water.get(seed);
            for(double j=0;j<light_to_temperature.size();j++){
                double range_start = light_to_temperature.get(j).get(source_range_start); // 98
                double dst_start = light_to_temperature.get(j).get(destination_range_start); // 50
                double range = light_to_temperature.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_light_to_temperature.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_light_to_temperature.put(seed, destino);
            }

            //System.out.println("Seed: "+seed+" destino: "+destino);    
        }

        for(int i=0;i<converted_light_to_temperature.size();i++){
            double destino=-1;
            //double iter=converted_fertilizer_to_water.get(seed);
            seed = seeds.get(i);
            double previous_jump = converted_water_to_light.get(converted_fertilizer_to_water.get(converted_soil_to_fertilizer.get(converted_seed_to_soil.get(seed))));
            seed = converted_light_to_temperature.get(previous_jump);
            //seed = converted_fertilizer_to_water.get(seed);
            for(double j=0;j<temperature_to_humidity.size();j++){
                double range_start = temperature_to_humidity.get(j).get(source_range_start); // 98
                double dst_start = temperature_to_humidity.get(j).get(destination_range_start); // 50
                double range = temperature_to_humidity.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_temperature_to_humidity.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_temperature_to_humidity.put(seed, destino);
            }

            //System.out.println("Seed: "+seed+" destino: "+destino);    
        }

        for(int i=0;i<converted_temperature_to_humidity.size();i++){
            double destino=-1;
            //double iter=converted_fertilizer_to_water.get(seed);
            seed = seeds.get(i);
            double previous_jump = converted_light_to_temperature.get(converted_water_to_light.get(converted_fertilizer_to_water.get(converted_soil_to_fertilizer.get(converted_seed_to_soil.get(seed)))));
            seed = converted_temperature_to_humidity.get(previous_jump);
            //seed = converted_fertilizer_to_water.get(seed);
            for(double j=0;j<humidity_to_location.size();j++){
                double range_start = humidity_to_location.get(j).get(source_range_start); // 98
                double dst_start = humidity_to_location.get(j).get(destination_range_start); // 50
                double range = humidity_to_location.get(j).get(range_lenght); // 2
                double upper_limit = (range_start+range-1); // 99
                double desvio =  dst_start - range_start;
                // Estamos dentro del rango
                if(seed >= range_start && seed <= upper_limit){
                    destino=seed+desvio;
                    converted_humidity_to_location.put(seed, destino);
                }
            }
            // Si no se ha cumplido la condicion en ningun punto entonces asignar el mismo valor.
            if (destino==-1){
                destino=seed;
                converted_humidity_to_location.put(seed, destino);
            }

            //System.out.println("Seed: "+seed+" destino: "+destino);    
        }

        // System.out.println("Seed to soil: "+converted_seed_to_soil.toString());
        // System.out.println("soil to fertilizer: "+converted_soil_to_fertilizer.toString());
        // System.out.println("fertilizer to water: "+converted_fertilizer_to_water.toString());
        // System.out.println("water to light: "+converted_water_to_light.toString());
        // System.out.println("light to temperature: "+converted_light_to_temperature.toString());
        // System.out.println("temperature to humidity: "+converted_temperature_to_humidity.toString());
        // System.out.println("humidity to location: "+converted_humidity_to_location.toString());

        List<Double> values = new ArrayList<Double>(converted_humidity_to_location.values());
        //System.out.println(values.toString());

        lowest=values.get(0);
        for(int i=0;i<values.size()-1;i++){
            if(values.get(i) < lowest){
                lowest = values.get(i);
            }
        }

        System.out.println("Valor mas bajo: "+(int)lowest);
    }
}