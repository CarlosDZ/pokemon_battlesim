public class Battlezone {

    Team team1;
    Pokemon act_Pokemon1;

    Team team2;
    Pokemon act_Pokemon2;

    public Battlezone(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;

        this.act_Pokemon1 = team1.Pokemon1;
        this.act_Pokemon2 = team2.Pokemon1;
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