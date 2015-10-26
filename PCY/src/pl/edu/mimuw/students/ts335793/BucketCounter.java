package pl.edu.mimuw.students.ts335793;

import java.util.ArrayList;

public class BucketCounter {

    private int[] counters;
    private int supportThreshold;

    public BucketCounter(int size, int supportThreshold) {
        counters = new int[size];
        this.supportThreshold = supportThreshold;
    }

    public void incrementCounter(ArrayList<Item> tuple) {
        int bucket = tuple.hashCode() % counters.length;
        counters[bucket] = Math.min(counters[bucket] + 1, supportThreshold);
    }

    public CompressedBucketCounter toCompressedBucketCounter() {
        CompressedBucketCounter compressedBucketCounter = new CompressedBucketCounter(counters.length);
        for (int i = 0; i < counters.length; i++) {
            if (counters[i] == supportThreshold) {
                compressedBucketCounter.setFrequent(i);
            }
        }
        return compressedBucketCounter;
    }
}
