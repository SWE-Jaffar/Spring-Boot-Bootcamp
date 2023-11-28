package com.ltp.globalsuperstore.repositery;

import java.util.ArrayList;
import java.util.List;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;

public class StoreRepo {
    
    List<Item> items = new ArrayList<>();

    public int getIndexFromId(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Item getItemIndex(int index) {
        return items.get(index);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item setItem(int index, Item item) {
        return items.set(index, item);
    }

    public List<Item> getItems() {
        return items;
    }

}
