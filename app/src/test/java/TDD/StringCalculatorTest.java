package TDD;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private StringCalculator sc = new StringCalculator();

    @Test
    void customDelimiterTest() {
        assertEquals(15, sc.calculate("?\n1?2?3?4?5"));
        assertEquals(95, sc.calculate("+\n15+30+40+1+6+3"));
        assertEquals(0, sc.calculate("a\n0a1a2a3a-6a0"));
        assertEquals(18, sc.calculate("1\n513141214"));
    }

    @Test 
    void emptyStringTest() {
        assertEquals(0, sc.calculate(""));
    }

    @Test
    void illegalDelimiterTest() {
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("1?2");});
    }

    @Test
    void invalidCustomDelimiterTest() {
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("?");});
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("?\n");});
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("?\n1+2+3+4+5");});
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("?\n1?2?3?a?5");});
    }

    @Test
    void invalidValueTest() {
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("a");});
    }

    @Test
    void mixedDelimiterTest() {
        assertEquals(100, sc.calculate("10,10:10,10,10:10:10:10,10:10"));
    }

    @Test
    void multipleDelimiterTest() {
        assertEquals(0, sc.calculate("50,100,150,-150,-100,-50"));
    }

    @Test
    void secondaryDelimiterTest() {
        assertEquals(15, sc.calculate("1:2:3:4:5"));
    }

    @Test
    void singleDelimiterTest() {
        assertEquals(10, sc.calculate("5,5"));
    }

    @Test
    void singleIntTest() {
        assertEquals(1, sc.calculate("1"));
    }

    @Test
    void surroundedInvalidValueTest() {
        assertThrows(IllegalArgumentException.class, () -> {sc.calculate("1,2,3,a,5");});
    }
}
