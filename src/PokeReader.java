import java.io.*;
import java.util.regex.*;

public class PokeReader {
    public static final String FILE_PATH = "resources/Pokemons.txt";

    public int getNatID(String poke_name){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            Pattern pattern = Pattern.compile("NatID#(\\d{4})\\s*\\{\\s*Name:([A-Za-z ]+)");            
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    int natId = Integer.parseInt(matcher.group(1));
                    String name = matcher.group(2);
                    if (name.equals(poke_name)) {
                        return natId;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // Si no se encuentra el pokemon.
    }
    
    public int[] getBaseStats(int natID){
        int[] stats = {0,0,0,0,0,0};

        return stats;
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
