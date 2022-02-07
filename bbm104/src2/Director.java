public class Director  extends Artist{
    private String agentName;
    public Director(String id, String name, String surname,String country,String agentName){
        this.Id=id;
        this.agentName =agentName;
        this.name =name;
        this.surname =surname;
        this.country = country;
    }
    public String getAgentName() {
        return agentName;
    }
}
