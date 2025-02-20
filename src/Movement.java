
import java.util.Random;

public class Movement {
    public String name;
    public int id;
    public String type;
    public String family;
    public int power;
    public int accuracy;
    public int PP;
    public boolean specialEffect;

    public int cur_PP;

    public boolean disabled = false;
    public boolean encored = false;

    public Movement(int move_id){
        MoveReader mvRead = new MoveReader();

        this.name = mvRead.getName(move_id);
        this.id = move_id;
        this.type = mvRead.getType(move_id);
        this.family = mvRead.getFamily(move_id);
        this.power = mvRead.getPower(move_id);
        this.accuracy = mvRead.getAccur(move_id);
        this.PP = this.cur_PP = mvRead.getPP(move_id);
        this.specialEffect = mvRead.hasSideEffect(move_id);
    }

    public boolean doesItHit(){
        Random random = new Random();
        if(this.accuracy == 101) return true;
        else{
            int numeroAleatorio = random.nextInt(100) + 1;
            return numeroAleatorio <= this.accuracy;
        }
    }
    public void getSpecialEffect(Pokemon attacker, Movement movement, Pokemon defender) {
        if(movement.specialEffect){
            switch (movement.id) {
                case 1-> Acrobatics(attacker);
                case 2-> Alluring_Voice(defender);
                case 4-> generic_chanceToFreeze(10);
                case 5-> generic_chanceToParalyze(30);
                case 6-> Calm_Mind(attacker);
                case 7-> Facade(attacker);
                case 8-> generic_switchOutOnHit();
                case 10-> generic_chanceToFreeze(10);
                case 11-> generic_chanceToLowerReceiverStats(new String[] {"DEF"},20);
                case 12-> Pain_Split(attacker,defender);
                case 13-> generic_chanceToLowerReceiverStats(new String[] {"ATK"},10);
                case 14-> Protect(attacker);
                case 15-> generic_chanceToLowerReceiverStats(new String[] {"SPDEF"},10);
                case 16-> generic_chanceToBurn(30);
                case 17-> generic_chanceToLowerReceiverStats(new String[] {"SPDEF"},20);
                case 18-> Surf(defender);
                case 19-> generic_chanceToFlinch(20);
                case 20-> generic_chanceToFlinch(20);
                case 21-> generic_chanceToLowerReceiverStats(new String[] {"SPDEF"},10);
                case 22-> generic_chanceToLowerReceiverStats(new String[] {"ATK"},100);
                case 23-> generic_chanceToLowerReceiverStats(new String[] {"DEF"},20);
                case 24-> generic_Vampiric(50);
                case 25-> generic_Vampiric(50);
                case 26-> generic_chanceToChangeUserStats(new String[] {"SPEED"},new int[] {1},100);
                case 27-> generic_Vampiric(50);
                case 28-> Earthquake(defender);
                case 29-> generic_chanceToBurn(10);
                case 30-> generic_chanceToBurn(10);
                case 31-> Soft_Boiled(attacker);
                case 32-> generic_chanceToChangeUserStats(new String[] {"SPEED"},new int[] {2},100);
                case 33-> generic_chanceToChangeUserStats(new String[] {"ATK"},new int[] {2},100);
                case 34-> generic_chanceToBurn(10);
                case 35-> generic_chanceToPoison(30);
                case 36-> generic_switchOutOnHit();
                case 37-> generic_chanceToChangeUserStats(new String[] {"SPATK"},new int[] {-2},100);
                case 38-> generic_chanceToFlinch(30);
                case 39-> High_Jump_Kick();
                case 40-> generic_chanceToParalyze(10);
                case 41-> generic_chanceToParalyze(100);
                case 42-> Knock_Off(defender);
                case 43-> generic_Vampiric(75);
                case 44-> generic_chanceToParalyze(30);
                case 45-> generic_chanceToChangeUserStats(new String[] {"ATK","DEF"},new int[] {1,1},100);
                case 46-> Roost(attacker);
                case 47-> generic_chanceToConfuse(30);
                case 48-> Seismic_Toss(attacker,defender);



                default -> noEffectsMovement();
            }
        }
    }

    public void noEffectsMovement(){}
    public void generic_chanceToFreeze(int chance){}
    public void generic_chanceToParalyze(int chance){}
    public void generic_chanceToBurn(int chance){}
    public void generic_chanceToPoison(int chance){}
    public void generic_chanceToFlinch(int chance){}
    public void generic_chanceToConfuse(int chance){}
    public void generic_switchOutOnHit(){}
    public void generic_chanceToLowerReceiverStats(String[] stats,int chance){}
    public void generic_chanceToChangeUserStats(String[] stats,int[]quantity,int chance){}
    public void generic_Vampiric(int percentage_healed){}

    public void Acrobatics(Pokemon user){
        if(user.item_id == 0) this.power = 110;
        else this.power=55;
    }
    public void Alluring_Voice(Pokemon receiver){}
    public void Calm_Mind(Pokemon user){
        if(user.statChanges[3]<6) {
            user.statChanges[3] = user.statChanges[3] + 1;
            System.out.println("El ataque especial de "+user.name+" ha aumentado!");
        }
        else System.out.println("El ataque especial de "+user.name+" no puede subir mas!");
        if(user.statChanges[4]<6) {
            user.statChanges[4] = user.statChanges[4] + 1;
            System.out.println("La defensa especial de "+user.name+" ha aumentado!");
        }
        else System.out.println("La defensa especial de "+user.name+" no puede subir mas!");
    }
    public void Facade(Pokemon user){
        if(user.badly_poisoned || user.burned || user.poisoned ||user.paralyzed) this.power = 140;
        else this.power = 70;
    }
    public void Pain_Split(Pokemon user, Pokemon rival){
        int totalHP = user.cur_HP + rival.cur_HP;
        System.out.println("Los puntos de vida se han repartido entre ambos pokemon!");

        if(user.HP > (totalHP/2)) user.cur_HP = totalHP/2;
        else user.cur_HP = user.HP;
        System.out.println("Los HP de "+user.name+" ahora son de "+user.cur_HP);

        if(rival.HP > (totalHP/2)) rival.cur_HP = totalHP/2;
        else rival.cur_HP = rival.HP;
        System.out.println("Los HP de "+rival.name+" ahora son de "+rival.cur_HP);
    }
    public void Protect(Pokemon user){}
    public void Surf(Pokemon defender){}
    public void Earthquake(Pokemon defender){}
    public void Soft_Boiled(Pokemon user){}
    public void High_Jump_Kick(){}
    public void Knock_Off(Pokemon defender){}
    public void Roost(Pokemon user){}
    public void Seismic_Toss(Pokemon attacker, Pokemon defender){}



}
