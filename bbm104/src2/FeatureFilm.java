import java.util.ArrayList;

public class FeatureFilm extends Film {
    private String releaseDate;
    private String budget;
    public ArrayList<String> Genres = new ArrayList<String>();
    public ArrayList<String> Writers = new ArrayList<String>();
    public FeatureFilm(String filmID,String filmTitle,String language,ArrayList<String> Directors,String runtime, String country, ArrayList<String> Cast,ArrayList<String> Genres, String releaseDate, ArrayList<String> Writers , String budget )
    {
        this.filmID = filmID;
        this.filmTitle = filmTitle;
        this.language = language;
        this.Directors = Directors;
        this.runtime = runtime;
        this.country = country;
        this.Cast = Cast;
        this.Genres = Genres;
        this.releaseDate =releaseDate;
        this.Writers =Writers;
        this.budget = budget;
        this.filmType = "FeatureFilm";
    }
    public String getReleaseDate() {
        return releaseDate;
    }
}
