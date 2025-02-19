public class Movement {
    public String name;
    public int id;
    public String type;
    public String family;
    public int power;
    public int accuracy;
    public int PP;

    public int cur_PP;

    public boolean disabled;
    public boolean encored;

    public Movement(String movename){
        MoveReader mvRead = new MoveReader();

        this.name = movename;
        this.id = mvRead.get
    }
}
