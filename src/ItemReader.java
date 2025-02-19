import java.io.*;
import java.util.regex.*;

public class ItemReader {
    public static final String FILE_PATH = "resources/Items.txt";

    public int getID(String name){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile(name+"\\{ID:(\\d*)");

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    int id = Integer.parseInt(matcher.group(1));
                    return id;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
