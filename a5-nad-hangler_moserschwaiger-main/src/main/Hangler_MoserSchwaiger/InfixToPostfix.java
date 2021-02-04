package Hangler_MoserSchwaiger;
//code from: https://algorithms.tutorialhorizon.com/convert-infix-to-postfix-expression/
import java.util.Stack;

public class InfixToPostfix {

    private int precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public String[] infixToPostfix(String expression){

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i <expression.length() ; i++) {
            char c = expression.charAt(i);

            //check if char is operator
            if(precedence(c)>0){
                while(!stack.isEmpty() && precedence(stack.peek())>=precedence(c)){
                    result.append(" ");
                    //System.out.println("BLANK");
                    //System.out.println("if: pop: " + stack.peek());
                    result.append(stack.pop());
                }
                //System.out.println("BLANK");
                result.append(" ");
                stack.push(c);

            }else if(c==')'){
                char x = stack.pop();

                while(x!='('){
                    //System.out.println("elseif(')'): pop: " + x);
                    result.append(x);
                    x = stack.pop();
                }
                //result.append(" ");

            }else if(c=='(')
                stack.push(c);

            else {
                //character is neither operator nor (
                //System.out.println("else: pop: " + c);
                result.append(c);
            }

        }

        if(stack.size() > 1) result.append(" ");

        for (int i = 0; i <=stack.size()-2 ; i++)
            result.append(stack.pop());

        result.append(" ").append(stack.pop());

        return result.toString().split(" ");
    }
}
