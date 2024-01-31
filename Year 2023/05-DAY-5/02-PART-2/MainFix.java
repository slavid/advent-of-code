import java.io.File;
import java.io.IOException;
import java.util.*;


public class MainFix {

    public static List<Double> seeds = new ArrayList<Double>();
    public static List<Double> longer_seeds = new ArrayList<Double>();
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
    public static int max_chunk = 100000;

    public static Map<Double,Double> converted_seed_to_soil = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_soil_to_fertilizer = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_fertilizer_to_water = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_water_to_light = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_light_to_temperature = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_temperature_to_humidity = new HashMap<Double,Double>();
    public static Map<Double,Double> converted_humidity_to_location = new HashMap<Double,Double>();

     public static void main(String[] args) throws IOException {
        
        Scanner scanner = new Scanner(new File("input_small.txt"));
        



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

            System.out.println(seeds.toString());
        

            // Hacemos primera llamada a traducir, luego sacar del while

            seed_to_soil = translate(scanner, seed_to_soil_numbers, seed_to_soil, "seed-to-soil");
            System.out.println(seed_to_soil.toString());
            //seed_to_soil_numbers = new ArrayList<Double>();

            

            // if (next.equals("seed-to-soil")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$")){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             next = scanner.next();
            //         }
            //         seed_to_soil.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Seed to soil map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

            soil_to_fertilizer = translate(scanner, seed_to_soil_numbers, soil_to_fertilizer, "soil-to-fertilizer");
            System.out.println(soil_to_fertilizer.toString());
            //seed_to_soil_numbers = new ArrayList<Double>();

            // if (next.equals("soil-to-fertilizer")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$")){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             next = scanner.next();
            //         }
            //         soil_to_fertilizer.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

            fertilizer_to_water = translate(scanner, seed_to_soil_numbers, fertilizer_to_water, "fertilizer-to-water");
            System.out.println(soil_to_fertilizer.toString());
            //seed_to_soil_numbers = new ArrayList<Double>();

            // if (next.equals("fertilizer-to-water")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$")){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             next = scanner.next();
            //         }
            //         fertilizer_to_water.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

            water_to_light = translate(scanner, seed_to_soil_numbers, water_to_light, "water-to-light");
            System.out.println(water_to_light.toString());

            // if (next.equals("water-to-light")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$")){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             next = scanner.next();
            //         }
            //         water_to_light.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

            light_to_temperature = translate(scanner, seed_to_soil_numbers, light_to_temperature, "light-to-temperature");
            System.out.println(light_to_temperature.toString());

            // if (next.equals("light-to-temperature")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$")){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             next = scanner.next();
            //         }
            //         light_to_temperature.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

            temperature_to_humidity = translate(scanner, seed_to_soil_numbers, temperature_to_humidity, "temperature-to-humidity");
            System.out.println(temperature_to_humidity.toString());

            // if (next.equals("temperature-to-humidity")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$")){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             next = scanner.next();
            //         }
            //         temperature_to_humidity.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

            humidity_to_location = translate(scanner, seed_to_soil_numbers, humidity_to_location, "humidity-to-location");
            System.out.println(humidity_to_location.toString());            

            // if (next.equals("humidity-to-location")){
            //     next = scanner.next();
            //     next = scanner.next();
            //     double iterator = 0;
            //     while(next.matches("^\\d+$") && scanner.hasNext()){
            //         for(int i=0;i<3;i++){
            //             seed_to_soil_numbers.add(Double.parseDouble(next));
            //             if(scanner.hasNext())
            //                 next = scanner.next();
            //         }
            //         humidity_to_location.put(iterator, seed_to_soil_numbers);
            //         //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
            //         seed_to_soil_numbers = new ArrayList<Double>();
            //         iterator++;
            //     }
            // }

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
        
        // convertir seeds en un array mas largo por pares
        /** for(int i=0;i<=seeds.size()/2;i=i+2){
            //System.out.println("Siguiente valor: "+seeds.get(i));
            double diff = seeds.get(i+1);
            // System.out.println("numero iteraciones: "+diff);
            // System.out.println("numero final: "+(seeds.get(i)+diff));
            for(double j=0;j<diff;j++){
                longer_seeds.add(seeds.get(i)+j);
            }
            
        }

        //seeds = longer_seeds;

        //System.out.println(longer_seeds.toString());

        for(int i=0;i<longer_seeds.size();i++){
            double destino=-1;
            seed = longer_seeds.get(i);
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
            seed = longer_seeds.get(i);
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
            seed = longer_seeds.get(i);
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
            seed = longer_seeds.get(i);
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
            seed = longer_seeds.get(i);
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
            seed = longer_seeds.get(i);
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
            seed = longer_seeds.get(i);
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
        */
        // lowest=values.get(0);
        // for(int i=0;i<values.size()-1;i++){
        //     if(values.get(i) < lowest){
        //         lowest = values.get(i);
        //     }
        // }

        //System.out.println("Valor mas bajo: "+(int)lowest);
    }

    public static Map<Double,List<Double>> translate(Scanner scanner, List<Double> listaSrc, Map<Double,List<Double>> listaDst, String limitador){
        String next = scanner.next();
        if (next.equals(limitador)){
                next = scanner.next();
                next = scanner.next();
                double iterator = 0;
                while(next.matches("^\\d+$") && scanner.hasNext()){
                    for(int i=0;i<3;i++){
                        listaSrc.add(Double.parseDouble(next));
                        if(scanner.hasNext())
                            next = scanner.next();
                    }
                    listaDst.put(iterator, listaSrc);
                    //System.out.println("Soil to fertilizer map: "+seed_to_soil_numbers.toString());
                    listaSrc = new ArrayList<Double>();
                    iterator++;
                }
            }
            listaSrc = new ArrayList<Double>();
            return listaDst;
    }
                
}