package Hangler_MoserSchwaiger;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

class Assignment3Test {
    static private Assignment3 a3;
    static private InfixToPostfix infixToPostfix;
    private double expectedResult = 0;
    static private String version;

    @BeforeAll
    static void init(){
        a3 = new Assignment3();
        infixToPostfix = new InfixToPostfix();

        version = System.getProperty("java.version");
        version = version.substring(0,2);
    }

    @RepeatedTest(20)
    @DisplayName("Random arithmetic expression (works only for Java Version <= 14)")
    void testCalcRandom() throws ScriptException {
        assumeTrue(Integer.parseInt(version) < 15);

        String[] testInputRandom = generateRandomCalc();

        double arrayStackResult = a3.calc(testInputRandom, a3.getArrayStack());
        double listStackResult = a3.calc(testInputRandom, a3.getListStack());

        System.out.println( "Expected: " + expectedResult + "  Actual: " + listStackResult);
        System.out.println( "Expected: " + expectedResult + "  Actual: " + arrayStackResult);

        assertAll(
                () -> assertEquals(expectedResult, listStackResult, () -> "Calculation with list stack."),
                () -> assertEquals(expectedResult, arrayStackResult, () -> "Calculation with list stack.")
        );
    }

    @Test
    @DisplayName("Fix arithmetic expression (for Java Version >= 15)")
    void testCalcFix(){

        assumeTrue(Integer.parseInt(version) >= 15);

        String[] calc1 = new String[]{"7", "3.5", "+", "2.5", "2", "*", "/"};
        String[] calc2 = new String[]{"3", "7", "-", "18", "6", "/", "*"};
        String[] calc3 = new String[]{"1.25", "3", "+", "6", "5", "-", "*"};

        assertAll(
                () -> assertEquals(2.1, a3.calc(calc1,a3.getArrayStack()), () -> "Calculation with list stack."),
                () -> assertEquals(2.1, a3.calc(calc1,a3.getListStack()), () -> "Calculation with list stack."),
                () -> assertEquals(-12, a3.calc(calc2,a3.getArrayStack()), () -> "Calculation with list stack."),
                () -> assertEquals(-12, a3.calc(calc2,a3.getListStack()), () -> "Calculation with list stack."),
                () -> assertEquals(4.25, a3.calc(calc3,a3.getArrayStack()), () -> "Calculation with list stack."),
                () -> assertEquals(4.25, a3.calc(calc3,a3.getListStack()), () -> "Calculation with list stack.")
        );
    }

    @Test
    @DisplayName("invalid input")
    void testCalcInvalidInput() {
        String[] testInputToMuchOp = {"8", "4.5", "+", "7", "3", "-", "-", "/"};
        String[] testInputToMuchNumbers = {"8", "3.5", "+", "7", "3", "-", "/", "3"};
        String[] testInputToLessNumbers = {"8", "+", "7", "3", "-", "/"};
        String[] testInputNotANumber = {"8", "4.5", "+", "$", "3", "-", "/"};

        assertAll(
                () -> assertThrows(RuntimeException.class, () -> a3.calc(testInputToMuchOp,a3.getArrayStack()), () -> "Calculation with to much operands."),
                () -> assertThrows(RuntimeException.class, () -> a3.calc(testInputToMuchNumbers,a3.getListStack()), () -> "Calculation with to much numbers."),
                () -> assertThrows(RuntimeException.class, () -> a3.calc(testInputToLessNumbers,a3.getArrayStack()), () -> "Calculation with to less numbers."),
                () -> assertThrows(RuntimeException.class, () -> a3.calc(testInputNotANumber,a3.getListStack()), () -> "Calculation with not a number element.")
                );
    }


    private String[] generateRandomCalc() throws ScriptException {
        //calc the length of the calculation between 1 and 11
        int calcLength = (int) getRandomNumber(2,10);

        //if calcLength is not odd -> make it odd
        if(calcLength%2 == 0)
            calcLength++;

        StringBuilder calcString = new StringBuilder();

        calcString.append(getRandomNumber(0,100));
        for(int i=1; i<calcLength; i++){
            if(i%2 == 1)
                calcString.append(arithmeticOp( (int) getRandomNumber(1,4)));
            else
                calcString.append(getRandomNumber(0,100));
        }

        setExpectedResult(calcString.toString());

        return infixToPostfix.infixToPostfix(calcString.toString());
    }

    private double getRandomNumber(int min, int max) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));

        return Double.parseDouble(df.format((Math.random() * (max - min)) + min));
    }

    private String arithmeticOp(int number) {
        int rest = Math.abs(number % 4);

        return switch (rest) {
            case 0 -> "+";
            case 1 -> "-";
            case 2 -> "*";
            default -> "/";
        };
    }

    private void setExpectedResult(String calcString) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        expectedResult = (double) engine.eval(calcString);
    }
}