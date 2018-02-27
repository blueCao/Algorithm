package cnic.cjh.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * k个有序链表归并排序
 *
 *https://leetcode.com/problems/merge-k-sorted-lists/description/
 */
public class Problem_23 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0),c = head;
        for(;l1 != null && l2 != null;){
            if(l1.val > l2.val){
                c.next = l2;
                l2 = l2.next;
            }else{
                c.next = l1;
                l1 = l1.next;
            }
            c = c.next;
        }
        if(l1 != null){
            c.next = l1;
        }else if(l2 != null){
            c.next = l2;
        }
        return head.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        List<LengthNode> set = new ArrayList<LengthNode>(lists.length);
        /**
         * 初始化长度
         */
        for(int i = 0; i < lists.length; i++) {
            LengthNode node = new LengthNode();
            node.node = lists[i];
            int length = 0;
            ListNode n = node.node;
            while(n !=null){
                length++;
                n=n.next;
            }
            node.length=length;
            set.add(node);
        }
        /**
         * 俩俩归并排序
         */
        while(set.size() > 1){
            int min_length_1 = set.get(0).length,min_length_2 = set.get(1).length,min_1_index = 0,min_2_index=1;
            /**
             * 1.找到最短的俩个序列
             */
            if(min_length_1 > min_length_2){
                int tmp = min_length_1;
                min_length_1 = min_length_2;
                min_length_2 = tmp;

                min_1_index = 1;
                min_2_index = 0;
            }
            for(int i=2; i < set.size(); i++){
                LengthNode node = set.get(i);
                if(node.length < min_length_2){
                    if(node.length < min_length_1){
                        min_length_2 = min_length_1;
                        min_2_index = min_1_index;
                        min_length_1 = node.length;
                        min_1_index = i;
                    }
                    else {
                        min_length_2 = node.length;
                        min_2_index = i;
                    }
                }
            }
            /**
             * 2.合并这俩最短的序列
             */
            ListNode mergered = mergeTwoLists(set.get(min_1_index).node,set.get(min_2_index).node);
            LengthNode node = new LengthNode();
            node.node = mergered;
            int length = 0;
            ListNode n = node.node;
            while(n !=null){
                length++;
                n=n.next;
            }
            node.length=length;
            /*
            * 3.将归并的链表加入其中，并删除归并前的2个链表
             */
            set.add(node);
            if(min_2_index > min_1_index){
                set.remove(min_2_index);
                set.remove(min_1_index);
            }else{
                set.remove(min_1_index);
                set.remove(min_2_index);
            }
        }
        return set.get(0).node;
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
        new Problem_23().mergeKLists(list);
    }

}
