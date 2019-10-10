package com.hong.queue;

public class Test {
    public static void main(String[] args) {
        cycleQueueTest();
    }

    public static void queueTest(){
        Queue queue = new Queue(10);

        queue.add("a");
        queue.add("b");
        queue.add("c");
        System.out.println(queue.take());
        System.out.println(queue.take());
        queue.add("d");
        System.out.println(queue.take());
        System.out.println(queue.take());
    }

    public static void cycleQueueTest(){
        CycleQueue cycleQueue = new CycleQueue(3);
        cycleQueue.add("a");
        cycleQueue.add("b");
        cycleQueue.add("c");
        System.out.println(cycleQueue.take());
        System.out.println(cycleQueue.take());
        cycleQueue.add("d");
        System.out.println(cycleQueue.take());
        System.out.println(cycleQueue.take());
    }
}
