package LinkedList;

public class ReverseBetweenRecursion {



    public static void main(String[] args) {

        ListNode head = createLinkedList(10);
        System.out.println("翻转前：");
        print(head);
        System.out.println("翻转后：");
        ListNode reverseHead = reverseBetween(head, 2, 4);
        print(reverseHead);

    }

    /*翻转指定范围内的节点*/
    public static ListNode reverseBetween(ListNode head, int from, int end){
        /*基准条件
        这里有点特殊，此处的基准条件又是一个递归，但这个递归也有基准条件，所以不会死循环
        如果从索引1开始翻转，相当于翻转链表的前n个节点，直接调用之前的函数即可
        */
        if (from == 1){
            return ReverseNRecursion.reverseN(head,end);
        }else {

            /*翻转指定范围内节点，可以理解为把开始索引充当头结点，翻转剩下的end-1个节点
            * head不断的前进到翻转的起点，head往前走一步，起点的索引位置相对于head就会减一
            * head前进到起点位置时，触发基准条件
            * */
            head.setNext(reverseBetween(head.getNext(),from - 1, end - 1));
            return head;
        }
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
