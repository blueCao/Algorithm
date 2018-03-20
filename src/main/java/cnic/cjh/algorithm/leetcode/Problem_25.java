package cnic.cjh.algorithm.leetcode;

/**
 * reverse link list with group k
 * using recursive solutioni in https://leetcode.com/problems/reverse-nodes-in-k-group/discuss/11423/Short-but-recursive-Java-code-with-comments
 *
 *
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 */
public class Problem_25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if ( head == null){
            return head;
        }
        ListNode cur = head;
        int count = 0;
        /**
         * 找到 k 个元素
         */
        while( count < k && cur != null ){
            cur = cur.next;
            count++;
        }
        if( count == k){
            /**
             * 对剩余的元素递归求逆序
             */
            ListNode later_list = reverseKGroup(cur, k);
            /**
             * 对头部的 k 个元素逆过来
             */
            ListNode pre = head, tmp = null, node = head.next;
            while(node != cur){
                tmp = node;
                node = node.next;
                tmp.next = pre;
                pre = tmp;
            }
            /**
             * 拼接头部k个逆序和后面的元素
             */
            head.next = later_list;
            head = pre;
        }
        return head;
    }
    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        l1.next.next.next.next = new ListNode(5);
        l1.next.next.next.next.next = new ListNode(6);
        l1.next.next.next.next.next.next = new ListNode(7);

        ListNode result = new Problem_25().reverseKGroup(l1,3);
        System.out.println();
    }
}
