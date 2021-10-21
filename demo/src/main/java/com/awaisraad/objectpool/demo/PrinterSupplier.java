package com.awaisraad.objectpool.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class PrinterSupplier implements Supplier<Printer> {
    private final AtomicInteger counter;

    public PrinterSupplier() {
        this.counter = new AtomicInteger();
    }

    @Override
    public Printer get() {
        return new Printer("Printer<" + counter.incrementAndGet() + ">");
    }
}
