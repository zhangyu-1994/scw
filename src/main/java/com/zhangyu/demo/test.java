package com.zhangyu.demo;

public class test {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory();//JVM能够使用的最大内存
        long totalMemory = Runtime.getRuntime().totalMemory();//JVM使用的内存大小
        System.out.println(maxMemory/1024/1024);
        System.out.println(totalMemory/1024/1024);

    }
}
