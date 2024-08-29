package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    List<T>[] hashTable;
    float factor;
    int size;

    private class HashSetIterator implements Iterator<T> {
        //Hint:
        Iterator<T> currentIterator;
        Iterator<T> prevIterator;
        int indexIterator = 0;
        int insideIndexIterator = 0;
        
        @Override
        public boolean hasNext() {
            boolean res = internalHasNext();
            if (res == false) {
                res = externalHasNext();
            }
            return res;
        }

        public boolean internalHasNext() {
            Iterator<T> it = hashTable[indexIterator].iterator();
            return it.hasNext() ? true : false;
        }

        public boolean externalHasNext() {
            int i = indexIterator + 1;
            while(hashTable[i] == null && i < hashTable.length) {
                i++;
            }
            return i == hashTable.length? false : true;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

        }

        private T internalNext() {
            Iterator<T> it = hashTable[indexIterator].iterator();
            if (it.hasNext()) {
                
            }
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'next'");
        }
    }

    public HashSet(int hashTableLength, float factor) {
        hashTable = new List[hashTableLength];
        this.factor = factor;
    }

    public HashSet() {
        this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            if (size >= hashTable.length * factor) {
                hashTableReallocation();
            }

            addObjInHashTable(obj, hashTable);
            size++;
        }
        return res;

    }

    private void addObjInHashTable(T obj, List<T>[] table) {
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list == null) {
            list = new ArrayList<>(3);
        }
        list.add(obj);
    }

    private int getIndex(T obj, int length) {
        int hashCode = obj.hashCode();
        return Math.abs(hashCode % length);
    }

    private void hashTableReallocation() {
       List<T> []tempTable = new List[hashTable.length * 2];
       for(List<T> list: hashTable) {
        if(list != null) {
            list.forEach(obj -> addObjInHashTable(obj, tempTable));
            list.clear(); //??? for testing if it doesn't work remove this statement
        }
       }
       hashTable = tempTable;

    }

    @Override
    public boolean remove(T pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
       return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        int index = getIndex(pattern, hashTable.length);
        List<T> list = hashTable[index];
        return list != null && list.contains(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashSetIterator();
    }

    @Override
    public T get(Object pattern) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

}