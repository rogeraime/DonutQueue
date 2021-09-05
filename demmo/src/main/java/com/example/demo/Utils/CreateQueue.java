package com.example.demo.Utils;

import com.example.demo.entity.Order;

import java.util.PriorityQueue;

public class CreateQueue {

    private  static  PriorityQueue<Order> queue = new PriorityQueue<>();

    private CreateQueue() {
    }

    public static PriorityQueue<Order> getInstance()
    {
        if (queue==null)
            queue = new PriorityQueue<>();
        return queue;
    }
}
