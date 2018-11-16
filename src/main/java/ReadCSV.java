import com.oracle.tools.packager.Log;

import java.io.*;
import java.util.Scanner;

public class ReadCSV {
    private String fileName;

    /**
     * Constructor
     * @param fileName csv file located in the resources folder
     */
    public ReadCSV(String fileName){
        this.fileName = fileName;
    }

    /**
     * Read the file and populates the daily schedule
     * @param dailySchedule a daily schedule object
     * @throws Exception error if the csv file cannot be read
     */
    public void startFileRead(DailySchedule dailySchedule){
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = fixTime(line);
                dailySchedule.setPersonSchedule(line);
            }

            scanner.close();
        }catch(Exception e){
            Log.debug("Error processing CSV File");
        }
    }

    /**
     * Standardizes the times
     * Since the input file can contain 9pm as a valid time, want to change it to be 9:00PM to be uniform
     * @param line a single line as read by the file
     * @return
     */
    private String fixTime(String line){
        String [] nameAndTimes = line.split(",");
        line = nameAndTimes[0];

        for(int i=1; i < nameAndTimes.length; i++){
            line += ",";
            String time = nameAndTimes[i];
            time = time.toUpperCase();

            //time is in format 9PM
            if(!time.contains(":")){
                if(time.contains("AM")){
                    time = time.replaceAll("[^\\d]", "") + ":00AM";
                }else{
                    time = time.replaceAll("[^\\d]", "") + ":00PM";
                }
            }
            line += time;
        }
        return line;
    }
}
