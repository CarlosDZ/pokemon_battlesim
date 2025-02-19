import java.io.*;
import java.util.regex.*;

public class PokeReader {
    public static final String FILE_PATH = "resources/Pokemons.txt";

    public int getNatID(String poke_name){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("NatID#(\\d+)\\s*\\{\\s*Name:"+poke_name);

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    int natID = Integer.parseInt(matcher.group(1));
                    return natID;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int[] getBaseStats(int natID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("NatID#"+natID+"\\s*\\{\\s*Name:[A-Za-z -]+");

            String line;
            int[] stats = new int[6];
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    Pattern statpattern = Pattern.compile("\\s*[A-Z ]+:(\\d+)\\s*");

                    for(int i = 0; i < 8; i++){
                        line = reader.readLine();
                        Matcher statmatcher = statpattern.matcher(line);
                        if(statmatcher.find()){
                            stats[i-2] = Integer.parseInt(statmatcher.group(1));
                        }
                    }
                    return stats;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return new int[]{-1};
    }
    public int getNumberOfHabs(int natID){
        int stats = 0;

        return stats;
    }

    public String[] getHabs(int natID){
        //esta necesitara ejecutar getNumberOfHabs()
        String[] habilities = {};

        return habilities;
    }

    public String[] getTypes(int natID){

        String[] types = {};

        return types;
    }

    public int[] getMovePool(int natID){
        int[] movePool = {};

        return movePool;
    }
}
