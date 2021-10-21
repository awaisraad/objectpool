package com.awaisraad.objectpool.demo;

import com.awaisraad.objectpool.ObjectPool;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrinterApplication {

    public static void main(String[] args) {
        var pool = new ObjectPool<Printer>();

        IntStream.range(0, 500).forEach(i -> {
            var name = "User<" + i + ">";
            var thread = new Thread(new PrinterClient(name, pool));
            thread.start();
        });

        var supplier = new PrinterSupplier();
        Stream.generate(supplier).limit(5).forEach(pool::add);
    }

}
