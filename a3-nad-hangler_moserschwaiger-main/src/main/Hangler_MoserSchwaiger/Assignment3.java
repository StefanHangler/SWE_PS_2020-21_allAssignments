package Hangler_MoserSchwaiger;

import assignment3_int.Calculator;
import assignment3_int.GenericStack;

/**
 * Execute arithmetic operations for a RPN (reverse-polish-notation) calculator
 */
public class Assignment3 implements Calculator {

        final String isNumber= "\\d+(\\.\\d)?";
        //private String isArithmeticSymbol = "[+/*-]";

        // empty default constructor, do not change
        public Assignment3(){}

        /**
         * returns the result or throws an (Runtime-) Exception if anything goes wrong (e.g. illegal input)
         *
         * @param input numbers and arithmetic symbols in the right order for a RPN calculator
         * @param stack result of all arithmetic operations from the input string array
         */
        @Override
        public double calc(String[] input, GenericStack<Double> stack) {
              for(String s : input){

                      //if input is a number - add to stack
                      if(s.matches(isNumber)) {
                              stack.push(Double.parseDouble(s));
                      }
                      //then the input is a arithmetic- or wrong symbol
                      else {
                              stack.push(this.singleCalculation(s,stack));
                      }
              }
              double result = stack.pop();

              //check if the result was the last element in the list, if true then push the result again and return it
              if(stack.isEmpty()) {
                      return result;
              }

              throw new RuntimeException("Stack contains more than one Element. Input is wrong");
        }

        /**
         * Execute an arithmetic operation
         * @param symbol for the arithmetic operation
         * @param stack given stack, where the numbers for the operations are stored
         * @return double: the result of the arithmetic operation
         */
        private double singleCalculation(String symbol, GenericStack<Double> stack){

                if(symbol.equals("+")){
                        return stack.pop() + stack.pop();
                }
                else if(symbol.equals("-")){
                        double a = stack.pop();
                        return stack.pop() - a;
                }
                else if(symbol.equals("*")){
                        return stack.pop() * stack.pop();
                }
                else if(symbol.equals("/")){
                        double d = stack.pop();
                        return stack.pop() / d;
                }

                throw new RuntimeException("Symbol " + symbol + " is neither an arithmetic symbol nor a number");
        }

        /**
         * returns an array-based stack
         */
        @Override
        public GenericStack<Double> getArrayStack() {
                return new StackArray<>(20);
        }

        /**
         * returns a list-based stack
         */
        @Override
        public GenericStack<Double> getListStack() {
                return new StackLinkedList<>();
        }
}
