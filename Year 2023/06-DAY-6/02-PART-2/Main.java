import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            List<Long> times = readTimes("input.txt");
            List<Long> distances = readDistances("input.txt");
            long count = 1;
            //System.out.println(times.toString());
            //System.out.println(distances.toString());
            //List<Integer> results = new ArrayList<>();

            for(int i=0;i<times.size();i++){
                //results.add(calculateDistances(times.get(i), distances.get(i)));
                count = count * calculateDistances(times.get(i), distances.get(i));
            }

            
            //System.out.println(results.toString());
            System.out.println("Final result: "+count);
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int calculateDistances(long maxTime, long distanceToBeat) {
        //List<Long> greaterReachDistances = new ArrayList<>();
        long speed = 0;
        long timeleft = 0;
        long reach = 0;
        int count = 0;

        for(int i=1;i<maxTime;i++){
            // Holdeamos el boton durante i milisegundos
            speed = i;
            timeleft = maxTime-i;
            reach = speed*timeleft;
            if(reach>distanceToBeat){
                //greaterReachDistances.add(speed*timeleft);
                count++;
            }
        }
        //return greaterReachDistances.size();
        return count;
    }

    static List<Long> readTimes(String fileName) throws IOException {
        List<Long> times = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            String[] timeValues = line.split(" ");
            for (String timeValue : timeValues) {
                if(timeValue.matches("^\\d+$")){
                    times.add(Long.parseLong(timeValue));
                }
            }
        }
        return times;
    }

    static List<Long> readDistances(String fileName) throws IOException {
        List<Long> distances = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            if(line.startsWith("Time:")){
                line=reader.readLine();
            }
            String[] distanceValues = line.split(" ");
            for (String distanceValue : distanceValues) {
                if(distanceValue.matches("^\\d+$")){
                    distances.add(Long.parseLong(distanceValue));
                }
            }
        }
        return distances;
    }
}
