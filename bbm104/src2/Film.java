import java.util.ArrayList;

public class Film {
    protected String filmType;
    protected String ratingScore = "";
    protected String filmID;
    protected String filmTitle;
    protected String language;
    protected String runtime;
    protected String country;
    protected String voteCount;
    public ArrayList<String> Directors = new ArrayList<String>();
    public ArrayList<String> Cast = new ArrayList<String>();
    public String GetRatingScore(){
        return ratingScore;
    }
}