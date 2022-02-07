public class ChildActor extends Performer{
    private String age;

    public ChildActor(String id, String name, String surname,String country,String age) {
        this.Id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.age = age;
    }
    public String getAge() {
        return age;
    }
    
}
