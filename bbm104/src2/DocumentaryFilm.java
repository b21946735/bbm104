import java.util.ArrayList;

public class DocumentaryFilm extends Film{
    private String releaseDate;
    public DocumentaryFilm(String filmID,String filmTitle,String language,ArrayList<String> Directors,String runtime, String country, ArrayList<String> Cast, String releaseDate) {
        this.filmID = filmID;
        this.filmTitle = filmTitle;
        this.language = language;
        this.Directors = Directors;
        this.runtime = runtime;
        this.country = country;
        this.Cast = Cast;
        this.releaseDate =releaseDate;
        this.filmType = "Documentary";
    }
    public String getReleaseDate() {
        return releaseDate;
    }
}
