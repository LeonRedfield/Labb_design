

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
    private int threshold;

    private QuickSort(float[] arr, int thress)
    {
        this(arr, 0,arr.length-1);
        this.threshold = thress;
    }

    public QuickSort( float[] arr, int start, int end) {
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

    private int partition(float[] arr, int p, int r)
    {
        /*int i= p-1;
        for(int j= p; j<r;j++)
        {
            if(arr[j] <= arr[r])
            {
                i++;
                float tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        float tmp = arr[i+1];
        arr[i +1] = arr[r];
        arr[r] = tmp;

        return i+1;*/

        int left = p, right= r;
        float tmp;
        float pivotValue = arr[arr.length/2];

        while (left <= right) {

            while (arr[left] < pivotValue)
            {
                left++;
            }

            while (arr[right] > pivotValue)
            {
                right--;
            }
            if (left <= right) {
                tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;

                left++;
                right--;
            }
        }
        return left;
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

    @Override
    protected void compute() {
        if ((end-start) < threshold)
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

        /*int THRESHOLD =;
        if(start< end)
        {
            if((end-start) < THRESHOLD)
            {
                innerSort(arr, start, end+1);
            }
            else
            {
                int part = partition(start, end);
                QuickSort leftQuickSort = new QuickSort(0, part-1);
                leftQuickSort.fork();
                QuickSort rightQuickSort = new QuickSort(part+1, end);
                rightQuickSort.fork();
            }
        }*/
    }

    public float[] getArray()
    {
        return this.arr;
    }

    public static void main(String[] args) throws Exception{
        float[] arr = new float[SIZE];
        Random r = new Random();
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        for(int i=0;i<arr.length;i++) {arr[i] = r.nextInt(arr.length)+1;}
        System.out.println("random done.");
        long t1 = System.currentTimeMillis();

        //start
        QuickSort q = new QuickSort(arr, THRESHOLD);
        forkJoinPool.invoke(q);
        long t2 = System.currentTimeMillis();
        System.out.println("done at "+ (t2-t1)+"ms.");

        //check:
        float[] tmp = q.getArray();
        for (int i=0;i<tmp.length-1;i++)
        {
            if(tmp[i] > tmp[i+1])
            {
                System.out.println("sort failed");
            }
        }
        System.out.println("sort check complete.");
    }

    public static int SIZE =      100000000; //10^8
    public static int THRESHOLD = 1000;

}
