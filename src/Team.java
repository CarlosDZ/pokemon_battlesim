
import java.util.Scanner;

public class Team {
    public int Player;
    public String name;

    public Pokemon Pokemon1;
    public Pokemon Pokemon2;
    public Pokemon Pokemon3;
    public Pokemon Pokemon4;
    public Pokemon Pokemon5;
    public Pokemon Pokemon6;

    public boolean hasLost = false;

    public Team(int player,String name){
        PokeReader pkRead = new PokeReader();
        Scanner sc = new Scanner(System.in);
        this.name = name;
        this.Player = player;

        String[] team_names = new String[6];
        String[] aviable_pokemons = pkRead.pokemon_list();

        System.out.println("Muy bien "+name+", empecemos por crear tu equipo Pokemon");
        System.out.println("---Estos son los pokemons disponibles para tu equipo---");

        int poke_id;
        int[] base_stats;
        for(int i = 0; i < aviable_pokemons.length; i++){
            poke_id = pkRead.getNatID(aviable_pokemons[i]);
            System.out.println("\n"+(i+1)+" - "+aviable_pokemons[i]+"  ||  "+pkRead.getTypes(poke_id)[0]+", "+pkRead.getTypes(poke_id)[1]);
            System.out.print("Abilities: ");
            for(int l = 0; l < pkRead.getNumberOfHabs(poke_id); l++){
                System.out.print(pkRead.getHabs(poke_id)[l]+"       ");
            }
            base_stats = pkRead.getBaseStats(poke_id);

            System.out.println("\nHP: "+base_stats[0]+"    ATK: "+base_stats[1]+"    DEF: "+base_stats[2]+"    SP.ATK: "+base_stats[3]+"    SP.DEF: "+base_stats[4]+"    SPEED: "+base_stats[5]);
        }

        System.out.println("---A continuacion, introduce el numero del pokemon que quieras seleccionar---");
        int[] selection_codes = {0,0,0,0,0,0};
        int selected_pokemon = 0;
        
        for(int i = 0; i < selection_codes.length; i++){
            try {
                do { 
                    System.out.println("Elige al pokemon "+(i+1)+"/6");
                    selected_pokemon = sc.nextInt();
                    if(selected_pokemon<1 || selected_pokemon > aviable_pokemons.length || selected_pokemon == selection_codes[0]|| selected_pokemon == selection_codes[1]|| selected_pokemon == selection_codes[2]|| selected_pokemon == selection_codes[3]|| selected_pokemon == selection_codes[4]) 
                        System.out.println("Numero no valido, por favor, prueba con otro. AVISO: No se permiten pokemon duplicados en el equipo.");
                } while (selected_pokemon<1 || selected_pokemon > aviable_pokemons.length || selected_pokemon == selection_codes[0]|| selected_pokemon == selection_codes[1]|| selected_pokemon == selection_codes[2]|| selected_pokemon == selection_codes[3]|| selected_pokemon == selection_codes[4]);
                selection_codes[i] = selected_pokemon;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        team_names[0] = aviable_pokemons[(selection_codes[0]-1)];
        team_names[1] = aviable_pokemons[(selection_codes[1]-1)];
        team_names[2] = aviable_pokemons[(selection_codes[2]-1)];
        team_names[3] = aviable_pokemons[(selection_codes[3]-1)];
        team_names[4] = aviable_pokemons[(selection_codes[4]-1)];
        team_names[5] = aviable_pokemons[(selection_codes[5]-1)];

        this.Pokemon1 = new Pokemon(team_names[0]);
        this.Pokemon2 = new Pokemon(team_names[1]);
        this.Pokemon3 = new Pokemon(team_names[2]);
        this.Pokemon4 = new Pokemon(team_names[3]);
        this.Pokemon5 = new Pokemon(team_names[4]);
        this.Pokemon6 = new Pokemon(team_names[5]);


    }

}
