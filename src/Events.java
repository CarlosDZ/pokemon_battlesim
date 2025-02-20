import java.util.Random;

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

        Random random = new Random();
        float multiplier = 1;
        int variation = random.nextInt(16) + 85; //    [85-100]

        switch (used.type) {
            case "Steel":
                multiplier = multiplier * (1+buscarTipos(defender, DEBIL_CONTRA_STEEL)-buscarTipos(defender, RESISTE_STEEL));
                break;  
            case "Flying":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_FLYING)-buscarTipos(defender, RESISTE_FLYING)));
                break;
            case "Water":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_WATER)-buscarTipos(defender, RESISTE_WATER)));
                break;
            case "Ice":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_ICE)-buscarTipos(defender, RESISTE_ICE)));
                break;
            case "Grass":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_GRASS)-buscarTipos(defender, RESISTE_GRASS)));
                break;
            case "Bug":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_BUG)-buscarTipos(defender, RESISTE_BUG)));
                break;
            case "Electric":
                if(buscarTipos(defender, INMUNE_A_ELECTRIC) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_ELECTRIC)-buscarTipos(defender, RESISTE_ELECTRIC)));
                break;
            case "Normal":
                if(buscarTipos(defender, INMUNE_A_NORMAL) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, -buscarTipos(defender, RESISTE_NORMAL));
                break;
            case "Rock":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_ROCK)-buscarTipos(defender, RESISTE_ROCK)));
                break;
            case "Ground":
                if(buscarTipos(defender, INMUNE_A_GROUND) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_GROUND)-buscarTipos(defender, RESISTE_GROUND)));  
                break;
            case "Fire":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_FIRE)-buscarTipos(defender, RESISTE_FIRE)));
                break;
            case "Fighting":
                if(buscarTipos(defender, INMUNE_A_FIGHTING) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_FIGHTING)-buscarTipos(defender, RESISTE_FIGHTING)));  
                break;
            case "Fairy":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_FAIRY)-buscarTipos(defender, RESISTE_FAIRY)));
                break;
            case "Psychic":
                if(buscarTipos(defender, INMUNE_A_PSYCHIC) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_PSYCHIC)-buscarTipos(defender, RESISTE_PSYCHIC)));  
                break;
            case "Poison":
                if(buscarTipos(defender, INMUNE_A_POISON) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_POISON)-buscarTipos(defender, RESISTE_POISON)));
                break;
            case "Dragon":
                if(buscarTipos(defender, INMUNE_A_DRAGON) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_DRAGON)-buscarTipos(defender, RESISTE_DRAGON)));
                break;
            case "Ghost":
                if(buscarTipos(defender, INMUNE_A_GHOST) == 1) multiplier = 0;
                else multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_GHOST)-buscarTipos(defender, RESISTE_GHOST)));  
                break;
            case "Dark":
                multiplier = multiplier * (float)Math.pow(2, (buscarTipos(defender, DEBIL_CONTRA_DARK)-buscarTipos(defender, RESISTE_DARK)));
                break;
        }

        int attackingstat;
        int defendingstat;
        if(used.family.equals("PHYS")){
            attackingstat = attacker.cur_ATK;
            defendingstat = defender.cur_DEF;
        }
        else{
            attackingstat = attacker.cur_SPATK;
            defendingstat = defender.cur_SPDEF;
        }

        float stab = 1;
        String[] type = {used.type};
        if(buscarTipos(attacker, type) >0) stab = (float) 1.5;

        int damage = (int)(0.01 * stab * multiplier * variation * (((0.2*attacker.lvl+1)*attackingstat*used.power)/(25*defendingstat)+2));

        return damage;

    }

    public void hitDamage(int damage, int activeTeam){
        if(activeTeam == 1){
            System.out.println(Battlezone.act_Pokemon1.name+" ha atacado a "+Battlezone.act_Pokemon2.name+" con "+Battlezone.selected_mov1.name+" !");
            if(damage == 0) System.out.println("Pero el movimeinto no le afecto!");
            else if(damage < Battlezone.act_Pokemon2.cur_HP){
                Battlezone.act_Pokemon2.cur_HP = Battlezone.act_Pokemon2.cur_HP - damage;
                System.out.println("Ahora "+Battlezone.act_Pokemon2.name+" esta a "+Battlezone.act_Pokemon2.cur_HP+" HP!");
            }
            else{
                Battlezone.act_Pokemon2.cur_HP = 0;
                Battlezone.act_Pokemon2.KOed = true;
                System.out.println("El ataque le ha hecho un KO al "+Battlezone.act_Pokemon2.name+" rival!!");
            }
        }
        else{
            System.out.println(Battlezone.act_Pokemon2.name+" ha atacado a "+Battlezone.act_Pokemon1.name+" con "+Battlezone.selected_mov2.name+" !");
            if(damage == 0) System.out.println("Pero el movimeinto no le afecto!");
            else if(damage < Battlezone.act_Pokemon1.cur_HP){
                Battlezone.act_Pokemon1.cur_HP = Battlezone.act_Pokemon1.cur_HP - damage;
                System.out.println("Ahora "+Battlezone.act_Pokemon1.name+" esta a "+Battlezone.act_Pokemon1.cur_HP+" HP!");
            }
            else{
                Battlezone.act_Pokemon1.cur_HP = 0;
                Battlezone.act_Pokemon1.KOed = true;
                System.out.println("El ataque le ha hecho un KO al "+Battlezone.act_Pokemon1.name+" rival!!");
            }
        }
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
