package com.zhous.learning.algorithm.utils;

import com.zhous.learning.algorithm.structure.linkedlist.LinkedListNode;

/**
 * 链表工具类
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/4/8
 */
public class LinkedListUtils {

    /**
     * null
     */
    private static final String NULL_STRING = "null";

    /**
     * 创建单链表
     *
     * @param dataList 数据集合
     * @param <T>      数据类型
     * @return
     */
    public static <T> LinkedListNode<T> createSingleLinkedList(T... dataList) {
        return createLinkedList(true, false, dataList);
    }

    /**
     * 创建单向循环链表
     *
     * @param dataList 数据集合
     * @param <T>      数据类型
     * @return
     */
    public static <T> LinkedListNode<T> createSingleCycleLinkedList(T... dataList) {
        return createLinkedList(true, true, dataList);
    }

    /**
     * 创建双向链表
     *
     * @param dataList 数据集合
     * @param <T>      数据类型
     * @return
     */
    public static <T> LinkedListNode<T> createDoublyLinkedList(T... dataList) {
        return createLinkedList(false, false, dataList);
    }

    /**
     * 创建双向循环链表
     *
     * @param dataList 数据集合
     * @param <T>      数据类型
     * @return
     */
    public static <T> LinkedListNode<T> createDoublyCycleLinkedList(T... dataList) {
        return createLinkedList(false, true, dataList);
    }

    /**
     * 打印链表
     *
     * @param head 链表头指针
     */
    public static void print(LinkedListNode head) {
        LogUtils.info(printToString(head));
    }

    public static String printToString(LinkedListNode head) {
        LinkedListNode firstNode = head;
        StringBuffer sb = new StringBuffer();
        if (head != null) {
            sb.append(toString(head.value));
            boolean doubly = false;
            while ((head = head.next) != null && head != firstNode) {
                sb.append("->").append(toString(head.value));
                doubly = head.previous != null;
            }
            if (head == firstNode) {
                if (doubly) {
                    sb.append(" [Doubly Cycle Linked List]");
                } else {
                    sb.append(" [Single Cycle Linked List]");
                }
            } else {
                if (doubly) {
                    sb.append(" [Doubly Linked List]");
                } else {
                    sb.append(" [Single Linked List]");
                }
            }
        } else {
            sb.append("empty Linked List!");
        }
        return sb.toString();
    }

    /**
     * 创建链表，返回头节点
     *
     * @param single   是否单链表
     * @param cycle    是否循环链表
     * @param dataList 数据集合
     * @param <T>      数据类型
     * @return
     */
    private static <T> LinkedListNode<T> createLinkedList(boolean single, boolean cycle, T... dataList) {
        if (dataList != null) {
            if (dataList.length > 0) {
                //头节点
                LinkedListNode<T> head = null;
                //尾节点
                LinkedListNode<T> tail = null;
                //每次循环创建的两个节点的第二个节点
                LinkedListNode<T> previous = null;
                //每次读取两个节点的数据进行创建
                for (int i = 0; i < dataList.length; i += 2) {
                    T value = dataList[i];
                    LinkedListNode<T> current = new LinkedListNode<>(value, null, null);
                    LinkedListNode<T> next = null;
                    if (i + 1 < dataList.length) {
                        T nextValue = dataList[i + 1];
                        next = new LinkedListNode<>(nextValue, null, null);
                    }
                    if (previous != null) {
                        //链接本次创建的两个节点和上次创建的两个节点
                        previous.next = current;
                    }
                    current.next = next;
                    if (!single) {
                        //非双链表需要处理反向指针
                        current.previous = previous;
                        if (next != null) {
                            next.previous = current;
                        }
                    }
                    if (i == 0) {
                        head = current;
                    }
                    if (i == dataList.length - 1) {
                        tail = current;
                    }
                    if (i + 1 == dataList.length - 1) {
                        tail = next;
                    }
                    previous = next;
                }
                if (cycle) {
                    //循环链表需要链接头节点和尾节点
                    tail.next = head;
                    if (!single) {
                        head.previous = tail;
                    }
                }
                return head;
            }
        }
        return null;
    }

    private static <T> String toString(T value) {
        if (value == null) {
            return NULL_STRING;
        } else {
            return value.toString();
        }
    }

}