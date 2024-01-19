package com.example.demo.threadsafesingleton;

public class BillPughSingleton {

    private BillPughSingleton() {
    }

    // not loaded when BillPughSingleton is init
    private static class BillPughSingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return BillPughSingletonHelper.INSTANCE;
    }
}
