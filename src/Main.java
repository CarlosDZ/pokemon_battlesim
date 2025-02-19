
public class Main {
    public static void main(String[] args) throws Exception {
        PokeReader read = new PokeReader();

        int nat = read.getNatID("Araquanid");

        System.out.println(nat);

        int[] stats = read.getBaseStats(nat);

        for(int i=0;i < stats.length; i++){
            System.out.println(stats[i]);
        }

        int numberOfHabs = read.getNumberOfHabs(nat);
        System.out.println(numberOfHabs);

        String[] habs = read.getHabs(nat);

        for (String hab : habs) {
            System.out.println(hab);
        }

        String[] types = read.getTypes(nat);

        for (String type : types) {
            System.out.println(type);
        }
    }
}
