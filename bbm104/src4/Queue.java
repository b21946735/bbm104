import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Queue<T> {
    ArrayList<T> queue = new ArrayList<T>();


    public void enqueue(T object) {
        queue.add(object);
    }


    public T dequeue() {
        try {
            T result = queue.get(queue.size() - 1);
            /*
            for (int i = 0; i < queue.size()-1; i++) {
                queue.set(i,queue.get(i+1));
            }
            */
            
            queue.remove(queue.size()-1);
            return result;
        } catch (Exception e) {
            System.out.println("List is Empty");
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    public void Sort(){
        queue.sort((Comparator<? super T>)Comparator.comparing(Token::getValue).thenComparing(Token::getTier));
    }



    public void reverse(){
        Collections.reverse(queue);
    }

    
    @SuppressWarnings("unchecked")
    public void Sort2(){
        
        queue.sort((Comparator<? super T>) Comparator.comparing(Token::getValue));
    }



}
