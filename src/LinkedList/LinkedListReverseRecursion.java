package LinkedList;

public class LinkedListReverseRecursion {

    public static void main(String[] args) {
        ListNode head = null;
        /*创建一个单链表  1-10*/
        for (int i = 1; i < 11; i++){
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
        /*打印反转前的链表*/
        System.out.println("翻转前：");
        print(head);
        System.out.println("\n翻转后：");
        ListNode reverseHead = reverse(head);
        print(reverseHead);

    }

    public static ListNode reverse(ListNode head){
        /*基准条件
        如果链表只有一个节点的时候反转也是它自己，直接返回即可*/
        if (head.getNext() == null)
            return head;
        /*递归翻转以下一个节点为头结点的链表，知道翻转最后一个节点，就直接返回，last指向原来链表的尾节点，充当翻转后的头结点*/
        ListNode last = reverse(head.getNext());
        /*相当于把链表的箭头翻转，将下一个的节点的next指针指向自己*/
        head.getNext().setNext(head);
        /*把每一趟翻转后的，原来的头结点next置为null当尾节点*/
        head.setNext(null);
        return last;
    }

    /*打印所有的节点*/
    public static void print(ListNode head){
        ListNode temp = head;
        while (temp != null){
            if (temp.getNext() == null)
                System.out.print(temp.getVal());
            else
                System.out.print(temp.getVal() + " --> ");
            temp = temp.getNext();
        }
    }
}
