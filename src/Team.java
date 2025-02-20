
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

    public boolean canSwitchOut(){
        int alivePokemons = 0;

        Pokemon[] team = {Pokemon1,Pokemon2,Pokemon3,Pokemon4,Pokemon5,Pokemon6};

        for(int i = 0; i < team.length; i++){
            if(team[i].KOed == false) alivePokemons = alivePokemons+1;
        }

        return alivePokemons>=2;
    }

    public void switchOut(){
        Pokemon active;
        if(this.Player == 1) active = Battlezone.act_Pokemon1;
        else active = Battlezone.act_Pokemon2;
        Scanner sc = new Scanner(System.in);
        Pokemon[] team = {Pokemon1,Pokemon2,Pokemon3,Pokemon4,Pokemon5,Pokemon6};
        System.out.println("---A que pokemon quieres cambiar? (Escribe el numero)---");

        for(int i = 0; i < team.length; i++){
            System.out.println((i+1)+" - "+team[i].name+"       "+team[i].cur_HP+"/"+team[i].HP);
        }
        try {
            int selectedOption = 1;
            do { 
                selectedOption= sc.nextInt();
                if(selectedOption<1 || selectedOption>6) System.out.println("Numero no valido, por favor introduce un numero entre 1 y 6");
                else if (team[selectedOption-1].KOed) System.out.println("No pudes cambiar a un pokemon que esta KO");
                else if (team[selectedOption-1] == active) System.out.println("No puedes cambiar a un pokemon que ya esta activo");
            } while (selectedOption<1 || selectedOption>6 || team[selectedOption-1] == active || team[selectedOption-1].KOed);

            System.out.println(active.name+" ha vuelto a su pokeball.");
            active = team[selectedOption-1];
            System.out.println(active.name+" ha salido al campo de batalla!");

            if(this.Player == 1)  Battlezone.act_Pokemon1 = active;
            else Battlezone.act_Pokemon2 = active;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int selectTurnAction(){
    Pokemon onBattle;
        if(this.Player == 1) onBattle = Battlezone.act_Pokemon1;
        else onBattle = Battlezone.act_Pokemon2;
        Scanner sc = new Scanner(System.in);

        System.out.println("---Que quieres hacer? (Escribe el numero)---\n1 - Atacar\n2 - Cambiar de pokemon\n3 - Rendirme");
        int actionSelected;
        boolean canAttack;
        boolean canSwitch;
        try {
            do {
                canAttack = (onBattle.Slot1.cur_PP>0 ||onBattle.Slot2.cur_PP>0 ||onBattle.Slot3.cur_PP>0 ||onBattle.Slot4.cur_PP>0);
                canSwitch = canSwitchOut();
                actionSelected = sc.nextInt();
                if(actionSelected<1 || actionSelected>3) System.out.println("Numero no valido, por favor, introduce un numero entre 1 y 3");
                else if(actionSelected == 1 && canAttack == false) System.out.println("A tu pokemon no le quedan PP en sus movimientos, selecciona otra opcion");
                else if(actionSelected == 2 && canSwitch == false) System.out.println("No te quedan pokemons a los que cambiar, selecciona otra opcion");
            } while (actionSelected<1 || actionSelected>3 || (actionSelected == 1 && canAttack == false) || (actionSelected == 2 && canSwitch == false));

            return actionSelected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void listTeam(){
        System.out.println("\nEquipo de "+this.name+":\n");
        PokeReader pkRead = new PokeReader();
        Pokemon[] team = {Pokemon1,Pokemon2,Pokemon3,Pokemon4,Pokemon5,Pokemon6};
        for(int i = 0; i < 6; i++){
            System.out.println((i+1)+" - "+team[i].name+"  #"+team[i].id+"  --"+team[i].ability_name+"--  "+"      "+pkRead.getTypes(team[i].id)[0]+"  "+pkRead.getTypes(team[i].id)[1]);
            System.out.println("    - "+team[i].Slot1.name+"\n    - "+team[i].Slot2.name+"\n    - "+team[i].Slot3.name+"\n    - "+team[i].Slot4.name);
        }
        System.out.println("\n");
    }
}
