import java.util.Objects;

public class MilaHashMap<K, V> {

    private static final int INITIAL_SIZE = 16;
    private static final double LIMIT = 0.75;

    private Pair<K, V>[] table;
    private int count;

    public MilaHashMap() {
        table = new Pair[INITIAL_SIZE];
        count = 0;
    }

    private static class Pair<K, V> {
        K key;
        V value;
        Pair<K, V> next;

        Pair(K key, V value, Pair<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        int hash = key.hashCode();
        if (hash < 0) {
            hash = -hash;
        }
        return hash % table.length;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Pair<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        Pair<K, V> newPair = new Pair<>(key, value, table[index]);
        table[index] = newPair;
        count++;

        if (count > table.length * LIMIT) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Pair<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Pair<K, V> current = table[index];
        Pair<K, V> previous = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                count--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    private void resize() {
        Pair<K, V>[] oldTable = table;
        table = new Pair[oldTable.length * 2];
        count = 0;

        for (Pair<K, V> pair : oldTable) {
            while (pair != null) {
                put(pair.key, pair.value);
                pair = pair.next;
            }
        }
    }

    public int size() {
        return count;
    }
}