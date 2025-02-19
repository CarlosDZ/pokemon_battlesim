import java.io.*;
import java.util.regex.*;

public class MoveReader {
    public static final String FILE_PATH = "resources/Movements.txt";

    public String getName(int moveID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            Pattern pattern = Pattern.compile("\\s*([A-Za-z0-9 -]+)\\{ID:"+moveID+"\\s*");
            
            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    String moveName = matcher.group(1);
                    return moveName;
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return "-1";
    }

    public String[] getMovePoolNames(int[] movepool){
        String[] movePoolNames = new String[movepool.length];
        for(int i=0; i < movepool.length; i++){
            movePoolNames[i] = getName(movepool[i]);
        }
        return movePoolNames;
    }

    public String getType(int moveID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            Pattern pattern = Pattern.compile("\\s*[A-Za-z0-9 -]+\\{ID:"+moveID+"\\s*");
            
            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    Pattern typePattern = Pattern.compile("\\s*TYPE:([A-Za-z]+)");
                    while((line = reader.readLine()) != null){
                        Matcher typeMatcher = typePattern.matcher(line);
                        if(typeMatcher.find()){
                            String type = typeMatcher.group(1);
                            return type;
                        }
                    }
                    
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return "-1";
    }

    public String getFamily(int moveID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            Pattern pattern = Pattern.compile("\\s*[A-Za-z0-9 -]+\\{ID:"+moveID+"\\s*");
            
            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    Pattern famPattern = Pattern.compile("\\s*FAMILY:([A-Za-z]+)");
                    while((line = reader.readLine()) != null){
                        Matcher famMatcher = famPattern.matcher(line);
                        if(famMatcher.find()){
                            String type = famMatcher.group(1);
                            return type;
                        }
                    }
                    
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return "-1";
    }

    public int getPower(int moveID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            Pattern pattern = Pattern.compile("\\s*[A-Za-z0-9 -]+\\{ID:"+moveID+"\\s*");
            
            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    Pattern powPattern = Pattern.compile("\\s*POWER:(\\d*)");
                    while((line = reader.readLine()) != null){
                        Matcher powMatcher = powPattern.matcher(line);
                        if(powMatcher.find()){
                            int pow = Integer.parseInt(powMatcher.group(1));
                            return pow;
                        }
                    }
                    
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getAccur(int moveID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {

            Pattern pattern = Pattern.compile("\\s*[A-Za-z0-9 -]+\\{ID:"+moveID+"\\s*");
            
            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    Pattern accPattern = Pattern.compile("\\s*ACCUR:(\\d*)");
                    while((line = reader.readLine()) != null){
                        Matcher accMatcher = accPattern.matcher(line);
                        if(accMatcher.find()){
                            int acc = Integer.parseInt(accMatcher.group(1));
                            return acc;
                        }
                    }
                    
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }
}
