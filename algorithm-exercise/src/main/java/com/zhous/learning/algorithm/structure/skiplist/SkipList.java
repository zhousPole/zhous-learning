package com.zhous.learning.algorithm.structure.skiplist;

import java.util.Random;

/**
 * 跳表的一种实现方法<br>
 * 跳表中存储的是不重复的正整数
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/11/23
 */
public class SkipList {

    public static void main(String[] args) {
        test(16);
    }

    private static void test(int dataSize) {
        int[] insertData = new int[]{3, 6, 9, 56, 88, 999, 857, 361, 996, 700};
        SkipList skipList = genSkipList(dataSize, insertData);
        System.out.println(skipList.printSkipList());
        System.out.println("find 361:" + skipList.find(361));
        System.out.println("find 22:" + skipList.find(22));
        System.out.println("find 56:" + skipList.find(56));
        System.out.println("delete data");
        for (int data : insertData) {
            skipList.delete(data);
        }
        System.out.println(skipList.printSkipList());
        System.out.println("find 361:" + skipList.find(361));
        System.out.println("find 22:" + skipList.find(22));
        System.out.println("find 56:" + skipList.find(56));

    }

    private static SkipList genSkipList(int randomSize, int[] insertData) {
        SkipList skipList = new SkipList();
        int[] array = new int[randomSize];
        Random random = new Random();
        for (int i = 0; i < randomSize; i++) {
            array[i] = random.nextInt(7000) + 1000;
        }
        for (int a : array) {
            skipList.insert(a);
        }
        if (insertData != null) {
            for (int a : insertData) {
                skipList.insert(a);
            }
        }
        return skipList;
    }

    private Node head;

    private int skipListLevel;

    private double indexPercentage;

    private int currentMaxLevel = 0;

    public SkipList() {
        this(16, 0.5d);
    }

    /**
     * @param skipListLevel   跳表最大层级，第一层级包含全部数据
     * @param indexPercentage 每层级元素个数占上层级的百分比
     */
    public SkipList(int skipListLevel, double indexPercentage) {
        this.skipListLevel = skipListLevel;
        this.indexPercentage = indexPercentage;
        this.head = new Node(skipListLevel);
    }

    public Node find(int value) {
        Node p = head;
        for (int i = currentMaxLevel; i >= 0; i--) {
            while (p.nextNodes[i] != null && p.nextNodes[i].data < value) {
                p = p.nextNodes[i];
            }
            //这里即使找到了和value相等的节点也不返回，因为除第0层外，其他的层级都是索引层级
        }
        if (p.nextNodes[0] != null && p.nextNodes[0].data == value) {
            return p.nextNodes[0];
        }
        return null;
    }

    public void insert(int value) {
        //随机一个层级
        int randomLevel = randomLevel();
        Node newNode = new Node(skipListLevel).setData(value).setMaxLevel(randomLevel);
        //为了维护索引与原始链表大小之间的平衡，新插入的节点需要插入到第一层级到第level层级
        //每层级中待插入节点的前一个节点，默认为head
        Node[] preNodes = new Node[randomLevel + 1];
        for (int i = 0; i <= randomLevel; i++) {
            preNodes[i] = head;
        }
        Node p = head;
        for (int i = currentMaxLevel; i >= 0; i--) {
            while (p.nextNodes[i] != null && p.nextNodes[i].data < value) {
                p = p.nextNodes[i];
            }
            if (i <= randomLevel) {
                preNodes[i] = p;
            }
        }
        for (int i = 0; i <= randomLevel; i++) {
            newNode.nextNodes[i] = preNodes[i].nextNodes[i];
            preNodes[i].nextNodes[i] = newNode;
        }
        if (currentMaxLevel < randomLevel) {
            currentMaxLevel = randomLevel;
        }
    }

    public void delete(int value) {
        Node p = head;
        //每层级中待删除节点的前一个节点
        Node[] preNodes = new Node[currentMaxLevel + 1];
        for (int i = currentMaxLevel; i >= 0; i--) {
            while (p.nextNodes[i] != null && p.nextNodes[i].data < value) {
                p = p.nextNodes[i];
            }
            if (p.nextNodes[i] != null && p.nextNodes[i].data == value) {
                preNodes[i] = p;
            }
        }
        for (int i = 0; i < preNodes.length; i++) {
            Node preNode = preNodes[i];
            if (preNode != null) {
                preNode.nextNodes[i] = preNode.nextNodes[i].nextNodes[i];
            }
        }
        while (currentMaxLevel > 0 && head.nextNodes[currentMaxLevel] == null) {
            currentMaxLevel--;
        }
    }

    /**
     * 理论来讲，一级索引中元素个数应该占原始数据的 {@link this#indexPercentage}，
     * 二级索引中元素个数占 {@link this#indexPercentage} * {@link this#indexPercentage}，一直到最顶层。<br>
     * 此方法{@link this#indexPercentage}概率返回1<br>
     * {@link this#indexPercentage} * {@link this#indexPercentage}概率返回2
     *
     * @return
     */
    private int randomLevel() {
        int level = 0;
        while (Math.random() < indexPercentage && level < skipListLevel) {
            level++;
        }
        return level;
    }

    public String printSkipList() {
        StringBuilder builder = new StringBuilder();
        builder.append("***********SkipList print***************\n");
        for (int i = currentMaxLevel; i >= 0; i--) {
            String print = levelPrint(i);
            builder.append(print).append("\n");
            if (i != 0) {
                for (int j = 0; j < print.length(); j++) {
                    builder.append("-");
                }
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private String levelPrint(int level) {
        String delimiter = " -> ";
        StringBuilder builder = new StringBuilder();
        if (level <= currentMaxLevel) {
            builder.append("[Level-").append(level).append("] ");
            Node current = head;
            boolean first = true;
            while (current.nextNodes[level] != null) {
                if (!first) {
                    builder.append(delimiter);
                }
                builder.append(current.nextNodes[level].data);
                current = current.nextNodes[level];
                first = false;
            }
        }
        return builder.toString();
    }

    public class Node {
        /**
         * 本节点最大层级
         */
        private int maxLevel = 0;
        /**
         * 数据
         */
        private int data = -1;
        /**
         * 下一个节点及其在所有层级的节点
         */
        private Node[] nextNodes;

        Node(int skipListLevel) {
            nextNodes = new Node[skipListLevel];
        }

        public Node setMaxLevel(int maxLevel) {
            this.maxLevel = maxLevel;
            return this;
        }

        public Node setData(int data) {
            this.data = data;
            return this;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{")
                    .append("data: ")
                    .append(data)
                    .append(",maxLevel: ")
                    .append(maxLevel)
                    .append("}");
            return stringBuilder.toString();
        }
    }

}