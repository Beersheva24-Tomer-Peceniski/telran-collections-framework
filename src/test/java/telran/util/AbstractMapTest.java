package telran.util;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Map.Entry;

public abstract class AbstractMapTest {
   Integer[] keys = {1, -1};
   Map<Integer, Integer> map;
   
   void setUp() {
      AbstractMap <Integer, Integer> abstractMap;
   }
   
   abstract <T> void runTest(T [] expected, T [] actual);
   //TODO tests
   }