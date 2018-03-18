package common;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public final class TestUtils {

    private TestUtils() {
        
    }

    public static List<Item> createNonImportSampleItems() {
        List<Item> itemList = new ArrayList<>(10);
        Item item1 = new Item(12.49, "book", 1, false);
        Item item2 = new Item(14.99, "music CD", 1, false);
        Item item3 = new Item(0.85, "chocolate bar", 1, false);
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        return itemList;
    }


    public static List<Item> createImportSampleItems() {
        List<Item> itemList = new ArrayList<>(10);
        Item item1 = new Item(10.00, "box of chocolates", 1, true);
        Item item2 = new Item(27.99, "bottle of perfume", 1, true);
        itemList.add(item1);
        itemList.add(item2);
        return itemList;
    }
}
