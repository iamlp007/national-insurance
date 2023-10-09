import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class nationalInsurance {
    public static void main(String[] args) {
        String fileName = "src/dataset.csv";
        BufferedReader br = null;
        String line = "";
        List<String[]> csvDataSet = new ArrayList<>();
        List<String[]> peopleDataSet = new LinkedList<>();
        Map<String, Integer> countryCount = new HashMap<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String header = br.readLine();
            String[] headerColumns = header.split(",");
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                csvDataSet.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (String[] row: csvDataSet) {
            String firstNameInitial = row[0];
            String lastNameInitial = row[1];
            String yearLastTwoDigits = row[4].substring(2,4);
            int randomCode = new Random().nextInt(9000) + 1000;
            String countryOfBirth = row[5];
            String[] person = {firstNameInitial, lastNameInitial, yearLastTwoDigits, Integer.toString(randomCode), countryOfBirth.substring(0,1)};
            peopleDataSet.add(person);
            countryCount.put(countryOfBirth, countryCount.getOrDefault(countryOfBirth, 0) + 1);
        }

        System.out.println("National Insurance Number for the dataset are as follows: \n");
        for (String[] person : peopleDataSet) {
            System.out.println(person[0] + " : " + person[0].substring(0,1) + person[1].substring(0,1) + " " + person[2] + " " + person[3] + " " + person[4]);
        }

        System.out.println("\n\nThe country counts for thr dataset are as follows: \n");
        for (Map.Entry<String, Integer> entry : countryCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }            
    }
}
