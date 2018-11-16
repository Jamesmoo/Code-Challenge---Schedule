import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ReadCSVTest {
    private final String fileName = "dummy-file.csv";
    private ReadCSV readCSV;

    @Before
    public void setup(){
        readCSV = new ReadCSV(fileName);
    }

    @Test
    public void fixTime_correctFormat(){
        String timeOne = "james,4PM";
        String timeTwo = "james,34PM";

        String fixedOne = readCSV.fixTime(timeOne);
        assertEquals("james,4:00PM", fixedOne );

        //this is not a valid time, but that class only is meant to take time inputs like 34PM and make them "34:00PM"
        String fixedTwo = readCSV.fixTime(timeTwo);
        assertEquals(fixedTwo, "james,34:00PM");
    }
}
