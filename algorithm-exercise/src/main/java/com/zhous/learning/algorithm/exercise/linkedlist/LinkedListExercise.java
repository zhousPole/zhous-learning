package com.zhous.learning.algorithm.exercise.linkedlist;

import com.zhous.learning.algorithm.structure.linkedlist.LinkedListNode;
import com.zhous.learning.algorithm.utils.LinkedListUtils;
import com.zhous.learning.algorithm.utils.LogUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 链表算法练习
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/4/12
 */
public class LinkedListExercise {

    /**
     * 单链表反转<br/>
     * 算法详解：<b>http://c.biancheng.net/view/8105.html</b>
     *
     * @param array         数据
     * @param algorithmType 算法类型:<br/>
     *                      1 -> 就地逆置法反转链表<br/>
     *                      2 -> 迭代反转链表<br/>
     *                      3 -> 递归反转链表<br/>
     *                      4 -> 头插法反转链表
     */
    public static void singleLinkedListReverse(String[] array, int algorithmType) {
        LinkedListNode<String> head = LinkedListUtils.createSingleLinkedList(array);
        if (head != null) {
            LogUtils.out("before reverse:\n%s", LinkedListUtils.printToString(head));
            switch (algorithmType) {
                case 1:
                    head = singleLinkedListReverse1(head);
                    break;
                case 2:
                    head = singleLinkedListReverse2(head);
                    break;
                case 3:
                    head = singleLinkedListReverse3(head);
                    break;
                case 4:
                    head = singleLinkedListReverse4(head);
                    break;
                default:
                    break;
            }
            LogUtils.out("after reverse:\n%s", LinkedListUtils.printToString(head));
        }
    }

    /**
     * 检测链表中是否有环
     *
     * @param head
     * @param algorithmType 算法类型:<br/>
     *                      1 -> 快慢指针法<br/>
     *                      2 -> 足迹法
     */
    public static void checkRing(LinkedListNode<String> head, int algorithmType) {
        boolean result = false;
        if (head != null) {
            switch (algorithmType) {
                case 1:
                    result = checkRing1(head);
                    break;
                case 2:
                    result = checkRing2(head);
                    break;
                default:
                    break;
            }
        }
        LogUtils.out(result + "");
    }

    /**
     * 合并两个有序链表
     *
     * @param first
     * @param second
     * @return
     */
    public static LinkedListNode<Integer> mergeLinkedLists(LinkedListNode<Integer> first,
                                                           LinkedListNode<Integer> second) {
        LinkedListNode<Integer> firstNode = first;
        LinkedListNode<Integer> secondNode = second;
        LinkedListNode<Integer> newHead = new LinkedListNode<>(0, null, null);
        LinkedListNode<Integer> newTail = newHead;
        while (firstNode != null && secondNode != null) {
            LinkedListNode<Integer> current;
            if (firstNode.value > secondNode.value) {
                current = secondNode;
                secondNode = secondNode.next;
            } else {
                current = firstNode;
                firstNode = firstNode.next;
            }
            newTail.next = current;
            newTail = newTail.next;
        }
        if (firstNode != null) {
            newTail.next = firstNode;
        }
        if (secondNode != null) {
            newTail.next = secondNode;
        }
        return newHead.next;
    }

    /**
     * 删除链表倒数第n个节点
     *
     * @param head
     * @param n
     */
    public static LinkedListNode<String> deleteLinkedListNode(LinkedListNode<String> head, int n) {
        LinkedListNode<String> before = head;
        LinkedListNode<String> after = head;
        while (n > 0) {
            if (before == null) {
                return head;
            }
            before = before.next;
            n--;
        }
        if (before == null) {
            return after.next;
        }
        while (before != null) {
            if (before.next == null) {
                after.next = after.next.next;
                break;
            }
            after = after.next;
            before = before.next;
        }
        return head;
    }

    /**
     * 求链表的中间节点
     *
     * @param head
     * @return
     */
    public static LinkedListNode<String> middleNode(LinkedListNode<String> head) {
        LinkedListNode<String> fast = head;
        LinkedListNode<String> slow = head;
        LinkedListNode<String> middle = null;
        if (head != null) {
            while (true) {
                if (fast.next != null) {
                    fast = fast.next.next;
                    slow = slow.next;
                    if (fast == null) {
                        middle = slow;
                        break;
                    }
                } else {
                    middle = slow;
                    break;
                }
            }
        }
        return middle;
    }

    /**
     * 就地逆置法反转链表
     *
     * @param head
     * @return
     */
    private static LinkedListNode<String> singleLinkedListReverse1(LinkedListNode<String> head) {
        LinkedListNode<String> begin = head;
        if (begin != null) {
            LinkedListNode<String> end = begin.next;
            while (end != null) {
                begin.next = end.next;
                end.next = head;
                head = end;
                end = begin.next;
            }
        }
        return head;
    }

    /**
     * 迭代反转链表
     *
     * @param head
     * @return
     */
    private static LinkedListNode<String> singleLinkedListReverse2(LinkedListNode<String> head) {
        LinkedListNode<String> begin = head;
        if (begin == null) {
            return null;
        }
        LinkedListNode<String> mid = begin.next;
        if (mid == null) {
            return head;
        }
        LinkedListNode<String> end = mid.next;
        if (end == null) {
            mid.next = begin;
            return mid;
        }
        head.next = null;
        while (mid != null) {
            mid.next = begin;
            begin = mid;
            mid = end;
            if (end != null) {
                end = end.next;
            }
        }
        head = begin;
        return head;
    }

    /**
     * 递归反转链表
     *
     * @param head
     * @return
     */
    private static LinkedListNode<String> singleLinkedListReverse3(LinkedListNode<String> head) {
        if (head == null || head.next == null) {
            return head;
        }
        LinkedListNode<String> newHead = singleLinkedListReverse3(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 头插法反转链表
     *
     * @param head
     * @return
     */
    private static LinkedListNode<String> singleLinkedListReverse4(LinkedListNode<String> head) {
        LinkedListNode<String> newHead = null;
        LinkedListNode<String> tmp;
        while (head != null) {
            tmp = head;
            head = head.next;
            tmp.next = newHead;
            newHead = tmp;
        }
        return newHead;
    }

    /**
     * 快慢指针法检测是否链表有环
     *
     * @param head
     */
    private static boolean checkRing1(LinkedListNode<String> head) {
        LinkedListNode<String> fast = head;
        LinkedListNode<String> slow = head;
        if (head != null) {
            while (true) {
                if (fast.next != null) {
                    fast = fast.next.next;
                } else {
                    fast = null;
                }
                slow = slow.next;
                if (fast == null || slow == null) {
                    break;
                }
                if (slow == fast) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 足迹法检测是否链表有环
     *
     * @param head
     */
    private static boolean checkRing2(LinkedListNode<String> head) {
        Set<LinkedListNode<String>> footprintSet = new HashSet<>();
        while (head != null) {
            if (footprintSet.contains(head)) {
                LogUtils.out("ring start -> %s", head);
                return true;
            } else {
                footprintSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

}