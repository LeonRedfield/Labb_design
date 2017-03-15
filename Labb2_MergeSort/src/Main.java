import java.util.Random;

/**
 * Created by Santos on 2017-03-13.
 */
public class Main {

    public static void main(String args[]){

        //MergeSort ms = new MergeSort(10000000);
        float[] list = new float[100000000];
        for(int i = 0; i < list.length; i++) {
            list[i] = new Random().nextInt(100);
        }
        //float[] test = new float[]{5, 2, 3, 4, 1, 6, 8, 7, 5, 6, 3, 4, 2, 8, 5, 33, 6, 1, 7};
        MergeSort2 ms = new MergeSort2(list,0, list.length);
        System.out.println("Starting...");
        System.out.println((float)ms.sort()/1000000000);
        float[] result = ms.result();
/*
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
  */
        for(int i = 0; i < result.length-1; i++){
            if(result[i] > result[i+1]){
                System.out.println("Algorithm error");
            }
        }

        System.out.println("All numbers checked");
    }
}
