package cnic.cjh.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  利用优先队列，对k个有序链表归并排序
 *
 *https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class Problem_23_priority_queue {

    public ListNode mergeKLists(ListNode[] lists) {
        /**
         * 空
         */
        if(lists.length < 1){
            return null;
        }
        ListNode result_head = new ListNode(0),result_last = result_head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        /**
         * 1. 各自取k个链表的不为空的节点加入优先队列中
         */
        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null){
                queue.add(lists[i]);
                lists[i] = lists[i].next;
            }
        }
        /**
         * 2. 一边删除、一边新增优先的节点，直到优先队列为空
         */
        while(queue.isEmpty() == false){
            /**
             * 2.1 取得优先队列的第一个节点（当前k个值中的最小值）
             */
            ListNode node = queue.poll();
            /**
             * 2.2 加入结果队列中
             */
            result_last.next = node;
            result_last = result_last.next;
            /**
             * 2.3 将node链表的下一个元素加入优先队列中
             */
            if( node.next != null){
                queue.add(node.next);
            }
        }
        return result_head.next;
    }

    class LengthNode{
        int length=0;
        ListNode node;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(2);
        ListNode l2 = null;
        ListNode l3 = new ListNode(-1);
        ListNode[] list = new ListNode[3];
        list[0] = l1;
        list[1] = l2;
        list[2] = l3;
        new Problem_23_priority_queue().mergeKLists(list);
    }

}
