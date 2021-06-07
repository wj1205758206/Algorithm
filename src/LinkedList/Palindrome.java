package LinkedList;

/**
 * 判断一个链表是否是回文链表
 */
public class Palindrome {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        Utils.addTail(head,2);
        Utils.addTail(head,2);
        Utils.addTail(head,1);
/*        Utils.addTail(head,3);
        Utils.addTail(head,3);
        Utils.addTail(head,2);
        Utils.addTail(head,1);*/
        Utils.print(head);

/*        *//*方法一：翻转链表进行遍历比较*//*
        System.out.println(isPalindrome(head));

        *//*方法二：递归二叉树后序遍历*//*
        System.out.println(isPalindrome2(head));*/

        /*方法三：快慢双指针，找链表中点*/
        System.out.println(isPalindrome3(head));
        Utils.print(head);

    }

    /**
     * 方法一：复制一个新的链表，将新链表翻转，然后与原来的链表遍历比较
     * @param head  原链表头节点
     * @return      是回文链表则返回true
     */
    public static boolean isPalindrome(ListNode head) {
        /*尾插法复制一个新的链表*/
        ListNode newHead = new ListNode(head.val);
        ListNode temp = head.next;
        while (temp != null) {
            Utils.addTail(newHead,temp.val);
            temp = temp.next;
        }

        /*递归翻转复制的链表*/
        ListNode reverse = LinkedListReverseRecursion.reverse(newHead);

        /*比较原来链表的每一个节点是否与翻转后的每一个节点都相等，只要有一个不相等就返回false*/
        ListNode t1 = head;
        ListNode t2 = reverse;
        while (t1 != null) {
            if (t1.val != t2.val)
                return false;
            t1 = t1.next;
            t2 = t2.next;
        }
        return true;
    }


    static ListNode left = null;

    /**
     * 方法二：二叉树后序遍历，递归方式
     * @param head  原链表头节点
     * @return      返回是否是回文链表
     */
    public static boolean isPalindrome2(ListNode head) {
        /*模拟双指针形式，head节点相当于左指针，从链表的头部开始挨个遍历*/
        left = head;

        /*后序遍历链表*/
        return postOrderTraversal(head);
    }


    private static boolean postOrderTraversal(ListNode right) {
        /*基准条件
        * 最后一个节点则返回true*/
        if (right == null)
            return true;

        /*递归实现后序遍历，不断的next递归定位到最后一个节点，右指针从尾节点开始，递归不断返回上一个节点，实现从右到左的遍历*/
        boolean result = postOrderTraversal(right.next);

        /*判断当前节点result是否为true，并且当前节点的，也就是右指针指向的节点的值是否等于左指针指向的节点的值*/
        result = result && (right.val == left.val);

        /*左指针右移，准备比较下一个节点*/
        left = left.next;

        /*返回当前result，就也是返回上一层的递归调用，相当于右指针左移，准备下一次的比较*/
        return result;
    }

    /**
     * 方法三：快慢双指针，找到链表中点，优化空间复杂度为 O(1)
     * @param head  原链表头节点
     * @return      返回是否是回文链表
     */
    public static boolean isPalindrome3(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        /*慢指针步长为1，快指针步长为2
        * while循环结束，
        * 如果链表节点为奇数，slow会指向中点，fast指向尾节点
        * 如果链表节点为偶数，slow会指向中点偏右的节点，fast则指向尾节点的下一个，即null*/
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /*如果fast不为null，也就是链表的节点个数为奇数，则slow再向前走一步
        * 这样链表尾中点为分割点，分成两部分，head指向了左部分链表的头节点，slow指向了右部分链表的头节点*/
        if (fast != null) {
            slow = slow.next;
        }

        /*左指针指向head，即左部分链表的头节点
        * 右指针指向右部分翻转后的头节点
        * 如果是回文链表，那么除去中点以外，左部分链表和右部分翻转后的链表应该完全相同*/
        ListNode left = head;
        ListNode right = LinkedListReverseRecursion.reverse(slow);
        Utils.print(right);

        /*因为在原来链表的直接操作，进行翻转，会破坏链表结构，需要用p，q来恢复链表原来的结构*/
        ListNode p = null;
        ListNode q = right;

        /*把左部分链表与右部分翻转后的链表进行一一比较*/
        while (right != null) {

            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;

            /*如果right.next == null，就说明left，right都走到了各自那部分链表的最后一个节点
            * 此时用p记录做左部分最后一个节点，并把翻转过的右部分再进行翻转，相当于右部分恢复成原来的顺序
            * 然后把左部分最后一个节点续接上右部分的头节点，这样就恢复了链表原来的结构*/
            if (left.next == slow) {
                p = left;

            }
        }

        p.next = LinkedListReverseRecursion.reverse(q);

        return true;
    }
}
