package com.example.demo.concurrentcore.linkedlist;

import java.util.Random;

public class SimpleSkipList {
    private final static byte HEAD_BIT = (byte)-1;

    private final static byte TAIL_BIT = (byte)1;

    private final static byte DATA_BIT = (byte)0;

    private static class Node {
        private Integer value;

        private Node up, down, left, right;

        private byte bit;

        public Node(Integer value) {
            this(value, DATA_BIT);
        }

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }
    }

    private Node head;

    private Node tail;

    private int size;

    private int height;

    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    private Node find(Integer element) {
        Node current = head;
        for (;;) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }
            if (current.down != null) {
                current = current.down;
            } else {
                break;
            }
        }
        return current;
    }

    public void add(Integer element) {
        Node nearNode = this.find(element);
        Node newNode = new Node(element);
        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;

        int currentLevel = 0;

        while (random.nextDouble() < 0.5d) {
            if (currentLevel >= height) {
                height++;
                Node dummyHead = new Node(null, HEAD_BIT);
                Node dummyTail = new Node(null, TAIL_BIT);
                dummyHead.right = dummyTail;
                dummyHead.down = head;
                head.up = dummyHead;
                dummyTail.left = dummyHead;
                dummyTail.down = tail;
                tail.up = dummyTail;
                head = dummyHead;
                tail = dummyTail;
            }
            while ((nearNode != null) && nearNode.up == null) {
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;
            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;
            nearNode.right.left = upNode;
            nearNode.right = upNode;
            newNode.up = upNode;
            newNode = upNode;
            currentLevel++;
        }
        size++;
    }

    public boolean contains(Integer element) {
        Node node = this.find(element);
        return (node.value.equals(element));
    }

    public Integer get(Integer element) {
        Node node = this.find(element);
        return (node.value.equals(element)) ? node.value : null;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }
}
