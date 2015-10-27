package pl.edu.mimuw.students.ts335793;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PCY {

    private int supportThreshold;
    private int maxId;
    private int maxBucket;

    private int[] itemCounter;
    private CompressedBucketCounter compressedPairCounter;

    private ArrayList<Item> frequentItems;
    private ArrayList<ArrayList<Item>> frequentPairs;

    public PCY(int supportThreshold, int maxId, int maxBucket) {
        this.supportThreshold = supportThreshold;
        this.maxId = maxId;
        this.maxBucket = maxBucket;
    }

    private void firstPass(BasketReader basketReader) {
        itemCounter = new int[maxId + 1];
        BucketCounter pairCounter = new BucketCounter(maxBucket + 1, supportThreshold);

        while (basketReader.hasNext()) {
            Basket basket = basketReader.next();
            for (Item item : basket.getItems()) {
                itemCounter[item.getId()] = Math.min(itemCounter[item.getId()] + 1, supportThreshold);
            }
            for (ArrayList<Item> pair : basket.getPairs()) {
                pairCounter.incrementCounter(pair);
            }
        }

        compressedPairCounter = pairCounter.toCompressedBucketCounter();

        frequentItems = new ArrayList<>();
        for (int i = 0; i <= maxId; i++) {
            if (itemCounter[i] == supportThreshold) {
                frequentItems.add(new Item(i));
            }
        }
    }

    private void secondPass(BasketReader basketReader) {
        HashMap<ArrayList<Item>, Integer> frequentPairCandidates = new HashMap<>();

        while (basketReader.hasNext()) {
            Basket basket = basketReader.next();

            for (ArrayList<Item> pair : basket.getPairs()) {
                if (itemCounter[pair.get(0).getId()] == supportThreshold
                 && itemCounter[pair.get(1).getId()] == supportThreshold
                 && compressedPairCounter.isFrequent(pair)) {
                    //int count = frequentPairCandidates.getOrDefault(pair, 0);
                    int count = 0;
                    if (frequentPairCandidates.containsKey(pair)) {
                        count = frequentPairCandidates.get(pair);
                    }
                    frequentPairCandidates.put(pair, Math.min(supportThreshold, count + 1));
                }
            }
        }

        frequentPairs = new ArrayList<>();
        for(Map.Entry<ArrayList<Item>, Integer> entry : frequentPairCandidates.entrySet()) {
            if (entry.getValue() == supportThreshold) {
                frequentPairs.add(entry.getKey());
            }
        }
    }

    public void runAlgorithm(BasketReader basketReader) {
        firstPass(basketReader);
        basketReader.reset();
        secondPass(basketReader);
    }

    public ArrayList<Item> getFrequentItems() {
        return frequentItems;
    }

    public ArrayList<ArrayList<Item>> getFrequentPairs() {
        return frequentPairs;
    }
}
