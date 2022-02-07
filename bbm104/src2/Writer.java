public class Writer extends Artist{
    private String writingType;

    public Writer(String id, String name, String surname,String country, String writingType) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.writingType = writingType;
    }
    public String getWritingType() {
        return writingType;
    }
    
}
