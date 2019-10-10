package com.hong.queue;


/**
 * 队列：先入先出，后入后出
 * 最多只能入列maxSize个元素，不管是否已经出列
 */
public class Queue {

    private int maxSize; // 队列的最大容量
    private int first = -1; // 第一个成员的index
    private int last = 0; // 最后一个成员的index +1
    private String[] arr;

    public Queue(int maxSize){
        this.maxSize = maxSize;
        arr = new String[maxSize];
    }

    public String take(){
        if (first+1 == last){
            System.out.println("队列为空，无法出列");
            return null;
        }

        first ++;
        String e = arr[first];
        return e;
    }

    public void add(String e){
        if (last == maxSize){
            System.out.println("队列已满，无法添加");
            return;
        }

        arr[last] = e;
        last ++;
    }
}
