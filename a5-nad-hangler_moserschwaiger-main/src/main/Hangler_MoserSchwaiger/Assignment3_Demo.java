package Hangler_MoserSchwaiger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;

public class Assignment3_Demo {

    public static void main(String[] args) throws ScriptException {

        InfixToPostfix infixToPostfix = new InfixToPostfix();
        String exp = "(8+2)/(7-3)";
        System.out.println("Infix Expression: " + exp);
        System.out.println("Postfix Expression: " + Arrays.toString(infixToPostfix.infixToPostfix(exp)));

        Assignment3 a3 = new Assignment3();
        String[] input = {"8", "223.223", "+", "7", "3", "-", "/"};
        String[] exp2 = {"(", "8", "+", "2", ")", "/", "(", "7", "-", "3", ")",};
        System.out.print("Input: ");


        for(String s : input)
            System.out.print(s + ", ");

        System.out.println("\n\nOutput: \nArray: " + a3.calc(input, a3.getArrayStack()));
        System.out.println("LinkedList: " + a3.calc(input, a3.getListStack()));

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        StringBuilder sb = new StringBuilder();
        for (String s : exp2) sb.append(s);

        Object result = engine.eval(sb.toString());
         System.out.println(sb.toString());
        //System.out.println(Arrays.toString(exp2).substring(1, exp2.length));


        System.out.println("Compiler: " + result);
    }
}
