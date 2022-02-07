import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.LinkedHashMap;



public class Main {


    static LinkedHashMap<String,Stack<String>> partsList = new LinkedHashMap<String,Stack<String>>();
    static Queue<Token> tokenList = new Queue<Token>();
    public static void main(String[] args) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(args[4])); // Clean Txt
            output.close();
            ReadParts(args[0]);
            ReadItems(args[1]);
            ReadTokens(args[2]);
            ReadTasks(args[3]);
            WriteOutput(args[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void ReadParts(String arg){ // Read Parts.txt and create part objects
        try {
            BufferedReader parts = new BufferedReader(new FileReader(arg)); 
            String partsCurrentLine;
            while ((partsCurrentLine = parts.readLine()) != null){
                partsList.put(partsCurrentLine,new Stack<String>());
            }
            parts.close();
        } catch (Exception ex) {
            
        }
    }


    static void ReadItems(String arg){ // Read Items.txt and create initial items and put them into specific parts
        try {
            BufferedReader items = new BufferedReader(new FileReader(arg)); 
            String itemsCurrentLine;
            while ((itemsCurrentLine = items.readLine()) != null){
               if (partsList.containsKey(itemsCurrentLine.split(" ")[1])) {
                   partsList.get(itemsCurrentLine.split(" ")[1]).push((itemsCurrentLine.split(" ")[0]));
               }
            }
            
            items.close();
        } catch (Exception ex) {
            
        }
    }


    static void ReadTokens(String arg){ // Read Tokens.txt and create tokens also sort them according to priority queue
        try {
            BufferedReader tokens = new BufferedReader(new FileReader(arg)); 
            String tokensCurrentLine;
            while ((tokensCurrentLine = tokens.readLine()) != null){
                
               tokenList.enqueue(new Token((tokensCurrentLine.split(" ")[0].charAt(1)),
               Integer.parseInt(tokensCurrentLine.split(" ")[2]),tokensCurrentLine.split(" ")[1]));
            }
            tokens.close();
            tokenList.Sort();
            
            tokenList.reverse();
        } catch (Exception ex) {
            
        }
    }


    static void ReadTasks(String arg){ // Read Tasks.txt and do task
        try {
            BufferedReader tasks = new BufferedReader(new FileReader(arg)); 
            String tasksCurrentLine;
            while ((tasksCurrentLine = tasks.readLine()) != null){
               String[] temp = tasksCurrentLine.split("\t");
               if (temp[0].equals("BUY")) {
                   for (int i = 1; i < temp.length; i++) {
                        String[] temp2 =temp[i].split(",");
                        if (partsList.containsKey(temp2[0])) {
                            for (int j = 0; j < Integer.parseInt(temp2[1]); j++) {
                                partsList.get(temp2[0]).pop();
                            }
                            for (int k = 0; k < tokenList.queue.size(); k++) {
                                boolean remove = false;
                                if (tokenList.queue.get(k).name.equals(temp2[0])) {
                                    Token currentToken =tokenList.queue.get(k);
                                    for (int l = 0; l < Integer.parseInt(temp2[1]) ; l++) {
                                        if (currentToken.value>0) {
                                            currentToken.value --;
                                           
                                            if (currentToken.value ==0) {
                                                remove =true;
                                            }
                                        }
                                        
                                    }
                                    tokenList.enqueue(currentToken);
                                    tokenList.queue.remove(k);
                                    if (remove) { 
                                        tokenList.dequeue();
                                        continue;
                                    }
                                    else {
                                        break;
                                    }
                                }
                            }
                        }
                   }
               }
               else if (temp[0].equals("PUT")){
                    for (int i = 1; i < temp.length; i++){
                        String[] temp2 =temp[i].split(",");
                        if (partsList.containsKey(temp2[0])) {
                            for (int j = 1; j < temp2.length; j++) {
                                partsList.get(temp2[0]).push(temp2[j]);
                            }
                        }
                    }
               }
            }
            tokenList.reverse();
            tokenList.Sort2();
            tasks.close();
        } catch (Exception ex) {
            
        }
    }


    static void WriteOutput(String arg){ // Write to output file
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(arg));
            output = new BufferedWriter(new FileWriter(arg,true));
            for (String string : partsList.keySet()) {
                Collections.reverse(partsList.get(string).stack);
                output.write(string+":\n") ;
                for (String ss : partsList.get(string).stack) {
                    output.write(ss+"\n");
                }
                output.write("---------------\n") ;
            }
            output.write("Token Box:\n") ;
            for (int i = 0; i < tokenList.queue.size(); i++) {
                if (i==tokenList.queue.size()-1) {
                    output.write("T"+tokenList.queue.get(i).tier +" "+
                    tokenList.queue.get(i).name +" "+tokenList.queue.get(i).value);
                }
                else {
                    output.write("T"+tokenList.queue.get(i).tier +" "+
                    tokenList.queue.get(i).name +" "+tokenList.queue.get(i).value+"\n");
                }
                
            }
            output.close();
        } catch (Exception ex) {
            
        }
    }

}