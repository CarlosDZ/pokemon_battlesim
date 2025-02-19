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

    public String getDescription(int id){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("[A-Za-z -]+\\{ID:"+id);

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    Pattern desPattern = Pattern.compile("\\s*DES:(.*)");
                    while((line = reader.readLine()) != null){
                        Matcher desMatcher = desPattern.matcher(line);
                        if(desMatcher.find()){
                            String description = desMatcher.group(1);
                            return description;
                        }
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return "-1";
    }

}
