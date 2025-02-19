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

        int[] team_ids = new int[6];
        int[] aviable_pokemons = pkRead.pokemon_list();

        System.out.println("---Estos son los pokemons disponibles para tu equipo---");

        for(int i = 0; i < aviable_pokemons.length; i++){
            System.out.println(pkRead.);
        }



    }

}
