import java.util.Random;

/**
 * Created by Teddy on 2017-03-13.
 */
public class main {

    public static void main(String[] args)
    {
        QuickSort quickSort = new QuickSort(10,10);
        //quickSort.print();
        float[] arr = quickSort.sort();
        /*for(int i=0; i<arr.length-1;i++)
        {
            if(arr[i] > arr[i+1])
            {
                System.out.println(arr[i]);
                System.out.println("sort fail");
            }
        }
        System.out.println("sort done.");*/
    }


}



