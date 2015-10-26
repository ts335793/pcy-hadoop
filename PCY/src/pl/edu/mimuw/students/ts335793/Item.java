package pl.edu.mimuw.students.ts335793;

public class Item implements Comparable<Item> {
    private int id;

    public Item(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Item other) {
        if (this.id < other.id) {
            return -1;
        } else if (this.id == other.id) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "i" + id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Item) {
            return id == ((Item) o).id;
        } else {
            return false;
        }
    }
}
