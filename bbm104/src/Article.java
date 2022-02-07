public class Article {
    private String paperID;
    private String name;
    private String publisherName;
    private String publisherYear;

    public Article(String paperID, String name, String publisherName,String publisherYear) {
        this.paperID= paperID;
        this.name= name;
        this.publisherName= publisherName;
        this.publisherYear= publisherYear;
    }
    public String getPaperID() {
        return paperID;
    }
    public String getEverything() {
        return  "+" + paperID + "\t" + name +"\t" + publisherName + "\t" + publisherYear;
    }
}
