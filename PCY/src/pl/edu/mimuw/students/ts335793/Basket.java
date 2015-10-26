package pl.edu.mimuw.students.ts335793;

import java.util.*;

public class Basket {

    ArrayList<Item> items;

    public Basket() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public ArrayList<ArrayList<Item>> getSingletons() {
        ArrayList<ArrayList<Item>> singletons = new ArrayList<>();
        for (Item item : items) {
            ArrayList<Item> singleton = new ArrayList<>();
            singleton.add(item);
            singletons.add(singleton);
        }
        return singletons;
    }

    public ArrayList<ArrayList<Item>> getPairs() {
        ArrayList<ArrayList<Item>> pairs = new ArrayList<>();
        Collections.sort(items);
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                ArrayList<Item> itemPair = new ArrayList<>();
                itemPair.add(items.get(i));
                itemPair.add(items.get(j));
                pairs.add(itemPair);
            }
        }
        return pairs;
    }
}
