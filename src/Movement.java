public class Movement {
    public String name;
    public int id;
    public String type;
    public String family;
    public int power;
    public int accuracy;
    public int PP;

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
    }
}
