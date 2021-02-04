package Hangler_MoserSchwaiger;

import assignment3_int.GenericStack;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EmptyStackException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GenericStackTest {

    public static Stream<Arguments> Stacks() {
        return Stream.of(
                Arguments.of(new StackArray<>(5)),
                Arguments.of(new StackLinkedList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("is empty")
    void isEmpty(GenericStack<Double> stack) {
        assertTrue(stack.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("throws RuntimeException when popped")
    void popException(GenericStack<Double> stack) {
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("throws EmptyStackException when peeked")
    void peekException(GenericStack<Double> stack) {
        assertThrows(RuntimeException.class, stack::peek);
    }

    @Test
    @DisplayName("push: throws StackOverflowException max ArrayStack reached")
    void pushException(){
        StackArray<Integer> a = new StackArray<>(3);
        a.push(1);
        a.push(2);
        a.push(3);

        assertThrows(StackOverflowError.class, () -> a.push(3));
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("size of an empty Stack")
    void getSizeTest(GenericStack<Double> stack) {
        assertEquals(0,stack.getSize());
    }


    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("is not empty")
    void isNotEmptyTest(GenericStack<Double> stack) {
        stack.push(1.0);
        assertFalse(stack.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("pop the pushed element and is empty")
    void popTest(GenericStack<Double> stack) {
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.push(4.0);

        assertEquals(4.0, stack.pop());
        assertEquals(3.0, stack.pop());
        assertEquals(2.0, stack.pop());
        assertEquals(1.0, stack.pop());

        assertTrue(stack.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("peek the pushed element and is empty")
    void peekTest(GenericStack<Double> stack) {
        stack.push(1.0);
        stack.push(2.0);
        stack.push(3.0);
        stack.push(4.0);

        assertEquals(4.0, stack.peek());
        assertEquals(4.0, stack.peek());

        assertFalse(stack.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("Stacks")
    @DisplayName("size of stack with 4 elements")
    void getSizeWithElementsTest(GenericStack<Double> stack) {
        for(int i=4; i>0; i--) {
            stack.push((double)i);
        }

        assertEquals(4,stack.getSize());
    }
}