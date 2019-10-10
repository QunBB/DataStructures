package com.hong.queue;

/**
 * 环形队列：在队列可以同时存在maxSize个元素，不管之前已经出列了多少元素
 */
public class CycleQueue {

    private int maxSize; // 队列的最大容量
    private int first = -1; // 第一个成员的index
    private int last = 0; // 最后一个成员的index +1
    private String[] arr;

    public CycleQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new String[maxSize];
    }

    public void add(String e){
        if (last-first == maxSize+1){
            System.out.println("队列已满，无法添加");
            return;
        }

        arr[last%maxSize] = e;
        last ++;
    }

    public String take(){
        if (first == last-1){
            System.out.println("队列为空，无法出列");
            return null;
        }

        first ++;
        String e = arr[first%maxSize];
        return e;

    }
}
