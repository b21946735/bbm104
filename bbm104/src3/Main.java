import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    static HashMap<String,Character> characters = new HashMap<String,Character>(); // key = id value = Character object
    static int boardSizeX;
    static int boardSizeY;
    static ArrayList<ArrayList<String>> board = new ArrayList<ArrayList<String>>();

    public static void main(String[] args) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(args[2])); // Clean Txt
            output.close();
           
            ReadInitials(args[0]);
            CreateBoard();
            PrintBoard(args[2]);
            ReadCommands(args[1],args[2]);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void WhoWin(String outpu){
        try {
        BufferedWriter output = new BufferedWriter(new FileWriter(outpu,true));
        
        int zordeCount =0;
        int callCount=0;
        for (Character chr : characters.values()) {
            if(chr instanceof Zorde){
                zordeCount++;
            }
            else if(chr instanceof Calliance){
                callCount++;
            }
            
        }
        if (callCount==0) {
            output.write("\nGame Finished\nZorde Wins");
            //System.out.println("Game Finished\nZorde Wins");
            output.close();
            System.exit(0);
        }
        else if(zordeCount==0){
            output.write("\nGame Finished\nCalliance Wins");
            output.close();
            //System.out.println("Game Finished\nCalliance Wins");
            System.exit(0);
        }
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    


    private static void ReadInitials(String init){ // Read initial txt
        try {
            BufferedReader initials = new BufferedReader(new FileReader(init)); 
            String initialsCurrentLine;
            while ((initialsCurrentLine = initials.readLine()) != null){
                String[] lines = initialsCurrentLine.split(" ");
                if (initialsCurrentLine.equals("BOARD")) {
                    String nextLine = initials.readLine();
                   boardSizeX= Integer.parseInt(nextLine.split("x")[0]);
                   boardSizeY= Integer.parseInt(nextLine.split("x")[1]);
                }
                if (lines[0].equalsIgnoreCase("ELF")) {
                    characters.put(lines[1], new Elf(Integer.parseInt(lines[3]),Integer.parseInt(lines[2])));
                }
                else if(lines[0].equalsIgnoreCase("HUMAN")){
                    characters.put(lines[1], new Human(Integer.parseInt(lines[3]) ,Integer.parseInt(lines[2])));
                }
                else if(lines[0].equalsIgnoreCase("DWARF")){
                    characters.put(lines[1], new Dwarf(Integer.parseInt(lines[3]),Integer.parseInt(lines[2])));
                }
                else if(lines[0].equalsIgnoreCase("GOBLIN")){
                    characters.put(lines[1], new Goblin(Integer.parseInt(lines[3]),Integer.parseInt(lines[2])));
                }
                else if(lines[0].equalsIgnoreCase("TROLL")){
                    characters.put(lines[1], new Troll(Integer.parseInt(lines[3]),Integer.parseInt(lines[2])));
                }
                else if(lines[0].equalsIgnoreCase("ORK")){
                    characters.put(lines[1], new Ork(Integer.parseInt(lines[3]),Integer.parseInt(lines[2])));
                }
            }
            initials.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void ReadCommands(String comm,String outpu){ // Read commands txt
        try {
            BufferedReader commands = new BufferedReader(new FileReader(comm)); 
            String commandsCurrentLine;
            while ((commandsCurrentLine= commands.readLine())!= null) {
                String chr = commandsCurrentLine.split(" ")[0],step= commandsCurrentLine.split(" ")[1];
                String[] steps = step.split(";");
                int stepCount = 0;
                boolean endStep = false;
                try {
                    for (int i = 0; i < steps.length; i+=2) {
                        stepCount++;
                        if (characters.get(chr) instanceof Ork) {
                            if(steps.length/2 != Constants.orkMaxMove){throw new IndexOutOfBoundsException();}
                            Heal(chr, Constants.orkHealPoints);
                            endStep=MoveCharacter(chr,Integer.parseInt(steps[i+1]) , Integer.parseInt(steps[i]),true);
                            if (endStep) {break;}
                            Attack(chr, Constants.orkAP,3,3,true);
                        }
                        else if (characters.get(chr) instanceof Troll){
                            if(steps.length/2 != Constants.trollMaxMove){throw new IndexOutOfBoundsException();}
                            endStep=MoveCharacter(chr,Integer.parseInt(steps[i+1]) , Integer.parseInt(steps[i]),true);
                            if (endStep) {break;}
                            Attack(chr, Constants.trollAP,3,3,true);
    
                        }
                        else if (characters.get(chr) instanceof Goblin){
                            if(steps.length/2 != Constants.goblinMaxMove){throw new IndexOutOfBoundsException();}
                            endStep=MoveCharacter(chr,Integer.parseInt(steps[i+1]) , Integer.parseInt(steps[i]),true);
                            if (endStep) {break;}
                            Attack(chr, Constants.goblinAP,3,3,true);
    
                        }
                        else if (characters.get(chr) instanceof Elf){
                            if(steps.length/2 != Constants.elfMaxMove){throw new IndexOutOfBoundsException();}
                            endStep=MoveCharacter(chr,Integer.parseInt(steps[i+1]) , Integer.parseInt(steps[i]),false);
                            if(stepCount==Constants.elfMaxMove){Attack(chr, Constants.elfRangedAP,5,5,false);break;}
                            if (endStep) {break;}
                            Attack(chr, Constants.elfAP,3,3,false);
    
                        }
                        else if (characters.get(chr) instanceof Dwarf){
                            if(steps.length/2 != Constants.dwarfMaxMove){throw new IndexOutOfBoundsException();}
                            endStep=MoveCharacter(chr,Integer.parseInt(steps[i+1]) , Integer.parseInt(steps[i]),false);
                            if (endStep) {break;}
                            Attack(chr, Constants.dwarfAP,3,3,false);
    
                        }
                        else if (characters.get(chr) instanceof Human){
                            if(steps.length/2 != Constants.humanMaxMove){throw new IndexOutOfBoundsException();}
                            endStep=MoveCharacter(chr,Integer.parseInt(steps[i+1]) , Integer.parseInt(steps[i]),false);
                            if (endStep) {break;}
                            if(stepCount==Constants.humanMaxMove){Attack(chr, Constants.humanAP,3,3,false);break;}
                            
                            
                        }
                       
                        if (endStep) {
                            break;
                        }
                        
                        
                    }
                    PrintBoard(outpu); // Print board after every command
                    WhoWin(outpu);
                    
                } catch (ArrayIndexOutOfBoundsException e) {
                    BufferedWriter output = new BufferedWriter(new FileWriter(outpu,true));
                    if(stepCount >1){PrintBoard(outpu);}
                    output.write("Error : Game board boundaries are exceeded. Input line ignored.\n\n");
                    //System.out.println("Error : Game board boundaries are exceeded. Input line ignored.");
                    //System.out.println();
                    output.close();
                }
                catch (IndexOutOfBoundsException e){
                    BufferedWriter output = new BufferedWriter(new FileWriter(outpu,true));
                    output.write("Error : Move sequence contains wrong number of move steps. Input line ignored.\n\n");
                    //System.out.println("Error : Move sequence contains wrong number of move steps. Input line ignored.");
                    //System.out.println();
                    output.close();
                }
            }
            commands.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void CreateBoard(){ // Create board when game started
        for (int i = 0; i < boardSizeX; i++) {
            board.add(new ArrayList<String>());
        }
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < boardSizeY; j++) {
                board.get(i).add(j,"  ");
            }
        }
        for (String chars : characters.keySet())  {
            board.get(characters.get(chars).initialPositionX).set(characters.get(chars).initialPositionY,chars);
        }
    }


    private static void PrintBoard(String outpu){ // Print board to the output txt
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(outpu,true));
            TreeMap<String,Character> sortedMap =new TreeMap<String, Character>(characters);
        
        
        for (int i = 0; i < boardSizeX*2+2; i++) {
            output.write("*");
           // System.out.print("*");
        }
        output.write("\n");
        //System.out.println();
        for (ArrayList<String> arrayList : board) {
            output.write("*");
            //System.out.print("*");
            for (String string : arrayList) {
                output.write(string);
                //System.out.print(string);
            }
            output.write("*\n");
            //System.out.print("*");
            //System.out.println();
            
        }
        for (int i = 0; i < boardSizeX*2+2; i++) {
            output.write("*");
            //System.out.print("*");
        }
        output.write("\n\n");
        //System.out.println();
        //System.out.println();
        for (String chr : sortedMap.keySet()){
            output.write(chr + "\t" +characters.get(chr).currentHP + "\t("+ characters.get(chr).maxHP + ")\n");
            //System.out.println(chr + "\t" +characters.get(chr).currentHP + "\t("+ characters.get(chr).maxHP + ")");
        }
        output.write("\n");
        //System.out.println();
        output.close();
        } catch (Exception e) {
        }
        
    }


    private static boolean MoveCharacter(String chr,int x,int y,boolean isZorde){ // Move characters according to command
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j).equals(chr)) {
                    
                    if (board.get(i+x).get(j+y).equals("  ")) {
                        
                        board.get(i+x).set(j+y,chr);
                        board.get(i).set(j,"  ");
                        return false;
                    }
                    else if(characters.get(board.get(i+x).get(j+y))instanceof Zorde &&isZorde){ // Ally
                        return true;
                    }
                    else if(characters.get(board.get(i+x).get(j+y))instanceof Calliance &&!isZorde){ // Ally
                        
                        return true;
                    }
                    else if(characters.get(board.get(i+x).get(j+y))instanceof Zorde &&!isZorde){ // Enemy
                        
                        FightToDeath(characters.get(chr),characters.get(board.get(i+x).get(j+y)),i,j,i+x,j+y,chr,board.get(i+x).get(j+y));
                        return true;
                    }
                    else if(characters.get(board.get(i+x).get(j+y))instanceof Calliance &&isZorde){ // Enemy
                        FightToDeath(characters.get(chr),characters.get(board.get(i+x).get(j+y)),i,j,i+x,j+y,chr,board.get(i+x).get(j+y));
                        return true;
                    
                }
                }
                    
                
            }
        }
        return false;
    }


    private static void FightToDeath(Character attacker,Character defender,int attackerX,int attackerY,int defenderX,int defenderY,String attackerID,String defenderID) {
        defender.currentHP -= attacker.AP;
        
        if (defender.currentHP>attacker.currentHP) { // if defender win
            board.get(attackerX).set(attackerY,"  ");
            defender.currentHP -= attacker.currentHP;
            Remove(attackerID);
        }
        else if (attacker.currentHP> defender.currentHP) { // if attacker win
            board.get(defenderX).set(defenderY,attackerID);
            board.get(attackerX).set(attackerY,"  ");
            if (defender.currentHP>=0) {
                attacker.currentHP -= defender.currentHP;
            }
            Remove(defenderID);
        }
        else if (attacker.currentHP == defender.currentHP){ // if both die
            board.get(attackerX).set(attackerY,"  ");
            board.get(defenderX).set(defenderY,"  ");
            Remove(attackerID);
            Remove(defenderID);
        }
    }


    private static void Heal(String chr,int amount){ // Heal of Ork
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j).equals(chr)) {
                    for (int j2 = -1; j2 < 2; j2++) {
                        for (int k = -1; k < 2; k++) {
                           
                            if (i+j2>=0&&j+k>=0&&i+j2<boardSizeX&&j+k<boardSizeY) {
                                Character charr = characters.get(board.get(i+j2).get(j+k));
                            if (charr instanceof Zorde) {
                                if (charr.currentHP+amount >= charr.maxHP) {
                                    charr.currentHP =charr.maxHP;
                                }
                                else{
                                    charr.currentHP+=amount;
                                }
                            }
                            }
                            
                        }
                    }
                    return ;
                }
            }
        }
    }


    private static void Attack(String chr,int damage,int X, int Y,boolean isZorde){ 
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j).equals(chr)) {
                    for (int j2 = -(X/2); j2 < (X/2 +1); j2++) {
                        for (int k = -(Y/2); k < (Y/2+1); k++) {
                            if (i+j2>=0&&j+k>=0&&i+j2<boardSizeX&&j+k<boardSizeY){
                            Character charr = characters.get(board.get(i+j2).get(j+k));
                            if (  !(charr==null)) {
                                if (charr instanceof Zorde&& !isZorde) {
                                    charr.currentHP -= damage;
                                }
                                else if (charr instanceof Calliance && isZorde){
                                    charr.currentHP -= damage;
                                }
                                if (charr.currentHP<=0) {
                                    Remove(board.get(i+j2).get(j+k),i+j2,j+k);
                                    
                                }
                            }}
                            
                        }
                    }
                    return;
                }
            }
        }
    }
    private static void Remove(String id,int x,int y){
        characters.remove(id);
        board.get(x).set(y,"  ");
    }
    private static void Remove(String id){
        characters.remove(id);
    }

}
