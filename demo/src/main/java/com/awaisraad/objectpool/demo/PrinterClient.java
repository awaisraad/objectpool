package com.awaisraad.objectpool.demo;

import com.awaisraad.objectpool.ObjectPool;

public record PrinterClient(String name, ObjectPool<Printer> pool) implements Runnable {
    @Override
    public void run() {
        var printer = pool.acquire();
        if (printer != null) {
            printer.print(name + " " + "Hello World from Printer");
        }
        pool.release(printer);
    }
}
