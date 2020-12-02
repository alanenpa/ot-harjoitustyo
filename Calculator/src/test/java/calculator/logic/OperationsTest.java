package calculator.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationsTest {
    Operations o;

    @Before
    public void setUp() {
        o = new Operations();
        o.setA(30);
        o.setB(15);
    }

    @Test
    public void resetting() {
        o.setOperation("*");
        o.reset();
        assertEquals(0, o.getA(), 0);
        assertEquals(0, o.getB(), 0);
        assertNull(o.getOperation());
        assertFalse(o.isUndefined());
    }

    @Test
    public void basicArithmeticOperations() {
        o.setOperation("+");
        assertEquals(45, o.executeOperation(), 0);
        o.setOperation("-");
        assertEquals(15, o.executeOperation(), 0);
        o.setOperation("*");
        assertEquals(450, o.executeOperation(), 0);
        o.setOperation("/");
        assertEquals(2, o.executeOperation(), 0);
    }

    @Test
    public void dividingByZero() {
        o.setB(0);
        o.setOperation("/");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
    }

    @Test
    public void power() {
        o.setA(4);
        o.setB(5);
        o.setOperation("^");
        assertEquals(1024, o.executeOperation(), 0);
        o.setOperation("^2");
        assertEquals(16, o.executeOperation(), 0);
    }

    @Test
    public void squareRoot() {
        o.setA(9);
        o.setOperation("sqrtRoot");
        assertEquals(3, o.executeOperation(), 0);
    }

    @Test
    public void factorial() {
        o.setA(4);
        o.setOperation("n!");
        assertEquals(24, o.executeOperation(), 0);
        o.setA(0);
        assertEquals(1, o.executeOperation(), 0);
    }

    @Test
    public void factorialWithANegativeNumber() {
        o.setA(-5);
        o.setOperation("n!");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
    }

    @Test
    public void Combination() {
        o.setA(8);
        o.setB(5);
        o.setOperation("nCr");
        assertEquals(56, o.executeOperation(), 0);
    }

    @Test
    public void CombinationWithBBiggerThanA() {
        o.setA(5);
        o.setB(8);
        o.setOperation("nCr");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
    }

    @Test
    public void CombinationWithNegativeNumbers() {
        o.setA(2);
        o.setB(-8);
        o.setOperation("nCr");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
        o.setA(-2);
        o.setB(8);
        o.setOperation("nCr");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
    }

    @Test
    public void Permutation() {
        o.setA(6);
        o.setB(3);
        o.setOperation("nPr");
        assertEquals(120, o.executeOperation(), 0);
    }

    @Test
    public void PermutationWithBBiggerThanA() {
        o.setA(5);
        o.setB(10);
        o.setOperation("nPr");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
    }

    @Test
    public void PermutationWithNegativeNumbers() {
        o.setA(2);
        o.setB(-8);
        o.setOperation("nPr");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
        o.setA(-2);
        o.setB(8);
        o.setOperation("nPr");
        assertEquals(0, o.executeOperation(), 0);
        assertTrue(o.isUndefined());
    }

    @Test
    public void modulo() {
        o.setOperation("mod");
        assertEquals(0, o.executeOperation(), 0);
        o.setA(9);
        o.setB(5);
        assertEquals(4, o.executeOperation(), 0);
    }

    @Test
    public void biggerThanIntegerWorks() {
        assertFalse(o.biggerThanInteger(1));
        assertFalse(o.biggerThanInteger(2));
        o.setA(5E-15);
        o.setB(0.5E-12);
        assertFalse(o.biggerThanInteger(1));
        assertFalse(o.biggerThanInteger(2));
        o.setA(9E15);
        o.setB(14E34);
        assertTrue(o.biggerThanInteger(1));
        assertTrue(o.biggerThanInteger(2));
        o.setA(-32460E15);
        o.setB(-75E23);
        assertTrue(o.biggerThanInteger(1));
        assertTrue(o.biggerThanInteger(2));
    }

    @Test
    public void addingToMemory() {
        o.setMemory(5);
        assertEquals(5, o.getMemory(), 0);
    }

}