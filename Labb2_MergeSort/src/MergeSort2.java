
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by Santos on 2017-03-14.
 */
public class MergeSort2 extends RecursiveAction{

    private float[] mSource;
    private int mLength;
    private int mStart;

    private long startTime;
    private long stopTime;


    public MergeSort2(float[] src, int start, int length) {

        mSource = src;
        mStart = start;
        mLength = length;
    }

    /*
     *  Returns time it took to complete the calculations
     */
    public long sort(int cores){
        this.startTime = System.nanoTime();
        ForkJoinPool joinPool = new ForkJoinPool(cores);
        joinPool.invoke(this);
        Arrays.parallelSort(mSource, 0, mLength);
        this.stopTime = System.nanoTime();
        return stopTime - startTime;

    }


    private int[] sort2(int start, int length) {
        if(length <= 1){
            return new int[]{start,length};
        }
        int leftSize = length/2;
        int leftStart = start;
        int rightSize = length/2;
        if(length%2==1) {
            rightSize++;
        }
        return merge(sort2(start, leftSize), sort2(start+leftSize, rightSize));

    }
    private int[] merge(int[] left, int[] right) {
        int leftSize = left[1];
        int rightSize = right[1];
        int totalSize = leftSize + rightSize;
        float[] list = new float[totalSize];
        for(int i = 0; i < totalSize; i++){
            list[i] = mSource[left[0]+i];
        }
        int l = 0;
        int r = 0;
        for(int i = 0; i < totalSize; i++){
            if(l < leftSize && (r >= rightSize || list[l] < list[leftSize+r])){
                mSource[left[0]+l+r] = list[+l];
                l++;
            }else
            {
                mSource[left[0]+l+r] = list[leftSize+r];
                r++;
            }

        }
        return new int[] {left[0], totalSize};
    }

    protected static int sThreshold = 7000;
    public void setThreshold(int t){
        sThreshold = t;
    }

    @Override
    protected void compute() {
        //System.out.println("compute " +mLength);
        if(mLength < sThreshold) {
            return;
        }
        else {
            int leftSplit = mLength / 2;
            int rightSplit = mLength / 2;
            //if(mLength%2 == 1)
                //rightSplit++;
            MergeSort2 left = new MergeSort2(mSource, mStart, leftSplit);
            MergeSort2 right = new MergeSort2(mSource, mStart + rightSplit, rightSplit);
            invokeAll(left, right);

        }
    }

    public float[] result() {
        return mSource;
    }
}
