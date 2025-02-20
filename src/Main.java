
import java.util.Scanner;










public class Main {
    public static void main(String[] args) throws Exception {
        PokeReader pkRead = new PokeReader();
        MoveReader mvRead = new MoveReader();
        ItemReader itRead = new ItemReader();
        AbilityReader abRead = new AbilityReader();
        PokeBuilder pkBuild = new PokeBuilder();
        Scanner sc = new Scanner(System.in);
        Events event = new Events();


        
        System.out.println("----Bienvenido al simulador de combates pokemon (Formato OU)----\n En este, dos jugadores distintos crean sus equipos para luego tener un combate entre ellos.");

        System.out.println("Empecemos por el jugador 1, como te llamas?");
        Team team1 = new Team(1, sc.nextLine());
        

        System.out.println("Y ahora el jugador 2, cual es tu nombre?");
        Team team2 = new Team(2, sc.nextLine());
        
        
        System.out.println("Ahora que estan creados los equipos, vamos a listarlos brevemente.");
        team1.listTeam();
        team2.listTeam();

        System.out.println("Y, finalmente, que de comienzo el combate!");
        Battlezone battle = new Battlezone(team1, team2);
        
        battle.mainPlayableLoop();

        if(team1.hasLost && team2.hasLost) System.out.println("Vaya, que inesperado, parece que tenemos un empate!");
        else{
            System.out.print("Parece que tenemos un ganador, en concreto ");
            if(team1.hasLost) System.out.println(team2.name);
            else System.out.println(team1.name);
        }

        System.out.println("Espero que os haya gustado el simulador de batallas Pokemon. Muchas gracias por jugar! - Carlos");
    }
}
