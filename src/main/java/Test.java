import java.util.Stack;

public class Test {
    public static boolean main(String[] args) {
        String s = "";
        Stack<Character> stack = new Stack<>();
        for(Character c: s.toCharArray()){
            if(c==')' || c==']' || c=='}'){
                stack.push(c);
                continue;
            }
            if(stack.isEmpty()) return false;
            Character lastChar = stack.peek();
            if(c == '}' && lastChar != '{') return false;
            if(c == ')' && lastChar != '(') return false;
            if(c == ']' && lastChar != '[') return false;
        }

        return false;
    }

}
