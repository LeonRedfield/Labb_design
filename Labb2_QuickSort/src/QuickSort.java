import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by Teddy on 2017-03-14.
 */
public class QuickSort /*extends ForkJoinTask*/{
    private int size, randomRange;
    private float[] array;
    public QuickSort(int size, int randomRange)
    {
        this.size=size;
        this.randomRange = randomRange;
    }

    public float[] sort()
    {
        float[] arr = new float[size];
        Random r = new Random();
        System.out.println("Random: ");
        for(int i=0;i<size;i++)
        {
            arr[i] = r.nextInt(randomRange)+1;
            System.out.println(arr[i]);
        }
        System.out.println("end/");

        return quickSort(arr);
    }

    /*private int partition(float arr[])
    {


        return i;
    }*/

    private float[] quickSort(float arr[]) {

        if(arr.length == 1)
        {
            return arr;
        }

        int left = 0, right= arr.length-1;
        float tmp;
        float pivot = arr[arr.length-1];

        while (left <= right) {
            while (arr[left] < pivot)
                left++;
            while (arr[right] > pivot)
                right--;
            if (left <= right) {
                tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }

        System.out.println("result, with pivot=" + pivot);
        for(int i=0;i< arr.length;i++)
        {
            System.out.println("arr["+i+"]=" + arr[i]);
        }

        float[] L = new float[left+1];
        float[] R = new float[arr.length-left];
        System.out.println("leftIndex = " + left+" L.lenght=" + L.length);
        for(int i=0;i<L.length;i++)
        {
            System.out.println(L[i]);
            System.out.println("i=" + i+ " "+arr[i]);
            L[i] = arr[i];
        }

        int j= 0;
        for (int i=left+1;i<arr.length;i++)
        {
            R[j++] = arr[i];
        }

        float[] resultL = quickSort(L);
        float[] resultR = quickSort(R);

        return quickSort(merge(resultL,resultR));
    }

    private float[] merge(float[] L, float[] R)
    {
        float[] resultArray = new float[L.length+R.length];
        System.arraycopy(L, 0, resultArray, 0, L.length);
        System.arraycopy(R,0, resultArray, L.length-1, R.length);
        //System.out.println("L.l = " + L.length + " R.l = " +R.length + " resuAr.l=" + resultArray.length);
        return resultArray;
    }





    /*@Override
    public Object getRawResult() {
        return null;
    }

    @Override
    protected void setRawResult(Object o) {

    }

    @Override
    protected boolean exec() {
        return false;
    }*/
}
