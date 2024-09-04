package telran.util;

import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual) {
       //TODO

    }
    
    @BeforeEach
    @Override
    void setUp() {
        map = new TreeMap<>();
        super.setUp();
    }

}