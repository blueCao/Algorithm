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
            ListNode n = node.node.next;
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
        while(lists.length > 1){
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
            for(int i=3; i < set.size(); i++){
                LengthNode node = set.get(i);
                if(node.length < min_2_index){
                    if(node.length < min_1_index){
                        min_length_2 = min_length_1;
                        min_2_index = min_1_index;

                        min_length_1 =
                    }
                }

            }
        }
    }

    class LengthNode{
        int length=0;
        ListNode node;
    }

}
