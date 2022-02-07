import java.util.ArrayList;

public class Stack<T> {
    ArrayList<T> stack = new ArrayList<T>();

    public void push(T object) {
        stack.add(object);
    }

    public T pop() {
        try {
            T result = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
           
            return result;
        } catch (Exception e) {
            System.out.println("List is Empty");
            return null;
        }
    }
        
}
