

public class Pokemon {
    public String name;
    public int id;

    public int HP;
    public int ATK;
    public int DEF;
    public int SPATK;
    public int SPDEF;
    public int SPEED;

    public int cur_HP;
    public int cur_ATK;
    public int cur_DEF;
    public int cur_SPATK;
    public int cur_SPDEF;
    public int cur_SPEED;

    public Movement Slot1;
    public Movement Slot2;
    public Movement Slot3;
    public Movement Slot4;

    public String ability_name;
    public int ability_id;

    public String item_name;
    public int item_id;

    public int[] statChanges = {0,0,0,0,0,0};

    public boolean poisoned = false;
    public boolean badly_poisoned = false;
    public boolean sleeping = false;
    public boolean paralized = false;
    public boolean burned = false;
    public boolean frozen = false;
    public boolean KOed = false;

    public boolean confused = false;
    public boolean flinched = false;

    public Pokemon(String name){
        PokeBuilder pkBuild = new PokeBuilder();
        AbilityReader abRead = new AbilityReader();
        ItemReader itRead = new ItemReader();
        PokeReader pkRead = new  PokeReader();

        this.name = name;
        this.id = pkRead.getNatID(name);

        int[] stats = pkBuild.buildFinalStats(id);

        this.HP = this.cur_HP = stats[0];
        this.ATK = this.cur_ATK = stats[1];
        this.DEF = this.cur_DEF = stats[2];
        this.SPATK = this.cur_SPATK = stats[3];
        this.SPDEF = this.cur_SPDEF = stats[4];
        this.SPEED = this.cur_SPEED = stats[5];

        this.ability_name = pkBuild.selectAbility(id);
        this.ability_id = abRead.getID(this.ability_name);

        this.item_name = pkBuild.selectItem();
        this.item_id = itRead.getID(this.item_name);

        int[] moves = pkBuild.selectMovements(id);

        this.Slot1 = new Movement(moves[0]);
        this.Slot2 = new Movement(moves[1]);
        this.Slot3 = new Movement(moves[2]);
        this.Slot4 = new Movement(moves[3]);
    }

}
