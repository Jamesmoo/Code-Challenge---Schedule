public class Main {
    public static void main(String[] args){
        DailySchedule daySchedule =  new DailySchedule();
        ReadCSV readCSV = new ReadCSV("schedules.csv");

        readCSV.startFileRead(daySchedule);

        daySchedule.printAll();
    }
}
