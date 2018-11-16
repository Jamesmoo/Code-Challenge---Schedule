public class Main {
    public static void main(String[] args){
        //original dataset
        launchProcessing("schedules.csv", "results.json");

        //blank file
        //launchProcessing("test1.csv", "test1.json");

        //busy all day
        //launchProcessing("test2.csv", "test2.json");

        //3 people busy at the same hours
        //launchProcessing("test3.csv", "test3.json");

        //4 people busy
        //launchProcessing("test4.csv", "test4.json");

        //everyone available
        //launchProcessing("test5.csv", "test5.json");
    }

    private static void launchProcessing(String csvFile, String outputFileName){
        DailySchedule daySchedule =  new DailySchedule();

        ReadCSV readCSV = new ReadCSV(csvFile);
        readCSV.startFileRead(daySchedule);

        WriteFile.writeToFile(outputFileName, daySchedule.getResultsAsJson().toString());
    }
}
