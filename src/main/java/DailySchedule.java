import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DailySchedule {
    private LinkedHashMap<String, String> hourAvailability;
    private JSONObject jsonResults;

    public DailySchedule(){
        setHours();
    }

    /**
     * Sets an ordered list linkedhashmap with the times of the day
     * the time starts at 12:00AM and goes to 11:30PM
     */
    private void setHours(){
        hourAvailability = new LinkedHashMap<String, String>();
        String timeOfDay = "12:00";
        String timeIndicator = "AM";

        for(int i=0; i < 48; i ++){
            hourAvailability.put(timeOfDay  + timeIndicator, "");

            if(timeOfDay.equals("11:30")){
                timeIndicator = "PM";
            }

            if(timeOfDay.endsWith("00")){
                timeOfDay = timeOfDay.split(":")[0] + ":30";
            }else{
                int hour = Integer.parseInt(timeOfDay.split(":")[0]);
                hour++;
                if(hour == 13){
                    hour = 1;
                }
                timeOfDay = hour + ":00";
            }
        }
    }



    /**
     * Takes a line read from a CSV and process it into the availability list
     * @param fileLine String from CSV in format: Kyle,1:30PM,4PM
     */
    public void setPersonSchedule(String fileLine){
        String name = fileLine.substring(0, fileLine.indexOf(','));
        String meetingTimes = fileLine.substring(fileLine.indexOf(','));

        for (Map.Entry<String, String> entry : hourAvailability.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (!meetingTimes.contains(key)){
                addAvailablePerson(key, name);
            }
        }
    }

    /**
     * Adds a person thats available to an hour block
     * @param hour String hour representation in format: 12:00PM
     * @param person String name
     */
    private void addAvailablePerson(String hour, String person){
        String peopleAvailable = hourAvailability.get(hour);

        if(!peopleAvailable.equals("")){
            peopleAvailable = peopleAvailable + ",";
        }

        peopleAvailable += person;
        hourAvailability.put(hour, peopleAvailable);
    }

    public JSONObject getResultsAsJson(){
        jsonResults = new JSONObject();
        ArrayList<LinkedHashMap<String, String>> availability = getAvailabilityByTimeOfDay();

        JSONArray
    }

    /**
     * Divide the day into 5 sections, a list for each, makes it easier to organize a JSON file
     * @return ArrayList of LinkedHashMaps that each is a part of the day and availability
     */
    private ArrayList<LinkedHashMap<String, String>> getAvailabilityByTimeOfDay(){
        ArrayList<LinkedHashMap<String, String>> results = new ArrayList<LinkedHashMap<String, String>>();

        LinkedHashMap<String, String> morningBeforeWork = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> morningAtWork = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> lunchTime = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> afternoonAtWork = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> nightAfterWork = new LinkedHashMap<String, String>();

        int count = 0;
        for (Map.Entry<String, String> entry : hourAvailability.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int peopleAvailable = value.split(",").length;

            if(count < 16 && peopleAvailable >= 3){ //AM - before office hours
                morningBeforeWork.put(key, value);

            }if(count >= 16 && count < 24 && peopleAvailable >= 3){//AM during work
                morningAtWork.put(key,value);

            }if(count >= 24 && count < 26 && peopleAvailable >= 3){//Lunch
                lunchTime.put(key, value);

            }if(count >= 26 && count < 35 && peopleAvailable >= 3){//PM during office hours
                afternoonAtWork.put(key, value);

            }if(count >= 35 && peopleAvailable >= 3){//PM after work
                nightAfterWork.put(key, value);
            }
            count++;
        }

        results.add(morningBeforeWork);
        results.add(morningAtWork);
        results.add(lunchTime);
        results.add(afternoonAtWork);
        results.add(nightAfterWork);

        return results;
    }

    //TODO: Test method - delete before turning in
    public void printAll(){
        int count = 0;
        for (Map.Entry<String, String> entry : hourAvailability.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int peopleAvailable = value.split(",").length;

            if(count < 16){ //AM - before office hours
                System.out.println(count + " after " + key + " - " + value);
                System.out.println("people available" + peopleAvailable);
            }if(count >= 16 && count < 24){//AM during work
                System.out.println(count + " during " + key + " - " + value);
                System.out.println("people available" + peopleAvailable);
            }if(count >= 24 && count < 26){//Lunch
                System.out.println(count + " lunch " + key + " - " + value);
                System.out.println("people available" + peopleAvailable);
            }if(count >= 26 && count < 35){//PM during office hours
                System.out.println(count + " during " + key + " - " + value);
                System.out.println("people available" + peopleAvailable);
            }if(count >= 35){//PM after work
                System.out.println(count + " after " + key + " - " + value);
                System.out.println("people available" + peopleAvailable);
            }
            count++;
        }
    }
}
