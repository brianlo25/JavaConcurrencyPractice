package concurrency.chapter25;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache<K, V> {
    private final LinkedList<K> keyList = new LinkedList<>();

    private final Map<K, V> cache = new HashMap<>();

    private final int capacity;

    private final CacheLoader<K, V> cacheLoader;

    public LRUCache(int capacity, CacheLoader<K, V> cacheLoader) {
        this.capacity = capacity;
        this.cacheLoader = cacheLoader;
    }

    public void put(K key, V value){
        if (keyList.size() >= capacity){
            K eldestKey = keyList.removeFirst();
            cache.remove(eldestKey);
        }

        if (keyList.contains(key)){
            keyList.remove(key);
        }
        keyList.addLast(key);
        cache.put(key, value);
    }

    public V get(K key){
        V value;
        boolean success = keyList.remove(key);
        if (!success){
            value = cacheLoader.load(key);
            this.put(key, value);
        }else {
            value = cache.get(key);
            keyList.addLast(key);
        }
        return value;
    }

    @Override
    public String toString() {
        return this.keyList.toString();
    }

    public static void main(String[] args) {
        LRUCache<String, Reference> cache = new LRUCache<>(5, key -> new Reference());
        cache.get("Alex");
        cache.get("Jack");
        cache.get("Gavin");
        cache.get("Dillon");
        cache.get("Leo");
        cache.get("Jenny");

        System.out.println(cache.toString());
    }
}
