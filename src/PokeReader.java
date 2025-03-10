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
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("NatID#"+natID+"\\s*\\{\\s*Name:[A-Za-z -]+");

            String line;
            int numberOfHabs = 0;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    Pattern habsnumpattern = Pattern.compile("\\s*NumOfAbilitys:(\\d+)\\s*");

                    while(numberOfHabs == 0){
                        line = reader.readLine();
                        Matcher habsnumMatcher = habsnumpattern.matcher(line);
                        if(habsnumMatcher.find()){
                            numberOfHabs = Integer.parseInt(habsnumMatcher.group(1));
                        }
                    }
                    return numberOfHabs;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return -1;
    }

    public String[] getHabs(int natID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String[] habs = new String[getNumberOfHabs(natID)];
            Pattern pattern = Pattern.compile("NatID#"+natID+"\\s*\\{\\s*Name:[A-Za-z -]+");

            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    Pattern habsPattern = Pattern.compile("\\s*AbilityString:\\s*");
                    while(true){
                        line = reader.readLine();
                        Matcher habsMatcher = habsPattern.matcher(line);
                        if(habsMatcher.find()){
                            for(int i = 0; i < habs.length; i++){
                                line = reader.readLine();
                                habs[i] = line.trim();
                            }
                            return habs;
                        }
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return new String[]{"-1"};
    }

    public String[] getTypes(int natID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("NatID#"+natID+"\\s*\\{\\s*Name:[A-Za-z -]+");

            String line;
            String[] types = new String[2];
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    Pattern typePattern = Pattern.compile("\\s*[A-Za-z ]+\\d{1}:([A-Za-z]+)\\s*");

                    for(int i = 0; i < 2; i++){
                        line = reader.readLine();
                        Matcher typeMatcher = typePattern.matcher(line);
                        if(typeMatcher.find()){
                            types[i] = typeMatcher.group(1);
                        }
                    }
                    return types;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return new String[]{"-1"};
    }

    public int[] getMovePool(int natID){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("NatID#"+natID+"\\s*\\{\\s*Name:[A-Za-z -]+");

            int numOfMoves = 0;
            String line;
            while((line = reader.readLine()) != null){
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    Pattern movepoolPattern = Pattern.compile("\\s*MovePool:(\\d+,)*\\s*");
                    while(true){
                        line = reader.readLine();
                        Matcher movepoolMatcher = movepoolPattern.matcher(line);
                        if(movepoolMatcher.find()){
                            for (char c : line.toCharArray()){
                                if(c == ',') numOfMoves++;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            String moveConstrucion = "\\s*MovePool:";
            String moveConstructionAdd = "(\\d+),";

            for(int i = 0; i < numOfMoves; i++){
                moveConstrucion = moveConstrucion + moveConstructionAdd;
            }
            moveConstrucion = moveConstrucion +"\\s*";
            int[] moves = new int[numOfMoves];

            Pattern movesPattern = Pattern.compile(moveConstrucion);
            Matcher movesMatcher = movesPattern.matcher(line);
            if(movesMatcher.find()){
                for(int i = 0; i < moves.length; i++){
                    moves[i] = Integer.parseInt(movesMatcher.group(i+1));
                }
            }
            return moves;
    } catch(IOException e){
            e.printStackTrace();
    }
        return new int[]{-1};
    }

    public String[] pokemon_list(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            Pattern pattern = Pattern.compile("NatID#\\d*\\{Name:(.*)");
            int itemsCounter = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()) {
                    itemsCounter++;
                }
            }
            String[] pokemon_names_list = new String[itemsCounter];
            itemsCounter = 0;

            try (BufferedReader reader2 = new BufferedReader(new FileReader(FILE_PATH))){
                while((line = reader2.readLine()) != null){
                    Matcher matcher = pattern.matcher(line);
                    if(matcher.find()){
                        pokemon_names_list[itemsCounter] = matcher.group(1);
                        itemsCounter++;
                    }
                }
                return pokemon_names_list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch(IOException e){
        e.printStackTrace();
        }
        return new String[] {"-1"};
    }
}

