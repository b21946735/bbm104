import java.util.ArrayList;

public class TVSeries extends Film{
    private String startDate, endDate;
    private String numberOfSeasons,numberOfEpisodes;
    public ArrayList<String> Genres = new ArrayList<String>();
    public ArrayList<String> Writers = new ArrayList<String>();
    public TVSeries(String filmID,String filmTitle,String language,ArrayList<String> Directors,String runtime, String country, ArrayList<String> Cast,ArrayList<String> Genres, ArrayList<String> Writers,String startDate,String endDate,String numberOfSeasons,String numberOfEpisodes){
        this.filmID = filmID;
        this.filmTitle = filmTitle;
        this.language = language;
        this.Directors = Directors;
        this.runtime = runtime;
        this.country = country;
        this.Cast = Cast;
        this.Genres = Genres;
        this.Writers =Writers;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfEpisodes = numberOfEpisodes;
        this.numberOfSeasons = numberOfSeasons;
        this.filmType = "TVSeries";
    }
    public String getEndDate() {
        return endDate;
    }
    public String getNumberOfEpisodes() {
        return numberOfEpisodes;
    }
    public String getNumberOfSeasons() {
        return numberOfSeasons;
    }
    public String getStartDate() {
        return startDate;
    }

}
