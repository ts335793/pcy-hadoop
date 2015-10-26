package pl.edu.mimuw.students.ts335793;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PCY {

    private int supportThreshold;
    private int maxId;
    private int maxBucket;

    private CompressedBucketCounter compressedItemCounter;
    private CompressedBucketCounter compressedPairCounter;

    public PCY(int supportThreshold, int maxId, int maxBucket) {
        this.supportThreshold = supportThreshold;
        this.maxId = maxId;
        this.maxBucket = maxBucket;
    }

    private void firstPass(BasketReader basketReader) {
        BucketCounter itemCounter = new BucketCounter(maxId + 1, supportThreshold);
        BucketCounter pairCounter = new BucketCounter(maxBucket + 1, supportThreshold);

        System.out.println("COKOLWIEK");

        while (basketReader.hasNext()) {
            Basket basket = basketReader.next();
            System.out.println(basket.getSingletons());
            for (ArrayList<Item> singleton : basket.getSingletons()) {
                itemCounter.incrementCounter(singleton);
            }
            for (ArrayList<Item> pair : basket.getPairs()) {
                pairCounter.incrementCounter(pair);
            }
        }

        compressedItemCounter = itemCounter.toCompressedBucketCounter();
        System.out.println(compressedItemCounter);
        compressedPairCounter = pairCounter.toCompressedBucketCounter();
        System.out.println(compressedPairCounter);
    }

    private ArrayList<ArrayList<Item>> secondPass(BasketReader basketReader) {
        HashMap<ArrayList<Item>, Integer> frequentPairCandidates = new HashMap<>();

        while (basketReader.hasNext()) {
            Basket basket = basketReader.next();

            for (ArrayList<Item> pair : basket.getPairs()) {
                ArrayList<Item> firstSingleton = new ArrayList<>();
                firstSingleton.add(pair.get(0));
                ArrayList<Item> secondSingleton = new ArrayList<>();
                secondSingleton.add(pair.get(1));

                if (compressedItemCounter.isFrequent(firstSingleton)
                 && compressedItemCounter.isFrequent(secondSingleton)
                 && compressedPairCounter.isFrequent(pair)) {
                    int count = frequentPairCandidates.getOrDefault(pair, 0);
                    frequentPairCandidates.put(pair, Math.min(supportThreshold, count + 1));
                }
            }
        }

        System.out.println(frequentPairCandidates);

        ArrayList<ArrayList<Item>> frequentPairs = new ArrayList<>();
        for(Map.Entry<ArrayList<Item>, Integer> entry : frequentPairCandidates.entrySet()) {
            if (entry.getValue() == supportThreshold) {
                frequentPairs.add(entry.getKey());
            }
        }

        return frequentPairs;
    }

    public ArrayList<ArrayList<Item>> getFrequentPairs(BasketReader basketReader) {
        firstPass(basketReader);
        basketReader.reset();
        return secondPass(basketReader);
    }
}
