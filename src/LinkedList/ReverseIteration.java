package LinkedList;

public class ReverseIteration {
    public static void main(String[] args) {
        ListNode head = createLinkedList(10);

        System.out.println("翻转前：");
        print(head);

        System.out.println("迭代翻转全部：");
        ListNode reverseALLHead = reverseALLIteration(head);
        print(reverseALLHead);

        System.out.println("迭代翻转前N个节点：");
        ListNode reverseNHead = reverseNIteration(reverseALLHead, 3);
        print(reverseNHead);

        System.out.println("迭代翻转指定范围的节点：");
        ListNode reverseBetweenHead = reverseBetweenIteration(reverseNHead, 2, 5);
        print(reverseBetweenHead);

    }

    /*迭代翻转全部节点*/
    public static ListNode reverseALLIteration(ListNode head){
        /*三个节点分别指向前驱结点、当前节点、后驱节点*/
        ListNode pre, cur, post;
        pre = null;
        cur = post = head;

        while (cur != null) {
            /*后驱节点*/
            post = cur.next;
            /*翻转节点*/
            cur.next = pre;

            /*整体逐个后移*/
            pre = cur;
            cur = post;
        }
        /*此时前驱结点就是翻转后的头结点*/
        return pre;
    }

    /*迭代翻转前N个节点*/
    public static ListNode reverseNIteration(ListNode head, int n) {
        /*三个节点分别指向前驱结点、当前节点、后驱节点*/
        ListNode pre, cur, post;
        pre = null;
        cur = post = head;

        while (n > 0 && cur != null) {
            /*后驱节点*/
            post = cur.next;
            /*翻转节点*/
            cur.next = pre;

            /*整体逐个后移*/
            pre = cur;
            cur = post;

            n--;
        }
        /*翻转前N个之后，还需要把head节点连接上剩余的链表*/
        head.next = cur;

        /*此时前驱结点就是翻转后的头结点*/
        return pre;
    }

    public static ListNode reverseBetweenIteration(ListNode head, int left, int right) {
        if (left == 1) {
            return reverseNIteration(head,right);
        }

        /*三个节点分别指向前驱结点、当前节点、后驱节点*/
        ListNode pre, cur, post;
        pre = null;
        cur = post = head;

        /*定位到left位置，即找到翻转的起点*/
        for (int i = 1; i < left; i++) {
            if (cur.next != null) {
                pre = cur;
                cur = cur.next;
                post = cur.next;
            }
        }

        /*如果翻转中间一部分，整个链表会断成三段，需要记录起始点断开的位置，为后续重连做准备*/
        ListNode fromNode = pre;

        /*翻转指定范围的节点*/
        while (left <= right && cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
            left++;
        }

        /*此时起始点节点成为翻转后那部分的尾节点，续接上剩余的链表*/
        fromNode.next.next = cur;

        /*翻转后成为头结点的pre，需要重连上断开链接的之前那部分*/
        fromNode.next = pre;

        return head;
    }

    /*创建num个节点的链表*/
    public static ListNode createLinkedList(int num){
        ListNode head = null;
        for (int i = 1; i <= num; i++){
            ListNode node = new ListNode(i);
            if (head == null){
                head = node;
            }else {
                ListNode t = head;
                while (t.getNext() != null){
                    t = t.getNext();
                }
                t.setNext(node);
            }
        }
        return head;
    }

    /*打印所有的节点*/
    public static void print(ListNode head){
        ListNode temp = head;
        while (temp != null){
            if (temp.getNext() == null)
                System.out.println(temp.getVal());
            else
                System.out.print(temp.getVal() + " --> ");
            temp = temp.getNext();
        }
    }
}
