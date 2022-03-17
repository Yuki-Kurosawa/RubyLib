package yuki.pm.extended;

import java.util.Comparator;

/**
 * Created by Akeno on 2017/01/05.
 */

public class KeyValuePairComparator<K,V> implements Comparator {
    @Override
    @SuppressWarnings("unchecked")
    public int compare(Object lhs, Object rhs) {
        return ((KeyValuePair<K,V>)lhs).getKey().toString().compareTo(((KeyValuePair<K,V>)rhs).getKey().toString());
    }
}
