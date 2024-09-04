package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

public class HashMapTest extends AbstractMapTest {

    @Override
    <T> void runTest(T[] expected, T[] actual) {
       //TODO

    }
    
    @BeforeEach
    @Override
    void setUp() {
        map = new HashMap<>();
        super.setUp();
    }

}