package heap;

import java.util.Comparator;

/**
 * 使用二叉堆实现优先级队列
 */
public class MaxPriorityQueue {
    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(10);
        maxPQ.insert(1);
        maxPQ.insert(3);
        maxPQ.insert(8);
        maxPQ.insert(4);
        maxPQ.insert(2);

        System.out.println(maxPQ.max());
        System.out.println(maxPQ.delMax());
    }

    static class MaxPQ<T extends Comparable<T>> {
        /*存储元素的数组*/
        private T[] pq = null;

        /*当前优先级队列中元素的个数*/
        private int N = 0;

        /*初始化队列，因为索引 0 不使用，索引容量+1，多分配一个空间*/
        public MaxPQ(int capacity) {
            pq = (T[]) new Comparable[capacity + 1];
        }

        /*返回当前队列中最大的元素，大顶堆，根节点就是最大的元素*/
        public T max() {
            return pq[1];
        }

        /*插入元素 e
        * 插入是先插到最后，然后上浮到正确的位置
        * */
        public void insert(T e) {
            //元素个数先加1，并把新元素插入到最后
            N++;
            pq[N] = e;
            //插入的元素可能破坏了大顶堆的性质，所以需要不断上浮，到合适的位置
            swim(N);
        }

        /*删除并返回当前队列中最大元素
        * 删除的是堆顶元素，先调换位置，把堆顶元素与堆底最后一个元素进行交换，这样删除最后一个元素不会影响其他节点，然后不断下沉交换到堆顶的那个元素
        * */
        public T delMax() {
            //堆顶是最大元素
            T max = pq[1];
            //先交换
            exchange(1,N);
            //元素个数减1
            pq[N] = null;
            N--;
            //不断下沉被交换到堆顶的那个元素
            sink(1);

            return max;
        }

        /*上浮第 k 个元素，以维护大顶堆性质*/
        private void swim(int k) {
            /*如果第 k 个元素的父节点比第 k 个元素小，那么就让第 k 个元素上浮，如果上浮的堆顶，就不应该再上浮了*/
            while (k > 1 && small(parent(k), k)) {
                exchange(parent(k), k);
                k = parent(k);
            }
        }

        /*下沉第 k 个元素，以维护大顶堆性质*/
        private void sink(int k) {
            /*如果下沉到堆底了，就不用继续调整下沉了*/
            while (leftChild(k) <= N){
                //先假设当前第k个节点的左孩子相对较小
                int bigger = leftChild(k);
                //如果存在右孩子，判断左右孩子谁更大，找出更大的那个孩子准备交换
                if (rightChild(k) <= N && small(bigger,rightChild(k))){
                    bigger = rightChild(k);
                }
                //如果左右孩子中较大的那个，都比第k个节点小，那么第k个节点满足大顶堆性质，直接break，不需要再继续交换了
                if (small(bigger,k))
                    break;
                //否则左右两个孩子中有一个比第k个节点更大，交换这两个元素
                exchange(bigger,k);
                //k向下走了一层，不断比较下沉
                k = bigger;
            }
        }

        /*交换两个元素*/
        private void exchange(int i, int j) {
            T temp = pq[i];
            pq[i] = pq[j];
            pq[j] = temp;
        }

        /*判断pq[i]是否比pq[j]小*/
        private boolean small(int i, int j) {
            return pq[i].compareTo(pq[j]) < 0;
        }

        /*返回父节点的索引*/
        public int parent(int root) {
            return root / 2;
        }

        /*返回左孩子节点的索引*/
        public int leftChild(int root) {
            return root * 2;
        }

        /*返回右孩子节点的索引*/
        public int rightChild(int root) {
            return root * 2 + 1;
        }

    }
}
