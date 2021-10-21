package com.awaisraad.objectpool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class ObjectPool<T> {
    private final Lock lock;
    private final Condition canRelease;
    private final Queue<T> objects;

    public ObjectPool() {
        this.lock = new ReentrantLock();
        this.canRelease = this.lock.newCondition();
        this.objects = new LinkedList<>();
    }

    public void add(T object) {
        try {
            lock.lock();
            this.objects.add(object);
            this.canRelease.signal();
        } finally {
            lock.unlock();
        }
    }

    public T acquire() {
        try {
            lock.lock();
            while (objects.isEmpty()) {
                canRelease.await();
            }
            return this.objects.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public void release(T object) {
        try {
            lock.lock();
            this.objects.add(object);
            canRelease.signal();
        } finally {
            lock.unlock();
        }
    }
}
