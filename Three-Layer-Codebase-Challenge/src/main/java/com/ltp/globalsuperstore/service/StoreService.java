package com.ltp.globalsuperstore.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.ltp.globalsuperstore.Constants;
import com.ltp.globalsuperstore.Item;
import com.ltp.globalsuperstore.repositery.StoreRepo;

public class StoreService {
    StoreRepo storeRepo = new StoreRepo();

   
    public Item getItemIndex(int index) {
        return storeRepo.getItemIndex(index);
    }

    public void addItem(Item item) {
        storeRepo.addItem(item);
    }

    public Item setItem(int index, Item item) {
        return storeRepo.setItem(index, item);
    }

    public List<Item> getItems() {
        return storeRepo.getItems();
    }

     public int getIndexFromId(String id) {
        for (int i = 0; i < storeRepo.getItems().size(); i++) {
            if (storeRepo.getItemIndex(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

     public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }
    
    String status = Constants.SUCCESS_STATUS;
    
    public void submitItem(Item item){
        int index = getIndexFromId(item.getId());
        if (index == Constants.NOT_FOUND) {
            addItem(item);
        } else if (within5Days(item.getDate(), getItemIndex(index).getDate())) {
            setItem(index, item);
        } else {
            setStatus(Constants.FAILED_STATUS); 
        }
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }




}
