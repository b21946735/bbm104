public class Actor extends Performer{
    private String height;

    public Actor(String id, String name, String surname,String country,String height) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.height = height;
    }
    public String getHeight() {
        return height;
    }
}
