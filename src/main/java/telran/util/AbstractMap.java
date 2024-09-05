package telran.util;

@SuppressWarnings("unchecked")
public abstract class AbstractMap<K, V> implements Map<K, V> {
    protected Set<Entry<K, V>> set;
    protected abstract Set<K> getEmptyKeySet();
    
    @Override
    public V get(Object key) {
        Entry<K, V> pattern = new Entry<>((K)key, null);
        Entry<K,V> entry = set.get(pattern);
        V res = null;
        if (entry != null) {
            res = entry.getValue();
        }
        return res;
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> pattern  = new Entry<K, V>(key, value);
        boolean res = set.add(pattern);
        return res == true ? value : null;
    }

    @Override
    public boolean containsKey(Object key) {
        Entry<K, V> entry = new Entry<>((K) key, null);
        return set.contains(entry);
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsValue'");
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = getEmptyKeySet();
        return keySet;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
       return set;
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public int size() {
       return set.size();
    }

    @Override
    public boolean isEmpty() {
       return set.isEmpty();
    }

}