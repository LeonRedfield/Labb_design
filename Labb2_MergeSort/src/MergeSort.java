import java.util.Random;

/**
 * Created by Santos on 2017-03-13.
 */
public class MergeSort {

    float[] list;
    float[] sortedList;

    public MergeSort(int size) {

        Random r = new Random();
        list = new float[size];
        for(int i = 0; i < size; i++) {
            list[i] = r.nextInt(10000);
        }
    }


    public float[] sort() {
        warmup();
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime;
        long startTime = System.nanoTime();
        sortedList = sort(list);
        endTime = System.nanoTime();
        System.out.println("Time.." +((float)(endTime-startTime)/1000000000));
        return sortedList;
    }

    private void warmup(){
        System.out.println("Warming up..");
        float[] tmpList = new float[10000];
        sort(tmpList);
        System.out.println("Warmup done...");
    }

    private float[] sort(float[] list) {
        if(list.length == 1) {
            return list;
        }

        int size = list.length;

        float[] left = new float[size/2];
        float[] right;
        if(size % 2 == 1)
            right = new float[(size/2)+1];
        else
            right = new float[size/2];


        for(int i = 0; i < size/2; i++) {
            left[i] = list[i];
            right[i] = list[(size/2)+i];
        }
        if(size%2 == 1)
            right[right.length-1] = list[size-1];


        return merge(sort(left), sort(right));

    }

    private float[] merge(float[] l1, float[] l2) {
        float[] list = new float[l1.length+l2.length];
        int j = 0;
        int k = 0;
        for(int i = 0; i < l1.length+l2.length; i++){
            if(j < l1.length && (k >= l2.length || l1[j] < l2[k])){
                list[i] = l1[j];
                j++;
            }else
            {
                list[i] = l2[k];
                k++;
            }
        }
        return list;
    }
}
