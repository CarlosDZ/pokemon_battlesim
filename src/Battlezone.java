import java.util.Random;

public class Battlezone {

    static Team team1;
    static Pokemon act_Pokemon1;
    static Movement selected_mov1;

    static Team team2;
    static Pokemon act_Pokemon2;
    static Movement selected_mov2;

    static int turn = 0;

    public Battlezone(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;

        this.act_Pokemon1 = team1.Pokemon1;
        this.act_Pokemon2 = team2.Pokemon1;
    }

    public void mainPlayableLoop(){
        this.turn++; int [] accioneSinOrdenar = startOfTurn();
        int[] ordenacciones = orderActions(accioneSinOrdenar);

        if(ordenacciones[0] == 1){
            resolveUseractions(team1, accioneSinOrdenar[0]);
            resolveUseractions(team2, accioneSinOrdenar[1]);
        }
        else{
            resolveUseractions(team2, accioneSinOrdenar[1]);
            resolveUseractions(team1, accioneSinOrdenar[0]);
        }

        
    }

    public int[] startOfTurn(){
        System.out.println("Comienza el turno "+this.turn+" !");
        int[] tiposAcciones = {0,0};

        System.out.println("Primero el jugador 1, "+team1.name+".");
        tiposAcciones[0] = team1.selectTurnAction();
        if(tiposAcciones[0] == 1) this.selected_mov1 = act_Pokemon1.selectAttack();

        System.out.println("Ahora el jugador 2, "+team2.name+".");
        tiposAcciones[1] = team2.selectTurnAction();
        if(tiposAcciones[1] == 1) this.selected_mov2 = act_Pokemon2.selectAttack();
        
        return tiposAcciones;
    }

    public int[] orderActions(int[] accionesAOrdenar){
        int[] ordenAcciones = new int[2];

        if(accionesAOrdenar[0] == 3) ordenAcciones = new int[] {1,2};
        else if(accionesAOrdenar[1] == 3) ordenAcciones = new int[] {2,1};
        else if(accionesAOrdenar[0] == 2) ordenAcciones = new int[] {1,2};
        else if(accionesAOrdenar[1] == 2) ordenAcciones = new int[] {2,1};
        else{
            if(this.selected_mov1.prio>this.selected_mov2.prio) ordenAcciones = new int[] {1,2};
            else if(this.selected_mov2.prio>this.selected_mov1.prio) ordenAcciones = new int[] {2,1};
            else{
                if(this.act_Pokemon1.cur_SPEED>this.act_Pokemon2.cur_SPEED) ordenAcciones = new int[] {1,2};
                else if(this.act_Pokemon2.cur_SPEED>this.act_Pokemon1.cur_SPEED) ordenAcciones = new int[] {2,1};
                else{
                    Random rand = new Random();
                    if(rand.nextInt(2) == 0) ordenAcciones = new int[] {1,2};
                    else ordenAcciones = new int[] {2,1};
                }
            }
        }
        return ordenAcciones;
    }

    public void resolveUseractions(Team player, int action){
        Events events = new Events();

        switch (action) {
            case 1-> {
                if(player == team1){
                    System.out.println(act_Pokemon1.name+" uso "+selected_mov1.name+" !");
                    switch (selected_mov1.family) {
                        case "EFFECT" -> {
                            if(selected_mov1.doesItHit()){
                                selected_mov1.getSpecialEffect(act_Pokemon1, selected_mov1, act_Pokemon2);
                            }
                            else System.out.println("El movimiento ha fallado!");
                        }
                            
                        default -> {
                            if(selected_mov1.doesItHit()){
                                selected_mov1.getSpecialEffect(act_Pokemon1, selected_mov1, act_Pokemon2);
                                events.hitDamage(events.calcDamage(act_Pokemon1, selected_mov1, act_Pokemon2), 1);
                            }
                            else System.out.println("El movimiento ha fallado!");
                        }
                    }
                }
                else {
                    System.out.println(act_Pokemon2.name+" uso "+selected_mov2.name+" !");
                    switch (selected_mov2.family) {
                        case "EFFECT" -> {
                            if(selected_mov2.doesItHit()){
                                selected_mov2.getSpecialEffect(act_Pokemon2, selected_mov2, act_Pokemon1);
                            }
                            else System.out.println("El movimiento ha fallado!");
                        }
                            
                        default -> {
                            if(selected_mov2.doesItHit()){
                                selected_mov2.getSpecialEffect(act_Pokemon2, selected_mov2, act_Pokemon1);
                                events.hitDamage(events.calcDamage(act_Pokemon2, selected_mov2, act_Pokemon1), 2);
                            }
                            else System.out.println("El movimiento ha fallado!");
                        }
                    }
                }
            }
            case 2-> {
                player.switchOut();
            }
            case 3-> {
                player.hasLost = true;
            }
        }
    }
}

/*
 * El turno se divide en:
 * Entering
 * Before Hitting
 * Onhit
 * After Hitting
 * EndOfTurn
 * 
 */