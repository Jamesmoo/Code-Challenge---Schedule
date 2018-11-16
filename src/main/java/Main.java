public class Main {
    //this should be the path on your local to where the input CSVs are, say a network drive location or something
    private static final String MAIN_PATH = "//Users//jamesadams//input//";

    public static void main(String[] args){
        //original dataset
        launchProcessing(MAIN_PATH + "schedules.csv", "results.json");

        //blank file
        launchProcessing(MAIN_PATH + "test1.csv", "test1.json");

        //busy all day
        launchProcessing(MAIN_PATH + "test2.csv", "test2.json");

        //3 people busy at the same hours
        launchProcessing(MAIN_PATH + "test3.csv", "test3.json");

        //4 people busy
        launchProcessing(MAIN_PATH + "test4.csv", "test4.json");

        //everyone available
        launchProcessing(MAIN_PATH + "test5.csv", "test5.json");
    }

    private static void launchProcessing(String csvFile, String outputFileName){
        DailySchedule daySchedule =  new DailySchedule();

        ReadCSV readCSV = new ReadCSV(csvFile);
        readCSV.startFileRead(daySchedule);

        WriteFile.writeToFile(outputFileName, daySchedule.getResultsAsJson().toString());
    }
}
