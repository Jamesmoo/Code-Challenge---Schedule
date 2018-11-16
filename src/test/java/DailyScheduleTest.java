import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DailyScheduleTest {
    DailySchedule dailySchedule;

    @Before
    public void setup(){
        dailySchedule = new DailySchedule();
    }

    @Test
    public void setPersonSchedule_setAndRetrieve(){
        String fileLine = "James,4:00PM,4:30PM";
        dailySchedule.setPersonSchedule(fileLine);

        //should be empty
        String resultOne = dailySchedule.getScheduleByHour("4:00PM");
        assertEquals("", resultOne);

        //should be filled
        String resultTwo = dailySchedule.getScheduleByHour("3:00PM");
        assertEquals("James", resultTwo);
    }

    @Test
    public void getResultsAsJson_noData(){
        String expected = "{\"am_before_work\":[{}],\"lunch\":[{}],\"pm_after_work\":[{}],\"pm_in_office\":[{}],\"am_in_office\":[{}]}";
        String results = dailySchedule.getResultsAsJson().toString();

        assertEquals(expected, results);
    }

    @Test
    public void getResultsAsJson_withData(){
        String expected = "{\"am_before_work\":[{\"12:00AM\":\"James,Jack,Jess\",\"12:30AM\":\"James,Jack,Jess\",\"1:00AM\":\"James,Jack,Jess\",\"1:30AM\":\"James,Jack,Jess\",\"2:00AM\":\"James,Jack,Jess\",\"2:30AM\":\"James,Jack,Jess\",\"3:00AM\":\"James,Jack,Jess\",\"3:30AM\":\"James,Jack,Jess\",\"4:00AM\":\"James,Jack,Jess\",\"4:30AM\":\"James,Jack,Jess\",\"5:00AM\":\"James,Jack,Jess\",\"5:30AM\":\"James,Jack,Jess\",\"6:00AM\":\"James,Jack,Jess\",\"6:30AM\":\"James,Jack,Jess\",\"7:00AM\":\"James,Jack,Jess\",\"7:30AM\":\"James,Jack,Jess\"}],\"lunch\":[{\"12:00PM\":\"James,Jack,Jess\",\"12:30PM\":\"James,Jack,Jess\"}],\"pm_after_work\":[{\"5:30PM\":\"James,Jack,Jess\",\"6:00PM\":\"James,Jack,Jess\",\"6:30PM\":\"James,Jack,Jess\",\"7:00PM\":\"James,Jack,Jess\",\"7:30PM\":\"James,Jack,Jess\",\"8:00PM\":\"James,Jack,Jess\",\"8:30PM\":\"James,Jack,Jess\",\"9:00PM\":\"James,Jack,Jess\",\"9:30PM\":\"James,Jack,Jess\",\"10:00PM\":\"James,Jack,Jess\",\"10:30PM\":\"James,Jack,Jess\",\"11:00PM\":\"James,Jack,Jess\",\"11:30PM\":\"James,Jack,Jess\"}],\"pm_in_office\":[{\"1:00PM\":\"James,Jack,Jess\",\"1:30PM\":\"James,Jack,Jess\",\"2:00PM\":\"James,Jack,Jess\",\"2:30PM\":\"James,Jack,Jess\",\"3:00PM\":\"James,Jack,Jess\",\"3:30PM\":\"James,Jack,Jess\",\"5:00PM\":\"James,Jack,Jess\"}],\"am_in_office\":[{\"8:00AM\":\"James,Jack,Jess\",\"8:30AM\":\"James,Jack,Jess\",\"9:00AM\":\"James,Jack,Jess\",\"9:30AM\":\"James,Jack,Jess\",\"10:00AM\":\"James,Jack,Jess\",\"10:30AM\":\"James,Jack,Jess\",\"11:00AM\":\"James,Jack,Jess\",\"11:30AM\":\"James,Jack,Jess\"}]}";

        dailySchedule.setPersonSchedule("James,4:00PM,4:30PM");
        dailySchedule.setPersonSchedule("Jack,4:00PM,4:30PM");
        dailySchedule.setPersonSchedule("Jess,4:00PM,4:30PM");

        String results = dailySchedule.getResultsAsJson().toString();

        //array with only the hours of 4pm and 430pm as not available
        assertEquals(expected, results);
    }


    @Test
    public void getResultsAsJson_onlyTwoPeople(){
        String expected = "{\"am_before_work\":[{}],\"lunch\":[{}],\"pm_after_work\":[{}],\"pm_in_office\":[{}],\"am_in_office\":[{}]}";

        dailySchedule.setPersonSchedule("James,4:00PM,4:30PM");
        dailySchedule.setPersonSchedule("Jack,4:00PM,4:30PM");

        String results = dailySchedule.getResultsAsJson().toString();

        //blank array - there should be 3 people available not 2
        assertEquals(expected, results);
    }
}
