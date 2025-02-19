
import java.util.Scanner;










public class Main {
    public static void main(String[] args) throws Exception {
        PokeReader pkRead = new PokeReader();
        MoveReader mvRead = new MoveReader();
        ItemReader itRead = new ItemReader();
        AbilityReader abRead = new AbilityReader();
        PokeBuilder pkBuild = new PokeBuilder();
        Scanner sc = new Scanner(System.in);

        System.out.println("----Bienvenido al simulador de combates pokemon (Formato OU)----\n En este, dos jugadores distintos crean sus equipos para luego tener un combate entre ellos.");

        System.out.println("Empecemos por el jugador 1, como te llamas?");
        try {
            Team team1 = new Team(1, sc.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Y ahora el jugador 2, cual es tu nombre?");
        try {
            Team team2 = new Team(2, sc.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
