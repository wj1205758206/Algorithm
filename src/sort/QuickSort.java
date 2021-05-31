package sort;


public class QuickSort {

    public static void main(String[] args) {
        int[] array = {1,4,2,8,5,6,9};
        quickSort(array,0,array.length-1);
        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }

    public static void quickSort(int[] array, int left, int right){

        int i,j,index;

        //基准条件
        if (left > right){
            return;
        }

        //递归条件
        i = left;
        j = right;
        index = array[left];

        while (i < j){
            while (i < j && array[j] >= index)
                j--;
            while (i < j && array[i] <= index)
                i++;
            if (i < j){
                int temp;
                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        array[left] = array[i];
        array[i] = index;

        quickSort(array, left, i - 1);
        quickSort(array,i + 1, right);
    }
}
