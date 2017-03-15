import java.util.Random;

/**
 * Created by Santos on 2017-03-13.
 */
public class Main {

    public static void main(String args[]){

        float[] list;
        int count = 21; //Number of times running calculations
        int cores = 2; // Number of cores
        long[] result = new long[count];

        for(int i = 0; i < count; i++){
            System.out.println("generating...");
            list = generateList();
            MergeSort2 ms = new MergeSort2(list,0, list.length);
            garbage();
            System.out.println("calculating: " +i);
            result[i] = ms.sort(cores); //specify no of cores
            System.out.println("calculation done");
            System.out.println((float)result[i]/1000000000);

        }
        float r = 0;
        for(int i = 1; i < count; i++){
            r = r +result[i];
        }
        System.out.println("Genomsnitt: " +(r/(count-1))/1000000000);
        //float[] result = ms.result();
/*
        for(int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
  */

    }
    private static float[] generateList(){
        float[] list = new float[100000000];
        for(int i = 0; i < list.length; i++) {
            list[i] = new Random().nextInt(100);
        }
        return list;
    }

    private static void garbage() {
        System.out.println("Garbage collecting...");
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
