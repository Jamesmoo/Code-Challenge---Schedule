import com.oracle.tools.packager.Log;

import java.io.*;

public class WriteFile {

    public static void writeToFile(String fileName, String content){
        BufferedWriter bufferedWriter;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            Writer writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(content);

            if(bufferedWriter != null){
                bufferedWriter.close();
            }
        } catch(IOException e){
            Log.debug("Error writing output file");
        }
    }
}
