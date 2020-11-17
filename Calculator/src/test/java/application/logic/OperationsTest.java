package application.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OperationsTest {
    Operations o;

    @Before
    public void setUp() {
        o = new Operations();
        o.setA(7);
        o.setB(15);
    }

    @Test
    public void executesCorrectOperation() {
        o.setOperation("+");
        assertEquals(22, o.executeOperation());
        o.setOperation("-");
        assertEquals(-8, o.executeOperation());
        o.setOperation("*");
        assertEquals(105, o.executeOperation());
//        jakolasku ei vielä toiminnassa
//        o.setOperation("/");
//        assertEquals(0, o.executeOperation());
    }

    @Test
    public void resetWorks() {
        o.setOperation("*");
        o.reset();
        assertEquals(0, o.getA());
        assertEquals(0, o.getB());
        assertEquals(null, o.getOperation());
    }

}