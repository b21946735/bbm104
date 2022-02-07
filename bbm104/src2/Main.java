import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;




public class Main {

    public ArrayList<Film> Films = new ArrayList<Film>();
    public ArrayList<Person> Persons = new ArrayList<Person>();
    public ArrayList<String> CastNames = new ArrayList<String>();
    public ArrayList<String> WritersNames = new ArrayList<String>();
    public ArrayList<String> DirectorsNames = new ArrayList<String>();
    public static void main(String[] args) {
        try {
            BufferedWriter output2 = new BufferedWriter(new FileWriter(args[3])); // Clean Txt
            output2.close();
        } catch (Exception e) {
            
        }
        
       
        Main asd = new Main();
        asd.ReadInput(new String[] {args[0],args[1], args[2], args[3]});
    }

    public void ReadInput(String[] args){ // Method for read input txts
        try {
            String peopleCurrentLine;
            BufferedReader peopleTxt = new BufferedReader(new FileReader(args[0])); // Read args[0] people txt and create people objects
            while ((peopleCurrentLine = peopleTxt.readLine()) != null) {
                String[] strr = peopleCurrentLine.split("\t");
                if (strr[0].equals("Director:")) 
                    Persons.add(new Director(strr[1],strr[2],strr[3],strr[4],strr[5]));
                else if (strr[0].equals("Writer:"))
                    Persons.add(new Writer((strr[1]),strr[2],strr[3],strr[4],strr[5]));
                else if (strr[0].equals("Actor:"))
                    Persons.add(new Actor((strr[1]),strr[2],strr[3],strr[4],(strr[5])));
                else if (strr[0].equals("ChildActor:"))
                    Persons.add(new ChildActor(strr[1],strr[2],strr[3],strr[4],strr[5]));
                else if (strr[0].equals("StuntPerformer:"))
                    Persons.add(new StuntPerformer((strr[1]),strr[2],strr[3],strr[4],(strr[5]),new ArrayList<>(Arrays.asList(strr[6].split(",")))));
                else if (strr[0].equals("User:"))
                    Persons.add(new User((strr[1]),strr[2],strr[3],strr[4]));
            }
            
            peopleTxt.close();
           
            String filmCurrentLine;
            BufferedReader filmTxt = new BufferedReader(new FileReader(args[1])); // Read args[1] film txt and create film objects
            while ((filmCurrentLine = filmTxt.readLine()) !=null){
                String[] strr = filmCurrentLine.split("\t");
               
            
                if (strr[0].equals("FeatureFilm:")) 
                    Films.add(new FeatureFilm(strr[1],strr[2],strr[3],new ArrayList<>(Arrays.asList(strr[4].split(","))),strr[5],strr[6],new ArrayList<>(Arrays.asList(strr[7].split(","))),new ArrayList<>(Arrays.asList(strr[8].split(","))),strr[9],new ArrayList<>(Arrays.asList(strr[10].split(","))),strr[11]));
                else if (strr[0].equals("ShortFilm:"))
                    if (Integer.parseInt(strr[5])<=40) {
                        Films.add(new ShortFilm(strr[1],strr[2],strr[3],new ArrayList<>(Arrays.asList(strr[4].split(","))),strr[5],strr[6],new ArrayList<>(Arrays.asList(strr[7].split(","))),new ArrayList<>(Arrays.asList(strr[8].split(","))),strr[9],new ArrayList<>(Arrays.asList(strr[10].split(",")))));
                    }
                    else{
                        System.out.println("Error message: A short film cannot be longer than 40 minutes!");
                    }
                  else if (strr[0].equals("Documentary:"))
                    Films.add(new DocumentaryFilm(strr[1],strr[2],strr[3],new ArrayList<>(Arrays.asList(strr[4].split(","))),strr[5],strr[6],new ArrayList<>(Arrays.asList(strr[7].split(","))),strr[8]));
                else if (strr[0].equals("TVSeries:"))
                    Films.add(new TVSeries(strr[1],strr[2],strr[3],new ArrayList<>(Arrays.asList(strr[4].split(","))),strr[5],strr[6],new ArrayList<>(Arrays.asList(strr[7].split(","))),new ArrayList<>(Arrays.asList(strr[8].split(","))),new ArrayList<>(Arrays.asList(strr[9].split(","))),strr[10],strr[11],strr[12],strr[13]));
            }
            filmTxt.close();
            
            String commandCurrentLine;
            BufferedReader commandTxt = new BufferedReader(new FileReader(args[2])); // Read args[2] command txt and decide what output should be
            outerloop:
            while ((commandCurrentLine = commandTxt.readLine()) != null){
                String[] strr = commandCurrentLine.split("\t");
                
                if (strr[0].equals("RATE")) { // first command Rate
                    if (Integer.parseInt(strr[3])<=10&&Integer.parseInt(strr[3]) >=1) {
                        for (Person person : Persons){
                            if(person instanceof User && person.Id.equals(strr[1])){
                                for (int i = 0; i < person.RatedFilms.size(); i++) {
                                    if (person.RatedFilms.get(i).split(",")[0].equals(strr[2])){
                                        WriteToTxt("This film was earlier rated\n",commandCurrentLine,args[3]);
                                        continue outerloop;
                                    }
                                }
                            }   
                        }
                        for (Person person : Persons) {
                            for (Film film: Films) {
                                if (person instanceof User && strr[1].equals(person.Id)&&strr[2].equals(film.filmID)) {
                                    
                                    Rate(strr[1],strr[2],strr[3]);
                                    WriteToTxt("Film rated successfully\nFilm type: " + film.filmType+"\nFilm title: " + film.filmTitle+"\n", commandCurrentLine,args[3]);
                                    continue outerloop;
                                }
                            }
                        }
                        WriteToTxt("Command Failed\nUser ID: " + strr[1]+ "\nFilm ID: "+strr[2]+ "\n",commandCurrentLine,args[3]); 
                    }
                    else{
                        System.out.println("Rate must be betwen 1-10");
                    }
                    
                }
                else if (strr[0].equals("ADD") && strr[1].equals("FEATUREFILM")){ // second command Add FeatureFilm
                    ArrayList<String> args0 = new ArrayList<String>();
                    ArrayList<String> args1= new ArrayList<String>();
                    for (String string : strr[5].split(",")) {
                        args1.add(string);
                    }
                    for (String string2 : strr[8].split(",")) {
                        args1.add(string2);
                    }
                    for (String string3 : strr[11].split(",")) {
                        args1.add(string3);
                    }

                    
                    for (Person person : Persons) { 
                        if (person instanceof Director||person instanceof Writer||person instanceof Performer) {
                            args0.add(person.Id);
                        }
                        
                    }
                    for (Film film : Films) {
                        if (film.filmID.equals(strr[2])) {
                            WriteToTxt("Command Failed\nFilm ID: " + strr[2]+ "\nFilm title: "+strr[3]+ "\n", commandCurrentLine,args[3]);
                            continue outerloop;
                        }
                    }
                    if (!args0.containsAll(args1)) {
                    
                        WriteToTxt("Command Failed\nFilm ID: " + strr[2]+ "\nFilm title: "+strr[3]+ "\n", commandCurrentLine,args[3]);
                        continue outerloop;
                    }
                    Films.add(new FeatureFilm(strr[2],strr[3],strr[4],new ArrayList<>(Arrays.asList(strr[5].split(","))),strr[6],strr[7],
                    new ArrayList<>(Arrays.asList(strr[8].split(","))),new ArrayList<>(Arrays.asList(strr[9].split(","))),strr[10],
                    new ArrayList<>(Arrays.asList(strr[11].split(","))),strr[12]));
                    WriteToTxt("FeatureFilm added successfully\nFilm ID: " + strr[2]+ "\nFilm title: "+strr[3]+ "\n", commandCurrentLine,args[3]);
                    continue outerloop;
                }
                else if (strr[0].equals("VIEWFILM")){ // third command VievFilm
                    double sum =0;
                    int votecount= 0;
                    String rate ="";
                    for (Person person : Persons) {
                        for (String RatedFilms : person.RatedFilms) {
                            if (Integer.parseInt(RatedFilms.split(",")[1])!=0 && RatedFilms.split(",")[0].equals(strr[1])) {
                                sum += Integer.parseInt(RatedFilms.split(",")[1]);
                                votecount++;
                            }
                        }
                    }
                    if (votecount>0) {
                        if (sum/votecount == (int)(sum/votecount)) {
                            rate = String.valueOf((int)(sum/votecount)).replace('.', ',');
                        }
                        else{
                            rate = String.valueOf((sum/votecount)).replace('.', ',');
                        }
                    }
                    
                    
                    for (Film Film : Films) {
                        if (Film.filmID.equals(strr[1])) {
                            
                            if (sum>0) {
                                if (Film.filmType.equals("FeatureFilm")) {
                                    FeatureFilm sss = (FeatureFilm)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Writers) {
                                            if (person.Id.equals(string)) {
                                                WritersNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }

                                    }
                                    WriteToTxt(sss.filmTitle + " ("+sss.getReleaseDate().split("\\.")[2]+")\n"+String.join(", ",sss.Genres)+
                                    "\nWriters: "+String.join(", ",WritersNames)+"\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nRatings: "+rate+"/10 from "+ votecount+" users\n",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    WritersNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }
                                else if(Film.filmType.equals("ShortFilm")){
                                    ShortFilm sss = (ShortFilm)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Writers) {
                                            if (person.Id.equals(string)) {
                                                WritersNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                    }
                                    WriteToTxt(sss.filmTitle + " ("+sss.getReleaseDate().split("\\.")[2]+")\n"+String.join(", ",sss.Genres)+
                                    "\nWriters: "+String.join(", ",WritersNames)+"\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nRatings: "+rate+"/10 from "+ votecount+" users\n",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    WritersNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }

                                else if(Film.filmType.equals("Documentary")){
                                    DocumentaryFilm sss = (DocumentaryFilm)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                    }
                                    WriteToTxt(sss.filmTitle + " ("+sss.getReleaseDate().split("\\.")[2]+")\n"+
                                    "\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nRatings: "+rate+"/10 from "+ votecount+" users\n",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }
                                else if(Film.filmType.equals("TVSeries")){
                                    TVSeries sss = (TVSeries)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Writers) {
                                            if (person.Id.equals(string)) {
                                                WritersNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                    }
                                    WriteToTxt(sss.filmTitle+ " ("+sss.getStartDate().split("\\.")[2]+"-"+sss.getEndDate().split("\\.")[2]+
                                    ")\n"+sss.getNumberOfSeasons()+" seasons, "+sss.getNumberOfEpisodes()+" episodes\n"
                                    +String.join(", ",sss.Genres)+"\nWriters: "+String.join(", ",WritersNames)+"\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nRatings: "+rate+"/10 from "+ votecount+" users\n",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    WritersNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }
                            }
                            else{
                                if (Film.filmType.equals("FeatureFilm")|| Film.filmType.equals("ShortFilm")) {
                                    FeatureFilm sss = (FeatureFilm)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Writers) {
                                            if (person.Id.equals(string)) {
                                                WritersNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                    }
                                    WriteToTxt(sss.filmTitle + " ("+sss.getReleaseDate().split("\\.")[2]+")\n"+String.join(", ",sss.Genres)+
                                    "\nWriters: "+String.join(", ",WritersNames)+"\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nAwaiting for votes\n",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    WritersNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }
                                else if(Film.filmType.equals("Documentary")){
                                    DocumentaryFilm sss = (DocumentaryFilm)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                       
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                    }
                                    WriteToTxt(sss.filmTitle + " ("+sss.getReleaseDate().split("\\.")[2]+")\n"+
                                    "\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nAwaiting for votes",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }
                                else if(Film.filmType.equals("TVSeries")){
                                    TVSeries sss = (TVSeries)Film;
                                    for (Person person : Persons) {
                                        for (String string : sss.Cast) {
                                            if (person.Id.equals(string)) {
                                                CastNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Writers) {
                                            if (person.Id.equals(string)) {
                                                WritersNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                        for (String string : sss.Directors) {
                                            if (person.Id.equals(string)) {
                                                DirectorsNames.add(person.name+" "+person.surname);
                                            }
                                        }
                                    }
                                    WriteToTxt(sss.filmTitle+ " ("+sss.getStartDate().split("\\.")[2]+"-"+sss.getEndDate().split("\\.")[2]+
                                    ")\n"+sss.getNumberOfSeasons()+" seasons, "+sss.getNumberOfEpisodes()+" episodes\n"
                                    +String.join(", ",sss.Genres)+"\nWriters: "+String.join(", ",WritersNames)+"\nDirectors: "+String.join(", ",DirectorsNames)+
                                    "\nStars: "+String.join(", ",CastNames)+"\nAwaiting for votes\n",commandCurrentLine,args[3]);
                                    CastNames.clear();
                                    WritersNames.clear();
                                    DirectorsNames.clear();
                                    continue outerloop;
                                }
                            }
                        }
                    }
                    WriteToTxt("Command Failed\nFilm ID: " + strr[1] + "\n",commandCurrentLine,args[3]);
                }
                else if (strr[0].equals("EDIT")&& strr[1].equals("RATE")){ // forth command Edit Rate
                    for (Person person : Persons){
                        if(person instanceof User && person.Id.equals(strr[2])){
                            for (int i = 0; i < person.RatedFilms.size(); i++) {
                                if (person.RatedFilms.get(i).split(",")[0].equals(strr[3])){
                                    person.RatedFilms.set(i, strr[3]+","+strr[4]);   
                                    for (Film film : Films) {
                                        if (film.filmID.equals(strr[3])) {
                                            WriteToTxt("New ratings done successfully\nFilm title: "+film.filmTitle+"\nYour rating: "+strr[4]+"\n", commandCurrentLine,args[3]); 
                                            continue outerloop;
                                        }
                                        
                                    }
                                    WriteToTxt("Command Failed\nUser ID: " +person.Id+"\nFilm ID: "+strr[3]+"\n",commandCurrentLine,args[3]);
                                    continue outerloop; 
                                }
                            }   
                            WriteToTxt("Command Failed\nUser ID: " +person.Id+"\nFilm ID: "+strr[3]+"\n",commandCurrentLine,args[3]);
                            continue outerloop;  
                        }   
                        else if(!(person instanceof User) &&person.Id.equals(strr[2])){
                            WriteToTxt("Command Failed\nUser ID: " +person.Id+"\nFilm ID: "+strr[3]+"\n",commandCurrentLine,args[3]);
                            continue outerloop; 
                        }

                    }
                }
                else if (strr[0].equals("REMOVE") && strr[1].equals("RATE")){ // fifth command Remove Rate
                    
                    for (Person person : Persons){
                        
                        if(person instanceof User && person.Id.equals(strr[2])){
                            for (int i = 0; i < person.RatedFilms.size(); i++) {
                                if (person.RatedFilms.get(i).split(",")[0].equals(strr[3])){
                                    boolean okey = false;
                                    for (Film film : Films) {
                                        
                                        if (strr[3].equals(film.filmID)) {
                                            okey =true;
                                            WriteToTxt("Your film rating was removed successfully\nFilm title: "+ film.filmTitle +"\n" ,commandCurrentLine,args[3]); 
                                        }

                                    }
                                    if (!okey) {
                                        WriteToTxt("Command Failed\nUser ID: "+ strr[2] +"\nFilm ID: "+strr[3]+ "\n", commandCurrentLine,args[3]);
                                        continue outerloop;
                                    }
                                    person.RatedFilms.remove(i);
                                    continue outerloop;
                                }
                            }
                        } 
                        
                    }
                   WriteToTxt("Command Failed\nUser ID: "+ strr[2] +"\nFilm ID: "+strr[3]+ "\n", commandCurrentLine,args[3]);
                }
                
                else if (strr[0].equals("LIST")){
                    if (strr[1].equals("USER")) { // sixth command List User
                        
                        for (Person person : Persons) {
                            if (person.Id.equals(strr[2]) && person instanceof User) {
                                String asd="" ;
                                if (person.RatedFilms.size() !=0) {
                                    
                                    for (String string : person.RatedFilms) {
                                        for (Film film : Films) {
                                            if (string.split(",")[0].equals(film.filmID)) {
                                                asd +=film.filmTitle+": "+ string.split(",")[1]+ "\n";
                                            }
                                            
                                        }
                                    }
                                    
                                }
                                else{
                                    WriteToTxt("There is not any ratings so far\n",commandCurrentLine,args[3]);
                                    continue outerloop;
                                }
                                WriteToTxt(asd,commandCurrentLine,args[3]);
                                continue outerloop;

                            }
                            
                            
                        }
                        WriteToTxt("Command Failed\nUser ID: "+strr[2]+ "\n", commandCurrentLine,args[3]);
                    }
                    else if (strr[1].equals("FILM") && strr[2].equals("SERIES")){ // seventh command List Film Series
                        
                        String asd ="";
                        for (Film film : Films) {
                            if (film instanceof TVSeries) {
                                TVSeries sss = (TVSeries)film;
                                asd += sss.filmTitle +" ("+sss.getStartDate().split("\\.")[2]+"-"+sss.getEndDate().split("\\.")[2]+
                                ")\n"+sss.getNumberOfSeasons()+" seasons and "+sss.getNumberOfEpisodes()+" episodes\n\n";
                            }
                        }
                        if (asd.equals("")) {
                            WriteToTxt("No result\n",commandCurrentLine,args[3]);
                        }
                        else{
                            asd = asd.substring(0, asd.length() - 1);
                            WriteToTxt(asd, commandCurrentLine,args[3]);
                        }

                    }
                    else if (strr[1].equals("FILMS") && strr[2].equals("BY")&& strr[3].equals("COUNTRY")){ // eighth command List Films By Country
                        String asd ="";
                        for (Film film : Films) {
                            if (strr[4].equals(film.country)) {
                                asd += "Film title: "+film.filmTitle +"\n"+film.runtime+" min\n"+"Language: "+ film.language+"\n\n";
                            }
                        }
                        if (asd.equals("")) {
                            WriteToTxt("No result\n",commandCurrentLine,args[3]);
                        }
                        else{
                            asd = asd.substring(0, asd.length() - 1);
                            WriteToTxt(asd, commandCurrentLine,args[3]);
                        }
                    }
                    else if (strr[1].equals("FEATUREFILMS")&&strr[2].equals("BEFORE")){ // ninth command List Feature Films Before Year
                        String asd =""; 
                        for (Film film : Films) {   
                            if (film instanceof FeatureFilm) {
                                FeatureFilm sss = (FeatureFilm)film;
                            if (Integer.parseInt(strr[3])>Integer.parseInt(sss.getReleaseDate().split("\\.")[2])) {
                                asd += "Film title: "+sss.filmTitle +" ("+sss.getReleaseDate().split("\\.")[2]+")\n"+sss.runtime+" min\n" +"Language: "+ film.language+"\n\n";
                            }
                            }
                            
                        }
                        if (asd.equals("")) {
                            WriteToTxt("No result\n",commandCurrentLine,args[3]);
                        }
                        else{
                            asd = asd.substring(0, asd.length() - 1);
                            WriteToTxt(asd, commandCurrentLine,args[3]);
                        }
                    }
                    else if (strr[1].equals("FEATUREFILMS")&&strr[2].equals("AFTER")){ // tenth command List FeatureFilms After year
                        String asd =""; 
                        for (Film film : Films) {   
                            if (film instanceof FeatureFilm) {
                                FeatureFilm sss = (FeatureFilm)film;
                            if (Integer.parseInt(strr[3])<=Integer.parseInt(sss.getReleaseDate().split("\\.")[2])) {
                                asd += "Film title: "+sss.filmTitle +" ("+sss.getReleaseDate().split("\\.")[2]+")\n"+sss.runtime+" min\n" +"Language: "+ film.language+"\n\n";
                            }
                            }
                            
                        }
                        if (asd.equals("")) {
                            WriteToTxt("No result\n",commandCurrentLine,args[3]);
                        }
                        else{
                            asd = asd.substring(0, asd.length() - 1);
                            WriteToTxt(asd, commandCurrentLine,args[3]);
                        }
                    }
                    else if (strr[1].equals("FILMS") && strr[2].equals("BY")&& strr[3].equals("RATE")&&strr[4].equals("DEGREE")){ // eleventh command List Films By Rate 
                        String ff = "";
                        String sf = "";
                        String df = "";
                        String tvs= "";
                        
                      

                        
                        
                        for (Film film : Films) {
                            double sum =0;
                            int votecount= 0;
                            for (Person person : Persons) {
                                for (String rates : person.RatedFilms) {
                                    if (Integer.parseInt(rates.split(",")[1])!=0 && rates.split(",")[0].equals(film.filmID)) {
                                        sum += (Integer.parseInt(rates.split(",")[1]));
                                        votecount++;
                                        film.voteCount =  String.valueOf(votecount);
                                             
                                        if (sum/votecount == (int)(sum/votecount)) {
                                            film.ratingScore = String.valueOf((int)(sum/votecount)).replace('.', ',');
                                        }
                                        else{
                                            
                                            film.ratingScore = String.valueOf(Math.floor(Math.round(sum/votecount* 10.0))/10.0).replace('.', ',');
                                        }
                                    
                                    }
                                }
                            }
                        }
                        
                        Comparator<Film> rate  = Comparator.comparing(Film :: GetRatingScore); 
                        Film[] sortedFilms =new Film[Films.size()];
                        int b= 0;
                        for (Film film : Films) {
                            sortedFilms[b] = film;
                            b++;
                        }
                        Arrays.sort(sortedFilms,rate.reversed());
                       
                        
                        

                        for (Film film : sortedFilms) {
                            if (film instanceof FeatureFilm) {
                                
                                FeatureFilm sss = (FeatureFilm)film;
                                if (sss.voteCount != null && sss.voteCount != "0") {
                                    ff += film.filmTitle + " (" + sss.getReleaseDate().split("\\.")[2]
                                 + ") Ratings: "+ sss.ratingScore +"/10 from "+sss.voteCount +" users\n";
                                }
                                else{
                                    ff += film.filmTitle + " (" + sss.getReleaseDate().split("\\.")[2]
                                 + ") Ratings: " +"0/10 from " +"0 users\n";
                                }
                                
                            }
                            else if (film instanceof ShortFilm){
                                
                                ShortFilm sss = (ShortFilm)film;
                                if (sss.voteCount != null && sss.voteCount != "0") {
                                    sf += film.filmTitle + " (" + sss.getReleaseDate().split("\\.")[2]
                                 + ") Ratings: "+ sss.ratingScore +"/10 from "+sss.voteCount +" users\n";
                                }
                                else{
                                    sf += film.filmTitle + " (" + sss.getReleaseDate().split("\\.")[2]
                                 + ") Ratings: " +"0/10 from " +"0 users\n";
                                }
                            }
                            else if (film instanceof DocumentaryFilm){
                                
                                DocumentaryFilm sss = (DocumentaryFilm)film;
                                
                              
                                if (sss.voteCount != null && sss.voteCount != "0") {
                                    df += film.filmTitle + " (" + sss.getReleaseDate().split("\\.")[2]
                                 + ") Ratings: "+ sss.ratingScore +"/10 from "+sss.voteCount +" users\n";
                                }
                                else{
                                    df += film.filmTitle + " (" + sss.getReleaseDate().split("\\.")[2]
                                 + ") Ratings: " +"0/10 from " +"0 users\n";
                                }

                            }
                            else if (film instanceof TVSeries){
                               
                                TVSeries sss = (TVSeries)film;
                                
                                if (sss.voteCount != null && sss.voteCount != "0") {
                                    tvs += film.filmTitle + " (" + sss.getStartDate().split("\\.")[2]+"-"+sss.getEndDate().split("\\.")[2]
                                 + ") Ratings: "+ sss.ratingScore +"/10 from "+sss.voteCount +" users\n";
                                }
                                else{
                                    tvs += film.filmTitle + " (" + sss.getStartDate().split("\\.")[2]+"-"+sss.getEndDate().split("\\.")[2]
                                 + ") Ratings: " +"0/10 from " +"0 users\n";
                                }
                                
                            }
                        }
                        
                        if (ff.equals("")) {
                            ff ="No result\n";
                        }
                        if (sf.equals("")){
                            sf ="No result\n";
                        }
                        if (df.equals("")){
                            df ="No result\n";
                        }
                        if(tvs.equals("")){
                            tvs ="No result\n";
                        }
                        
                        

                        WriteToTxt("FeatureFilm:\n"+ff+"\nShortFilm:\n"+sf+"\nDocumentary:\n"+df+"\nTVSeries: \n"+tvs,commandCurrentLine,args[3]);
                    }
                    else if (strr[1].equals("ARTISTS")&& strr[2].equals("FROM")){ // twelfth command List Artist From Country
                        String dr ="";
                        String wr= "";
                        String ac = "";
                        String ca="";
                        String sp= "";
                        for (Person person : Persons) {
                            if (person.country.equals(strr[3])) {
                                if (person instanceof Director) {
                                    Director sss = (Director)person;
                                    dr +=sss.name + " " + sss.surname + " " +sss.getAgentName()+"\n";
                                }
                                else if(person instanceof Writer){
                                    Writer sss = (Writer)person;
                                    wr += sss.name+" "+ sss.surname + " " + sss.getWritingType()+ "\n";
                                }
                                else if(person instanceof Actor){
                                    Actor sss = (Actor)person;
                                    ac += sss.name +" "+ sss.surname + " " +sss.getHeight() + " cm\n";
                                }
                                else if(person instanceof ChildActor){
                                    ChildActor sss = (ChildActor)person;
                                    ca += sss.name +" "+ sss.surname + " " + sss.getAge()+"\n" ;
                                }
                                else if(person instanceof StuntPerformer){
                                    StuntPerformer sss = (StuntPerformer)person;
                                    sp += sss.name +" "+ sss.surname + " " +sss.getHeight() + " cm\n";
                                }
                            }
                            
                            
                        }
                        if (dr.equals("")) {
                            dr ="No result\n";
                        }
                        if (wr.equals("")){
                            wr ="No result\n";
                        }
                        if (ac.equals("")){
                            ac ="No result\n";
                        }
                        if(ca.equals("")){
                            ca ="No result\n";
                        }
                        if(sp.equals("")){
                            sp ="No result\n";
                        }
                        WriteToTxt("Directors:\n"+dr+"\nWriters:\n"+wr+"\nActors:\n"+ac+"\nChildActors: \n"+ca+"\nStuntPerformers: \n"+sp ,commandCurrentLine,args[3]);
              
                    }
                    
                }
            }

            commandTxt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Rate(String userID,String filmID,String ratingPoint){ // first command Rate
        for (Person person : Persons){
            if(person instanceof User){
                for (Film film : Films) {
                    if (Integer.parseInt(ratingPoint) <=10 && Integer.parseInt(ratingPoint)>=0) { // if rating point is less than 10
                        if(person.Id.equals(userID)&& film.filmID.equals(filmID)){
                        person.RatedFilms.add(filmID+","+ratingPoint);  
                        return;
                    }

                        
                    } 
                }
            }
        }
    }
    public void WriteToTxt(String str,String command,String arg){ // Help to write text to output txt
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(arg,true));
            output.write(command+ "\n\n"+ str + "\n" + "-----------------------------------------------------------------------------------------------------\n" );
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
