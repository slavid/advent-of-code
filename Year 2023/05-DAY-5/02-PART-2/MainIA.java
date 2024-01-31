import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainIA {

    static class ConversionMap {
        long destStart;
        long sourceStart;
        long length;

        public ConversionMap(long destStart, long sourceStart, long length) {
            this.destStart = destStart;
            this.sourceStart = sourceStart;
            this.length = length;
        }
    }

    static List<Long> readSeeds(String fileName) throws IOException {
        List<Long> seeds = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            String[] seedValues = line.split(" ");
            for (String seedValue : seedValues) {
                if(seedValue.matches("^\\d+$")){
                    seeds.add(Long.parseLong(seedValue));
                }
            }
        }
        return seeds;
    }
    static List<Long> readLongSeeds(List<Long> seeds, int range, long lastPos) throws IOException {
        List<Long> longer_seeds = new ArrayList<>();
        for(int i=0;i<=seeds.size()/2;i=i+2){
        //System.out.println("Siguiente valor: "+seeds.get(i));
        Long diff = seeds.get(i+1);
        // System.out.println("numero iteraciones: "+diff);
        // System.out.println("numero final: "+(seeds.get(i)+diff));
            for(long j=lastPos;j<range;j++){
                longer_seeds.add((seeds.get(i)+j));
                //System.out.println("Añadimos seed: "+(seeds.get(i)+j));
            }
            
        }
        return longer_seeds;
        //System.out.println(longer_seeds.toString());
    }
    static List<List<ConversionMap>> readConversionMaps(String fileName) throws IOException {
        List<List<ConversionMap>> conversionMaps = new ArrayList<>();
        List<ConversionMap> currentMap = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("seeds:") || line.isEmpty()) {
                    continue;
                } else if (line.startsWith("seed-to-soil map:") || line.startsWith("soil-to-fertilizer map:")
                        || line.startsWith("fertilizer-to-water map:") || line.startsWith("water-to-light map:")
                        || line.startsWith("light-to-temperature map:") || line.startsWith("temperature-to-humidity map:")
                        || line.startsWith("humidity-to-location map:")) {
                    if (currentMap != null) {
                        conversionMaps.add(currentMap);
                    }
                    currentMap = new ArrayList<>();
                } else {
                    String[] values = line.split(" ");
                    Long[] valuesLong = new Long[3];
                    valuesLong[0] = Long.parseLong(values[0]);
                    valuesLong[1] = Long.parseLong(values[1]);
                    valuesLong[2] = Long.parseLong(values[2]);
                    
                    currentMap.add(new ConversionMap(
                            valuesLong[0],
                            valuesLong[1],
                            valuesLong[2]
                    ));
                }
            }
            if (currentMap != null) {
                conversionMaps.add(currentMap);
            }
        }
        return conversionMaps;
    }

    static long convertNumber(long number, List<ConversionMap> conversionMap) {
        for (ConversionMap map : conversionMap) {
            if (map.sourceStart <= number && number < map.sourceStart + map.length) {
                return map.destStart + (number - map.sourceStart);
            }
        }
        return number;
    }

    static long findLowestLocation(List<Long> seeds, List<List<ConversionMap>> maps) {
        List<Long> currentNumbers = new ArrayList<>(seeds);
        for (List<ConversionMap> conversionMap : maps) {
            List<Long> nextNumbers = new ArrayList<>();
            for (long number : currentNumbers) {
                long convertedNumber = convertNumber(number, conversionMap);
                nextNumbers.add(convertedNumber);
            }
            currentNumbers = nextNumbers;
        }
        return currentNumbers.stream().min(Long::compare).orElse((long) 0);
    }

    public static void main(String[] args) {
        int max_chunk = 10000;
        Map<Integer,Long> stepsSeed = new HashMap<Integer,Long>();
        long lastPos = 0;
        long steps = 0;
        try {
            List<Long> seeds = readSeeds("input.txt");
            
            for(int i=0;i<=seeds.size()/2;i=i+2){
                steps = Math.ceilDiv(seeds.get(i+1), max_chunk);
                stepsSeed.put(i, steps);
            }

            for(int i=0;i<=seeds.size()/2;i=i+2){
                
            }
            List<Long> seedsLong = readLongSeeds(seeds, max_chunk);
            List<List<ConversionMap>> conversionMaps = readConversionMaps("input.txt");
            //for(int i=0;)
            // Find the lowest location number
            long result = findLowestLocation(seeds, conversionMaps);
            long result2 = findLowestLocation(seedsLong, conversionMaps);
            System.out.println("Lowest is: "+result2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
