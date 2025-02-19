import java.util.Scanner;

public class PokeBuilder {

    public static final int pokemon_lvl = 100;
    public static final int pokemon_base_ivs = 31;


    public int[] buildFinalStats(int poke_id){
        Scanner sc = new Scanner(System.in);
        PokeReader pkRead = new PokeReader();

        System.out.println("Lo primero es decidir la naturaleza de tu pokemon, a continuacion mostrare una lista de posibilidades simplificadas:\n1 - Neutra\n2 - [+ATK -DEF]\n3 - [+ATK -SP.ATK]\n4 - [+ATK -SP.DEF]\n5 - [+ATK -SPEED]\n6 - [+DEF -ATK]\n7 - [+DEF -SP.ATK]\n8 - [+DEF -SP.DEF]\n9 - [+DEF -SPEED]\n10 - [+SP.ATK -ATK]\n11 - [+SP.ATK -DEF]\n12 - [+SP.ATK -SP.DEF]\n13 - [+SP.ATK -SPEED]\n14 - [+SP.DEF -ATK]\n15 - [+SP.DEF -DEF]\n16 - [+SP.DEF -SP.ATK]\n17 - [+SP.DEF -SPEED]\n18 - [+SPEED -ATK]\n19 - [+SPEED -DEF]\n20 - [+SPEED -SP.ATK]\n21 - [+SPEED -SP.DEF]\n\n---Escribe el numero de la naturaleza a escoger---");
        int naturaleza = 0;
        try {
            do { 
                naturaleza=sc.nextInt();
            } while (naturaleza < 1 || naturaleza > 21);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] nature_array = {0,0,0,0,0,0};
        switch (naturaleza) {
            case 1 -> {}
            case 2 -> {nature_array[1]= 10;nature_array[2]= -10;}
            case 3 -> {nature_array[1]= 10;nature_array[3]= -10;}
            case 4 -> {nature_array[1]= 10;nature_array[4]= -10;}
            case 5 -> {nature_array[1]= 10;nature_array[5]= -10;}
            case 6 -> {nature_array[2]= 10;nature_array[1]= -10;}
            case 7 -> {nature_array[2]= 10;nature_array[3]= -10;}
            case 8 -> {nature_array[2]= 10;nature_array[4]= -10;}
            case 9 -> {nature_array[2]= 10;nature_array[5]= -10;}
            case 10 -> {nature_array[3]= 10;nature_array[1]= -10;}
            case 11 -> {nature_array[3]= 10;nature_array[2]= -10;}
            case 12 -> {nature_array[3]= 10;nature_array[4]= -10;}
            case 13 -> {nature_array[3]= 10;nature_array[5]= -10;}
            case 14 -> {nature_array[4]= 10;nature_array[1]= -10;}
            case 15 -> {nature_array[4]= 10;nature_array[2]= -10;}
            case 16 -> {nature_array[4]= 10;nature_array[3]= -10;}
            case 17 -> {nature_array[4]= 10;nature_array[5]= -10;}
            case 18 -> {nature_array[5]= 10;nature_array[1]= -10;}
            case 19 -> {nature_array[5]= 10;nature_array[2]= -10;}
            case 20 -> {nature_array[5]= 10;nature_array[3]= -10;}
            case 21 -> {nature_array[5]= 10;nature_array[4]= -10;}
        }

        int remaining_ev = 508;
        int chosen_stat = 0;
        System.out.println("Ahora vamos a decidir los EV, tienes 508, para repartir entre todas las estadisticas a maximo de 252 en una sola estadistica.");
        int[] EVs = {0,0,0,0,0,0};
        do { 
            System.out.println("A que estadistica le quieres asignar EVs?\n1 - HP\n2 - ATK\n3 - DEF\n4 - SP.ATK\n5 - SP.DEF\n6 - SPEED\n\n---Escribe el numero de la estadistica a escoger---");
            try {
                do { 
                    chosen_stat = sc.nextInt();
                    if(chosen_stat < 1 || chosen_stat > 6) System.out.println("Numero no valido, prueba con otro.");
                } while (chosen_stat < 1 || chosen_stat > 6);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int evs_to_assign = 0;
            System.out.println("Cuantos EV le quieres asignar? (Maximo por estadistica: 252) (Restante: "+remaining_ev+")\n\n---Escribe el numero de EV---");
            try {
                do { 
                    evs_to_assign = sc.nextInt();
                    if(evs_to_assign<1||evs_to_assign>252||evs_to_assign>remaining_ev) System.out.println("Cantidad no valida, pruba con otra");
                } while (evs_to_assign<1||evs_to_assign>252||evs_to_assign>remaining_ev);
            } catch (Exception e) {
                e.printStackTrace();
            }
            EVs[chosen_stat-1] = EVs[chosen_stat-1]+evs_to_assign;
            remaining_ev = remaining_ev - evs_to_assign;
        } while (remaining_ev > 0);

        int[] baseStats = pkRead.getBaseStats(poke_id);
        int[] finalStats = new int[6];

        for(int i = 0; i < finalStats.length; i++){
            if(i == 0){
                finalStats[i] = (int) (((2*baseStats[i])+pokemon_base_ivs+(float)(EVs[i]/4))*(float)(pokemon_lvl/100)+pokemon_lvl+10);
            }
            else{
                finalStats[i]= (int) ((((2*baseStats[i])+pokemon_base_ivs+(float)(EVs[i]/4))*(float)(pokemon_lvl/100)+5)*(float)((float)(100+nature_array[i])/100));
            }
        }
        System.out.println("Creacion de estadisticas completada. Las estadisticas de tu pokemon son:\nHP: "+finalStats[0]+"\nATK: "+finalStats[1]+"\nDEF: "+finalStats[2]+"\nSP.ATK: "+finalStats[3]+"\nSP.DEF: "+finalStats[4]+"\nSPEED: "+finalStats[5]);

        return finalStats;
    }

    public String selectAbility(int poke_id){
        PokeReader pkRead = new PokeReader();
        AbilityReader abRead = new AbilityReader();
        Scanner sc = new Scanner(System.in);

        int numOfHabs = pkRead.getNumberOfHabs(poke_id);
        String[] aviableAbilities = pkRead.getHabs(poke_id);

        System.out.println("---Estas son las habilidades disponibles para tu pokemon---");
        for(int i = 0; i < numOfHabs; i++){
            System.out.println(i+1+" - "+aviableAbilities[i]+":\n       "+abRead.getDescription(abRead.getID(aviableAbilities[i])));
        }

        System.err.println("---Que habilidad quieres elegit? (Escribe el numero)---");
        int abilitySelector;
        try {
            do { 
                abilitySelector = sc.nextInt();
            } while (abilitySelector<1 || abilitySelector>numOfHabs);
            String finalAbility = aviableAbilities[abilitySelector-1];
            return finalAbility;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public String selectItem(){
        ItemReader itRead = new ItemReader();
        String[] itemList = itRead.listItems();
        Scanner sc = new Scanner(System.in);

        System.out.println("Estos son los objetos disponibles:");
        for(int i = 0; i < itemList.length; i++){
            System.out.println(i+1+" - "+itemList[i]+":\n       "+itRead.getDescription(itRead.getID(itemList[i])));
        }

        int selectedItem = 1;
        System.out.println("\n\n---Escribe el numero del objeto que quieres equipar a tu pokemon---");
        try {
            do { 
                if(selectedItem < 1 || selectedItem > itemList.length) System.out.println("Este numero no es valido. Prueba con otro");
                selectedItem = sc.nextInt();
            } while (selectedItem < 1 || selectedItem > itemList.length);
            String equipedItem = itemList[selectedItem-1];
            return equipedItem;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public String[] selectMovements(int poke_id){
        PokeReader pkRead = new PokeReader();
        MoveReader mvRead = new MoveReader();
        Scanner sc = new Scanner(System.in);

        int[] movePool = pkRead.getMovePool(poke_id);
        String[] movePoolNames = mvRead.getMovePoolNames(movePool);

        int accuracy;

        System.out.println("---Estos son los movimientos que tu pokemon tiene disponibles---");
        for(int i = 0; i < movePool.length; i++){
            System.out.println(i+1+" - "+ movePoolNames[i]+"\n  "+mvRead.getDescription(movePool[i]));
            String family = mvRead.getFamily(movePool[i]);
            System.out.println("Type: "+mvRead.getType(movePool[i])+"       Type of Attack: "+family);
            if(family.equals("PHYS")||family.equals("SPE")) System.out.print("Power: "+mvRead.getPower(movePool[i]));

            accuracy = mvRead.getAccur(movePool[i]);
            if(accuracy == 101)
                System.out.print("      Accuracy: Cant Miss");

            else
                System.out.print("      Accuracy: "+accuracy);
            System.out.println("      PP: "+mvRead.getPP(movePool[i]));
            
        }

        System.out.print("\n\n");

        int[] previouslySelected = {-1,-1,-1,-1};
        int selectedMov = 1;
        for(int i = 0; i<4;i++){
            System.out.println("Selecciona el movimiento numero "+(i+1)+"/4   (Escribe el numero a continuacion)");
            try {
                do { 
                    selectedMov = sc.nextInt();
                    if(selectedMov<1 || selectedMov>movePool.length || selectedMov == previouslySelected[0] || selectedMov == previouslySelected[1] || selectedMov == previouslySelected[2])
                    System.out.println("El numero introducido no es valido, prueba con otro.");

                } while (selectedMov<1 || selectedMov>movePool.length || selectedMov == previouslySelected[0] || selectedMov == previouslySelected[1] || selectedMov == previouslySelected[2]);
                previouslySelected[i] = selectedMov;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String[] Movements = mvRead.getMovePoolNames(previouslySelected);
        return Movements;
    }
}
