public class Events {

    //Tabla de tipos

    public static final String[] DEBIL_CONTRA_STEEL = {"Fairy", "Rock", "Ice"};
    public static final String[] RESISTE_STEEL = {"Water", "Electric", "Fire", "Steel"};

    public static final String[] DEBIL_CONTRA_FLYING = {"Grass", "Bug", "Fighting"};
    public static final String[] RESISTE_FLYING = {"Electric", "Rock", "Steel"};

    public static final String[] DEBIL_CONTRA_WATER = {"Rock", "Ground", "Fire"};
    public static final String[] RESISTE_WATER = {"Water", "Grass", "Dragon"};

    public static final String[] DEBIL_CONTRA_ICE = {"Flying", "Grass", "Ground", "Dragon"};
    public static final String[] RESISTE_ICE = {"Ice", "Water", "Fire", "Steel"};

    public static final String[] DEBIL_CONTRA_GRASS = {"Water", "Rock", "Ground"};
    public static final String[] RESISTE_GRASS = {"Flying", "Grass", "Bug", "Fire", "Poison", "Dragon", "Steel"};

    public static final String[] DEBIL_CONTRA_BUG = {"Grass", "Psychic", "Dark"};
    public static final String[] RESISTE_BUG = {"Flying", "Fire", "Fighting", "Fairy", "Poison", "Ghost", "Steel"};

    public static final String[] DEBIL_CONTRA_ELECTRIC = {"Flying", "Water"};
    public static final String[] RESISTE_ELECTRIC = {"Electric", "Grass", "Dragon"};
    public static final String[] INMUNE_A_ELECTRIC = {"Ground"};

    public static final String[] RESISTE_NORMAL = {"Rock", "Steel"};
    public static final String[] INMUNE_A_NORMAL = {"Ghost"};

    public static final String[] DEBIL_CONTRA_ROCK = {"Flying", "Ice", "Bug", "Fire"};
    public static final String[] RESISTE_ROCK = {"Ground", "Fighting", "Steel"};

    public static final String[] DEBIL_CONTRA_GROUND = {"Electric", "Rock", "Fire", "Poison", "Steel"};
    public static final String[] RESISTE_GROUND = {"Bug", "Grass"};
    public static final String[] INMUNE_A_GROUND = {"Flying"};

    public static final String[] DEBIL_CONTRA_FIRE = {"Ice", "Grass", "Bug", "Steel"};
    public static final String[] RESISTE_FIRE = {"Water", "Rock", "Fire", "Dragon"};

    public static final String[] DEBIL_CONTRA_FIGHTING = {"Ice", "Rock", "Normal", "Dark", "Steel"};
    public static final String[] RESISTE_FIGHTING = {"Flying", "Bug", "Fairy", "Psychic", "Poison"};
    public static final String[] INMUNE_A_FIGHTING = {"Ghost"};

    public static final String[] DEBIL_CONTRA_FAIRY = {"Fighting", "Dark", "Dragon"};
    public static final String[] RESISTE_FAIRY = {"Fire", "Poison", "Steel"};

    public static final String[] DEBIL_CONTRA_PSYCHIC = {"Fighting", "Poison"};
    public static final String[] RESISTE_PSYCHIC = {"Psychic", "Steel"};
    public static final String[] INMUNE_A_PSYCHIC = {"Dark"};

    public static final String[] DEBIL_CONTRA_POISON = {"Fairy", "Grass"};
    public static final String[] RESISTE_POISON = {"Rock", "Poison", "Ground", "Ghost"};
    public static final String[] INMUNE_A_POISON = {"Steel"};

    public static final String[] DEBIL_CONTRA_DRAGON = {"Dragon"};
    public static final String[] RESISTE_DRAGON = {"Steel"};
    public static final String[] INMUNE_A_DRAGON = {"Fairy"};

    public static final String[] DEBIL_CONTRA_GHOST = {"Psychic", "Ghost"};
    public static final String[] RESISTE_GHOST = {"Dark"};
    public static final String[] INMUNE_A_GHOST = {"Normal"};

    public static final String[] DEBIL_CONTRA_DARK = {"Psychic", "Ghost"};
    public static final String[] RESISTE_DARK = {"Fairy", "Fighting", "Dark"};

















    public int calcDamage(Pokemon attacker, Movement used, Pokemon defender){   //use this only on phys and spe attacks, not effects
        float multiplier = 1;

        switch (used.type) {
            case "Steel":
            //    multiplier = multiplier * (1+buscarTipos(defender, tiposBuscados))
                
                break;
            default:
                throw new AssertionError();
        }

        return 5;

    }

    public int buscarTipos(Pokemon pokemon, String[] tiposBuscados){
        PokeReader pkRead = new PokeReader();

        int encontrados = 0;

        String type1 = pkRead.getTypes(pokemon.id)[0];
        String type2 = pkRead.getTypes(pokemon.id)[1];

        for(int i = 0; i < tiposBuscados.length; i++){
            if(type1.equals(tiposBuscados[i]) || type2.equals(tiposBuscados[i])){
                encontrados = encontrados+1;
            }
        }
        return encontrados;
    }
}
