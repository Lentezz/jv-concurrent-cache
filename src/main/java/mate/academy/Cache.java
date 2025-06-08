package mate.academy;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache<K, V> {
    private final HashMap<K, V> cache;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Cache() {
        cache = new HashMap<>();
    }

    public V get(K key) {
        lock.readLock().lock();
        V val;
        try {
            val = cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
        return val;
    }

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void remove(K key) {
        lock.writeLock().lock();
        try {
            cache.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void clear() {
        lock.writeLock().lock();
        try {
            cache.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        int size;
        try {
            size = cache.size();
        } finally {
            lock.readLock().unlock();
        }
        return size;
    }
}
