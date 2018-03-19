package cnic.cjh.algorithm.leetcode;

/**
 * swap nodes in pairs
 *
 * https://leetcode.com/problems/swap-nodes-in-pairs/description/
 */
public class Problem_24 {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode head_1=head,
                head_2 = head.next,
                node_1 = head_1,
                node_2 = head_2,
                node = head_2;
        /**
         *  1. 将单链表拆成俩条
         */
        while(true){
            node = node.next;
            if(node != null){
                node_1.next = node;
                node_1 = node;
            }else {
                break;
            }
            node = node.next;
            if(node != null){
                node_2.next = node;
                node_2 = node;
            }else {
                break;
            }
        }
        /**
         * 2. 末尾置空
         */
        node_1.next = null;
        node_2.next = null;
        /**
         * 3. 将俩个链表合成1个
         */
        ListNode result_head = head_2;
        node = head_1;
        node_1 = head_1.next;
        node_2 = head_2.next;
        while(true){
            if(node_2 != null){
                node.next = node_2;
                node = node_2;
                node_2 = node_2.next;
            }else {
                break;
            }
            if(node_1 != null){
                node.next = node_1;
                node = node_1;
                node_1 = node_1.next;
            }else {
                break;
            }
        }
        head_2.next = head_1;

        /**
         * 4. 将链表1剩余的一个拼上
         */
        if(node_1 != null){
            node.next = node_1;
            node = node_1;
        }
        /**
         * 5. 尾节点置为空
         */
        node.next = null;
        return result_head;
    }
    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = new ListNode(6);

        new Problem_24().swapPairs(l1);
    }

}
