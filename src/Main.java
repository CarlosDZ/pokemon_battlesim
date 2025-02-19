
public class Main {
    public static void main(String[] args) throws Exception {
        PokeReader read = new PokeReader();

        int nat = read.getNatID("Alomomola");

        System.out.println(nat);

        int[] stats = read.getBaseStats(nat);

        for(int i=0;i < stats.length; i++){
            System.out.println(stats[i]);
        }
    }
}
