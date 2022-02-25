package com.zhous.learning.algorithm.structure.linkedlist;

/**
 * 链表节点
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/4/8
 */
public class LinkedListNode<T> {

    /**
     * 节点值
     */
    public T value;

    /**
     * 上一个节点
     */
    public LinkedListNode<T> previous;

    /**
     * 下一个节点
     */
    public LinkedListNode<T> next;

    public LinkedListNode(T value, LinkedListNode<T> next, LinkedListNode<T> previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("value:");
        if (value == null) {
            sb.append("null");
        } else {
            sb.append(value.toString());
        }
        sb.append(" ,previous:");
        if (previous != null) {
            if (previous.value == null) {
                sb.append("null");
            } else {
                sb.append(previous.value.toString());
            }
        } else {
            sb.append("null");
        }
        sb.append(" ,next:");
        if (next != null) {
            if (next.value == null) {
                sb.append("null");
            } else {
                sb.append(next.value.toString());
            }
        } else {
            sb.append("null");
        }
        return sb.toString();
    }

}