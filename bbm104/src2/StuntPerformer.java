import java.util.ArrayList;

public class StuntPerformer extends Performer {
    private String height;
    public ArrayList<String> RealActorsID = new ArrayList<String>();

    public StuntPerformer(String id, String name, String surname,String country,String height, ArrayList<String> RealActorsID) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.height = height;
        this.RealActorsID = RealActorsID;
    }
    public String getHeight() {
        return height;
    }

}
