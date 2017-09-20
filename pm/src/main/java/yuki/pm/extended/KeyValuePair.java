package yuki.pm.extended;

/**
 * Created by Akeno on 2017/01/05.
 */

public class KeyValuePair<K, V> {
    public K getKey() {
        return Key;
    }

    public void setKey(K key) {
        Key = key;
    }

    public V getValue() {
        return Value;
    }

    public void setValue(V value) {
        Value = value;
    }

    private K Key;
    private V Value;
}
