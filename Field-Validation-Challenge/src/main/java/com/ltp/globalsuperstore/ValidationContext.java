package com.ltp.globalsuperstore;

public class ValidationContext {
    private static final ThreadLocal<Item> currentContext = new ThreadLocal<>();

    public static void setCurrentItem(Item item) {
        currentContext.set(item);
    }

    public static Item getCurrentItem() {
        return currentContext.get();
    }
}
