import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class GitTest {
    @Test
    public void testDivide() {
        int x = 1;
        int y = 2;
        demo obj = new demo();
        double result = obj.divide(x, y);
        // assertions -> compare expected value with result
        assertEquals(0.5, result);
    }
}
