

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Teddy on 2017-03-14.
 */
public class QuickSort extends RecursiveAction{
    private int start, end;
    private float[] arr;

    private QuickSort(float[] arr)
    {
        this(arr, 0,arr.length-1);
    }

    private QuickSort( float[] arr, int start, int end) {
        this.start = start;
        this.end = end;
        this.arr = arr;
    }

    private int partition(float[] arr, int left, int right)
    {

        int i = left - 1;
        float pivot = arr[right];
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                float tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        i++;
        float tmp = arr[i];
        arr[i] = arr[right];
        arr[right] = tmp;

        return i;
    }

    @Override
    protected void compute() {
        if ((end-start) < THRESHOLD)
        {
            Arrays.sort(arr, start, end + 1);
        }
        else {
            int pivotIndex = partition(arr, start, end);
            QuickSort t1 = new QuickSort(arr, start, pivotIndex - 1);
            QuickSort t2 = new QuickSort(arr, pivotIndex + 1, end);
            t1.fork();
            t2.compute();
            t1.join();
        }
    }

    public boolean isSorted()
    {
        for (int i=0;i<arr.length-1;i++)
        {
            if(arr[i] > arr[i+1])
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        //init:
        float[] arr = new float[SIZE];
        Random r = new Random();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //form random number in float array:
        for(int i=0;i<arr.length;i++) {arr[i] = r.nextInt(arr.length)+1;}
        System.out.println("random done.");

        //start sorting:
        long timeStart = System.currentTimeMillis();
        QuickSort q = new QuickSort(arr);
        forkJoinPool.invoke(q);
        long timeEnd = System.currentTimeMillis();
        System.out.println("done at "+ (timeEnd-timeStart)+"ms.");
        forkJoinPool.shutdown();

        //check if sorted correctly:
        System.out.println("is sorted: " + q.isSorted());
    }

    public static int SIZE =      100000000; //10^8
    public static int THRESHOLD = 1000;
} 