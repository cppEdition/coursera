import java.util.Stack;

public class MaxStack<T extends Comparable<? super T>> {
    
    private Stack<T> stack_nums = new Stack<>();
    private Stack<T> stack_max = new Stack<>();
    
    public T pop() {
        stack_max.pop();
        return stack_nums.pop();
    }
    
    public void push(T number) {
        stack_nums.push(number);
        
        if (stack_max.isEmpty()) {
            stack_max.push(number);
        } 
      else {
            if (number.compareTo(stack_max.peek()) > 0) {
                stack_max.push(number);
            } else {
                stack_max.push(stack_max.peek());
            }
        }
    }
    
    public T max() {
        System.out.println("Max: " + stack_max.peek());
        return stack_max.peek();
    }
      public static void main(String[] args) {
        MaxStack<Integer> s = new MaxStack<>();
        s.push(3);
        s.push(10);
        s.push(7);
        System.out.println("max == 10: "+(s.max() == 10));
        s.push(12);
        s.push(5);
        System.out.println("max == 12: "+(s.max() == 12));
        s.pop();
        System.out.println("max == 12: "+(s.max() == 12));
        s.pop();
        System.out.println("max == 10: "+(s.max() == 10));
        s.pop();
        s.pop();
        System.out.println("max == 3: "+(s.max() == 3));
        s.pop();
    }
}
