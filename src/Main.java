




public class Main {
    public static void main(String[] args) throws Exception {
        PokeReader read = new PokeReader();
        MoveReader mvRead = new MoveReader();
        ItemReader itRead = new ItemReader();
        AbilityReader abRead = new AbilityReader();
        PokeBuilder pkBuild = new PokeBuilder();

        /*
        int nat = read.getNatID("Alomomola");

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

        int[] moves = read.getMovePool(nat);

        for (int move : moves) {
            System.out.println(move+" "+mvRead.getName(move)+"  "+mvRead.getType(move)+"  "+mvRead.getFamily(move)+"  "+mvRead.getPower(move)+"  "+mvRead.getAccur(move)+"  "+mvRead.getPP(move)+"  "+mvRead.hasSideEffect(move));
        }
        

        int itemid = itRead.getID("1/3 HP Berry");

        System.out.println(itRead.getDescription(itemid));
        */
        /*
        int abilityID = abRead.getID("Libero");
        System.out.println(abilityID);
        System.out.println(abRead.getDescription(abilityID));
         

        pkBuild.buildFinalStats(read.getNatID("Cinderace"));
        */
        
        
    }
}
