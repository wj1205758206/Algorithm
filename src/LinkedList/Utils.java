package LinkedList;

public class Utils {

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


    /*尾插法*/
    public static ListNode addTail(ListNode head, int val) {
        ListNode node = new ListNode(val);
        ListNode cur = head;

        while (cur.next != null)
            cur = cur.next;
        cur.next = node;
        return head;
    }
}
