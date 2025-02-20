
import java.util.Scanner;



public class Pokemon {
    public String name;
    public int id;
    public int lvl;

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
        this.lvl = PokeBuilder.pokemon_lvl;

        System.out.println("Ahora vamos a personalizar a tu "+ name);

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

    public void selectAttack(){
        Scanner sc = new Scanner(System.in);

        System.out.println("---Que ataque quieres utilizar?---\n1 - "+Slot1.name+"    "+Slot1.cur_PP+"/"+Slot1.PP+"    "+Slot1.type);
        System.out.println("-2 - "+Slot2.name+"    "+Slot2.cur_PP+"/"+Slot1.PP+"    "+Slot2.type);
        System.out.println("3 - "+Slot3.name+"    "+Slot3.cur_PP+"/"+Slot1.PP+"    "+Slot3.type);
        System.out.println("4 - "+Slot4.name+"    "+Slot4.cur_PP+"/"+Slot1.PP+"    "+Slot4.type);
        try {
            int movSelection = 0;
            Movement selectedSlot = Slot1;
            boolean hasPP;
            do { 
                movSelection = sc.nextInt();
                if(movSelection >0 && movSelection<5){
                    switch (movSelection) {
                        case 1 -> selectedSlot = Slot1;
                        case 2 -> selectedSlot = Slot2;
                        case 3 -> selectedSlot = Slot3;
                        case 4 -> selectedSlot = Slot4;
                    }
                    if(selectedSlot.cur_PP>0) hasPP = true;
                    else hasPP = false;
                }
                else hasPP = true;
                if(movSelection<1 || movSelection>4) System.out.println("El numero introducido no es valido, ha de ser un numero del 1 al 4");
                else if(hasPP == false) System.out.println("Al movimiento seleccionado no le quedan PP, por favor, selecciona otro");
            } while (movSelection<1 || movSelection>4 || hasPP == false);

            selectedSlot.cur_PP = selectedSlot.cur_PP - 1;
            //funcion que pone el movimiento en la funcion de cola con su prioridad y la velocidad de su usuario

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
