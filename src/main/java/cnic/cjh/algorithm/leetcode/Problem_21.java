package cnic.cjh.algorithm.leetcode;

import java.util.TreeSet;

/**
 * leetcode 21  合并俩有序链表
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 */
public class Problem_21 {
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
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}