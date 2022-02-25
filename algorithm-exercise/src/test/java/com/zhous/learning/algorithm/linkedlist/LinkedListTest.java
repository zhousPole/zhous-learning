package com.zhous.learning.algorithm.linkedlist;

import com.zhous.learning.algorithm.exercise.linkedlist.LinkedListExercise;
import com.zhous.learning.algorithm.structure.linkedlist.LinkedListNode;
import com.zhous.learning.algorithm.utils.LinkedListUtils;
import com.zhous.learning.algorithm.utils.LogUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/4/8
 */
public class LinkedListTest {

    private String[] dataArray;

    @Before
    public void init() {
        dataArray = new String[]{"1", "3", "4", "6", "-1", "2"};
    }

    /**
     * 单链表反转
     */
    @Test
    public void testSingleLinkedListReverse() {
        LogUtils.out("***就地逆置法反转链表****");
        LinkedListExercise.singleLinkedListReverse(dataArray, 1);
        LogUtils.out("***迭代反转链表****");
        LinkedListExercise.singleLinkedListReverse(dataArray, 2);
        LogUtils.out("***递归反转链表****");
        LinkedListExercise.singleLinkedListReverse(dataArray, 3);
        LogUtils.out("***头插法反转链表****");
        LinkedListExercise.singleLinkedListReverse(dataArray, 4);
    }

    /**
     * 检测链表是否有环
     */
    @Test
    public void testCheckRing() {
        LinkedListNode<String> head = LinkedListUtils.createSingleCycleLinkedList(dataArray);
        LogUtils.out("***快慢指针法检测链表是否有环****");
        LinkedListExercise.checkRing(head, 1);
        LogUtils.out("***足迹法检测链表是否有环****");
        LinkedListExercise.checkRing(head, 2);
    }

    /**
     * 合并两个有序链表
     */
    @Test
    public void testMergeLinkedLists() {
        Integer[] firstArray = new Integer[]{1, 2, 4};
        Integer[] secondArray = new Integer[]{-1, 3};
        LinkedListNode<Integer> first = LinkedListUtils.createSingleLinkedList(firstArray);
        LinkedListNode<Integer> second = LinkedListUtils.createSingleLinkedList(secondArray);
        LogUtils.out("***合并两个有序链表***");
        LogUtils.out("first list:%s", LinkedListUtils.printToString(first));
        LogUtils.out("second list:%s", LinkedListUtils.printToString(second));
        LinkedListNode<Integer> merge = LinkedListExercise.mergeLinkedLists(first, second);
        LogUtils.out("merge list:%s", LinkedListUtils.printToString(merge));
    }

    /**
     * 删除链表倒数第n个节点
     */
    @Test
    public void testDeleteLinkedListNode() {
        int n = 6;
        LogUtils.out("***删除链表倒数第%d个节点***", n);
        LinkedListNode<String> head = LinkedListUtils.createSingleLinkedList(dataArray);
        LogUtils.out("before delete:%s", LinkedListUtils.printToString(head));
        head = LinkedListExercise.deleteLinkedListNode(head, n);
        LogUtils.out("after delete:%s", LinkedListUtils.printToString(head));
    }

    /**
     * 求链表的中间节点
     */
    @Test
    public void testMiddleNode() {
        LogUtils.out("***求链表的中间节点***");
        String[] array1 = new String[]{"1", "3", "4", "6", "-1", "2"};
        LinkedListNode<String> head1 = LinkedListUtils.createSingleLinkedList(array1);
        LogUtils.out(LinkedListUtils.printToString(head1));
        LogUtils.out("middle node : %s", LinkedListExercise.middleNode(head1));
        String[] array2 = new String[]{"1", "3", "4", "6", "-1"};
        LinkedListNode<String> head2 = LinkedListUtils.createSingleLinkedList(array2);
        LogUtils.out(LinkedListUtils.printToString(head2));
        LogUtils.out("middle node : %s", LinkedListExercise.middleNode(head2));
        LinkedListNode<String> head3 = LinkedListUtils.createSingleLinkedList(new String[]{});
        LogUtils.out(LinkedListUtils.printToString(head3));
        LogUtils.out("middle node : %s", LinkedListExercise.middleNode(head3));
    }

}