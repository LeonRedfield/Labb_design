

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

    public static void warmUp()
    {
        System.out.println("warming up...");
        float[] arr = generateList();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //start sorting:
        QuickSort q = new QuickSort(arr);
        long timeStart = System.currentTimeMillis();
        forkJoinPool.invoke(q);
        long timeEnd = System.currentTimeMillis();
        forkJoinPool.shutdown();
        System.out.println("warming, done at "+ (timeEnd-timeStart)+"ms.");
    }

    private static void garbage() {
        System.out.println("Garbage collecting...");
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Garbage collecting done.");
    }

    private static float[] generateList(){
        float[] list = new float[SIZE];
        for(int i = 0; i < list.length; i++) {
            list[i] = new Random().nextInt(RANDOM_SIZE);
        }
        //System.out.println("random done.");
        return list;
    }

    public static void main(String[] args) throws Exception{
        //warming up:
        warmUp();
        //start sorting:
        int count = MEASUREMENT_AMOUNT;

        for(int i = 0; i < count; i++){
            System.out.println("generating...");
            garbage();
            ForkJoinPool forkJoinPool = new ForkJoinPool(2);
            QuickSort q = new QuickSort(generateList());
            System.out.println("time started.");
            long timeStart = System.currentTimeMillis();
            forkJoinPool.invoke(q);
            long timeEnd = System.currentTimeMillis();
            System.out.println("measurement["+ i+"] done at "+ (timeEnd-timeStart)+"ms.");

            forkJoinPool.shutdown();

            //check if sorted correctly:
            //System.out.println("is sorted: " + q.isSorted());
        }
    }

    public static int SIZE =      100000000; //10^8
    public static int THRESHOLD = 10000;
    public static int RANDOM_SIZE = 100000;
    public static int MEASUREMENT_AMOUNT = 3;
}