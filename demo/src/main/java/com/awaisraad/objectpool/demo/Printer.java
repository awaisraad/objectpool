package com.awaisraad.objectpool.demo;

public record Printer(String name) {
    public void print(String line) {
        System.out.println(name + " " + line);
    }
}
