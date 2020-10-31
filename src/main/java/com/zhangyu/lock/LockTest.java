package com.zhangyu.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    ticket.sale();
                }
            }
        }, "aa").start();
        new Thread(()->{for (int i = 0; i < 30; i++) ticket.sale();},"bb").start();//lambda表达式写法
    }
}

//卖票实例
class Ticket {
    private int number = 30;
    private Lock lock = new ReentrantLock();

    //卖票的方法
    public void sale() {
        lock.lock();
        try {

            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}