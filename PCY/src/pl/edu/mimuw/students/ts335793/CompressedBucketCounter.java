package pl.edu.mimuw.students.ts335793;

import java.util.ArrayList;
import java.util.BitSet;

public class CompressedBucketCounter {

    private BitSet bitSet;

    CompressedBucketCounter(int size) {
        bitSet = new BitSet(size);
    }

    public void setFrequent(int i) {
        bitSet.set(i);
    }

    public boolean isFrequent(ArrayList<Item> tuple) {
        int bucket = tuple.hashCode() % bitSet.length();
        return bitSet.get(bucket);
    }

    @Override
    public String toString() {
        return bitSet.toString();
    }
}
