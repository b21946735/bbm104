import java.io.*;
import java.util.*;


public class Main {

    
    
    
    
    
    
    
        private static ArrayList<Article> articles = new ArrayList<Article>();
        private static ArrayList<Author> authors = new ArrayList<Author>();
        
        public static void main(String[] args) {
            
    
            try {
                BufferedWriter bw3 =new BufferedWriter(new FileWriter("output.txt"));
                bw3.close();
                BufferedReader inputtCommand = new BufferedReader(new FileReader(args[1]));
                String g;
                g = inputtCommand.readLine();
    
                inputtCommand.close();
                BufferedReader inputArticle = new BufferedReader(new FileReader(g.split(" ")[1]));
               
                String y;
                while ((y = inputArticle.readLine()) != null){
                    String[] lines1 = y.split(" ");
                    articles.add(new Article(lines1[1],lines1[2],lines1[3],lines1[4]));
                }
                inputArticle.close();
    
    
                BufferedReader inputAuthor = new BufferedReader(new FileReader(args[0]));
    
                String x; 
                
                while ((x = inputAuthor.readLine()) != null){
                    
                    String[] lines = x.split(" ");
                    
                    for (int i = 6; i < lines.length; i++) {
                        for (Article article : articles)
                        {
                            if (lines[i].equals(article.getPaperID())) {
                                lines[i] = article.getEverything();
                            }
                        }
                    }
                    
                    if(lines.length == 11){
                        authors.add(new Author(lines[1],lines[2],lines[3],lines[4],lines[5],lines[6],lines[7],lines[8],lines[9],lines[10]));
                    }
                    else if(lines.length== 10){
                        authors.add(new Author(lines[1],lines[2],lines[3],lines[4],lines[5],lines[6],lines[7],lines[8],lines[9]));
                    }
                    else if(lines.length== 9){
                        authors.add(new Author(lines[1],lines[2],lines[3],lines[4],lines[5],lines[6],lines[7],lines[8]));
                    }
                    else if(lines.length== 8){
                        authors.add(new Author(lines[1],lines[2],lines[3],lines[4],lines[5],lines[6],lines[7]));
                    }
                    else if(lines.length== 7){
                        authors.add(new Author(lines[1],lines[2],lines[3],lines[4],lines[5],lines[6]));
                    }
                    else if(lines.length== 6){
                        authors.add(new Author(lines[1],lines[2],lines[3],lines[4],lines[5]));
                    }
                    else if (lines.length==2){
                        authors.add(new Author(lines[1]));
    
                    }
                    
                }
                inputAuthor.close();
                
                
                for(Author author : authors){
                    author.setArticleList2();
                }
                
                BufferedReader inputCommand = new BufferedReader(new FileReader(args[1]));
                String h;
                int count = 0;
                while ((h = inputCommand.readLine()) != null){
                    
                    if (h.split(" ")[0].equals("read") && count>0) {
                        BufferedReader inputtArticle = new BufferedReader(new FileReader(h.split(" ")[1]));
                    String f;
                    while ((f = inputtArticle.readLine()) != null){
                        String[] lines1 = f.split(" ");
                        articles.add(new Article(lines1[1],lines1[2],lines1[3],lines1[4]));
                    }
                        inputtArticle.close();
                    
                    
                    }
                    else if(h.split(" ")[0].equals("list")){
                        BufferedWriter bw =new BufferedWriter(new FileWriter("output.txt",true));
                        bw.write("----------------------------------------------List---------------------------------------------");
                        bw.close();
                        ListAll();
                        BufferedWriter bw5 =new BufferedWriter(new FileWriter("output.txt",true));
                        bw5.write("----------------------------------------------End----------------------------------------------\n");
                        
                        bw5.close();
                        
                    }
                    else if(h.split(" ")[0].equals("completeAll")){
                        BufferedWriter bw =new BufferedWriter(new FileWriter("output.txt",true));
                        bw.write("*************************************CompleteAll Successful*************************************\n");
                        CompleteAll();
                        bw.close();
                        
                    }
                    else if (h.split(" ")[0].equals("sortedAll") ){
                        BufferedWriter bw =new BufferedWriter(new FileWriter("output.txt",true));
                        bw.write("*************************************SortedAll Successful*************************************\n");
                        SortAll();
                        bw.close();
                    }
                    else if(h.split(" ")[0].equals("del")){
                        BufferedWriter bw =new BufferedWriter(new FileWriter("output.txt",true));
                        bw.write("*************************************del Successful*************************************\n");
    
                        Delete(h.split(" ")[1]);
                        bw.close();
                        
                    }
                    
                    count++;
                    
                }
                inputCommand.close();
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
        private static void ListAll() {
    
            for (Author author : authors) {
                
                try {
                BufferedWriter bw =new BufferedWriter(new FileWriter("output.txt",true));
                bw.write("\n" + "Author " + author.getId() +"\t" + author.getName()+ "\t" +author.getUniversity() + "\t" +author.getDepartment() + "\t" + author.getEmail() + "\n");
                bw.close();
                if (author.getArticleList().isEmpty()) {
                    
                   
                    continue;
                }
                BufferedWriter bw1 =new BufferedWriter(new FileWriter("output.txt",true));
                if (author.getArticleList().get(0) != null) {
                    
                    bw1.write(author.getArticleList().get(0)+ "\n");
                    
                }
                if (author.getArticleList().get(1) != null) {
                    bw1.write(author.getArticleList().get(1)+ "\n");
                   
                }
                if (author.getArticleList().get(2) != null) {
                    bw1.write(author.getArticleList().get(2)+ "\n");
                   
                }
                if (author.getArticleList().get(3) != null) {
                    bw1.write(author.getArticleList().get(3)+ "\n");
                    
                }
                if (author.getArticleList().get(4) != null) {
                    bw1.write(author.getArticleList().get(4)+ "\n");
                    
                }
                bw1.close();
                
            } catch (Exception e) {
                
            }
            }
        }
        private static void SortAll(){
            
            for (Author author : authors) {
                
                
                ArrayList<String> aaa =author.getArticleList();
                
                if (aaa.get(4) != null){
                    aaa.subList(5, aaa.size()).clear();
                }
                

                while (aaa.remove(null)) { 
                }
                
                Collections.sort(aaa);
            
                for (int i = 0; i < 9-aaa.size(); i++) {
                    aaa.add(null);
                }
               
                author.setArticleList(aaa);
                
            }
    
        }
        private static void CompleteAll()
        {
            
                
    
            for (Author author : authors) {
                ArrayList<String> aaa =author.getArticleList();
                boolean exist = false;
                while (aaa.remove(null)) { 
                }
                
                int z = aaa.size();
                for (Article article : articles){
    
                   
                    if (author.getId().equals(article.getPaperID().substring(0,3))) {
                        exist =false;
                        for (int i = 0; i < z; i++) {
    
                            if (article.getPaperID().equals(aaa.get(i).substring(1,8))) {
                                
                                exist = true;
              
                            }
                            
                        }
                       
                        if (!exist) {
                            
                            aaa.add(article.getEverything());
                            
                            
                        }
    
                    }
    
    
                }
                for (int i = 0; i < 9-aaa.size(); i++) {
                    aaa.add(null);
                }
                
                author.setArticleList(aaa);
                
            }
        }
        private static void Delete(String id){
            int z = authors.size();
            int b =100;
            for (int i = 0; i < z; i++) {
                if (authors.get(i).getId().equals(id)) {
                    b = i;
                }
            }
            authors.remove(b);
        }
    }
    
    

