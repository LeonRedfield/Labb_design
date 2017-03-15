

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

    public void innerSort(float[] array, int start, int end)
    {
        for(int i= start +1 ;i<end;i++)
        {
            float value = array[i];
            int j= i-1;
            while(j>=0 && value < array[j])
            {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = value;
        }
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
            innerSort(arr, start, end+1);//insertionSort
            //Arrays.sort(arr, start, end + 1);
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











    /*private float[] quickSort(float arr[]) {

        if(arr.length==1)
        {
            return arr;
        }
        else
        {
            int left = partition(arr, 0,0);

            if(arr.length==2)
            {
                return arr;
            }
            float[] L = new float[left];
            float[] R = new float[arr.length-left];

            for(int i=0;i<L.length;i++)
            {
                L[i] = arr[i];
            }

            int j= 0;
            for (int i=left;i<arr.length;i++)
            {
                R[j++] = arr[i];
            }

            float[] resultL = quickSort(L);
            float[] resultR = quickSort(R);
            float[] result = merge(resultL,resultR);

            return result;
        }
    }

    public float[] merge(float[] L, float[] R)
    {
        float[] resultArray = new float[L.length+R.length];
        for(int i=0;i<L.length;i++)
        {
            resultArray[i] = L[i];
        }
        int j=L.length;
        for(int i=0;i<R.length;i++)
        {
            resultArray[j++] = R[i];
        }
        return resultArray;
    }*/