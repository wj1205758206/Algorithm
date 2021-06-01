package LinkedList;

public class ReverseNRecursion {

    /*定义一个后驱节点，用来翻转部分节点后，再续接上剩余没有翻转的节点*/
    private static ListNode postDriver = null;

    public static void main(String[] args) {

        /*创建一个单链表  1-10*/
        ListNode head = createLinkedList(10);
        System.out.println("翻转前: ");

        print(head);

        System.out.println("翻转后: ");

        /*递归翻转前n个节点，翻转后第n个节点成为头结点*/
        ListNode reverseHead = reverseN(head, 3);

        print(reverseHead);

    }

    public static ListNode reverseN(ListNode head, int n){

        /*基准条件
        * 当递归到只剩一个节点时，返回这个节点，并且还要记录这个节点的下一个节点，充当后驱节点*/
        if (n == 1){
            postDriver = head.getNext();
            return head;
        }

        /*每次递归翻转以当前节点的下一个节点充当头结点的链表，所以只翻转n-1个*/
        ListNode last = reverseN(head.getNext(), n - 1);
        /*将链表箭头翻转*/
        head.getNext().setNext(head);

        /*翻转结束后，将当前节点的next指向后驱节点，相当于续接上剩余没有翻转的节点*/
        head.setNext(postDriver);
        return last;

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
