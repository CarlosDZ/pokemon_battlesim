public class Team {
    public int Player;

    public Pokemon Pokemon1;
    public Pokemon Pokemon2;
    public Pokemon Pokemon3;
    public Pokemon Pokemon4;
    public Pokemon Pokemon5;
    public Pokemon Pokemon6;

    public boolean hasLost = false;

    public Team(int player){
        PokeReader pkRead = new PokeReader();

        this.Player = player;

        String[] team_names = new String[6];
        String[] aviable_pokemons = pkRead.pokemon_list();

        System.out.println("---Estos son los pokemons disponibles para tu equipo---");

        int poke_id;
        int[] base_stats;
        for(int i = 0; i < aviable_pokemons.length; i++){
            poke_id = pkRead.getNatID(aviable_pokemons[i]);
            System.out.println("\n"+aviable_pokemons[i]+"  ||  "+pkRead.getTypes(poke_id)[0]+", "+pkRead.getTypes(poke_id)[1]);
            System.out.print("Abilities: ");
            for(int l = 0; l < pkRead.getNumberOfHabs(poke_id); l++){
                System.out.print(pkRead.getHabs(poke_id)[l]+"       ");
            }
            base_stats = pkRead.getBaseStats(poke_id);

            System.out.println("\nHP: "+base_stats[0]+"    ATK: "+base_stats[1]+"    DEF: "+base_stats[2]+"    SP.ATK: "+base_stats[3]+"    SP.DEF: "+base_stats[4]+"    SPEED: "+base_stats[5]);
        }



    }

}
