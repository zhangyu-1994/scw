package com.zhangyu.lock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest02 {
    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    demo.incr();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) demo.decr();
        }, "BB").start();
    }
}

class Demo {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void incr() {
        lock.lock();//加锁
        try {
            while (num != 0) {
                condition.await();//进入休眠状态
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " \t " + num);
            condition.signalAll();//唤醒所有
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }

    Set set = new HashSet();

    public void decr() {
        lock.lock();//加锁
        try {
            while (num != 1) {
                condition.await();//进入休眠状态
            }
            num++;
            System.out.println(Thread.currentThread().getName() + " \t " + num);
            condition.signalAll();//唤醒所有
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//释放锁
        }
    }

}