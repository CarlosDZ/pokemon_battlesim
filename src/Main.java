
public class Main {
    public static void main(String[] args) throws Exception {
        PokeReader read = new PokeReader();

        int nat = read.getNatID("Alomomola");

        System.out.println(nat);
    }
}
