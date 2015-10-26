package pl.edu.mimuw.students.ts335793;

import org.apache.hadoop.fs.FSDataInputStream;

import java.io.IOException;
import java.util.Iterator;

public class BasketReader implements Iterator<Basket> {

    private Basket nextBasket;
    private FSDataInputStream inputStream;

    public BasketReader(FSDataInputStream inputStream) {
        this.inputStream = inputStream;
        tryReadNextBasket();
    }

    public void reset() {
        try {
            inputStream.seek(0);
        } catch (IOException e) {
            System.out.println("Error when resetting basket reader.");
            System.out.println(e.getLocalizedMessage());
        }
        tryReadNextBasket();
    }

    private void tryReadNextBasket() {
        Basket basket = new Basket();
        int id;
        while (true) {
            try {
                id = inputStream.readInt();
            } catch (IOException e) {
                nextBasket = null;
                return;
            }
            if (id == -1) {
                break;
            }
            basket.addItem(new Item(id));
        }
        nextBasket = basket;
    }

    @Override
    public boolean hasNext() {
        return nextBasket != null;
    }

    @Override
    public Basket next() {
        Basket basket = nextBasket;
        tryReadNextBasket();
        return basket;
    }
}
