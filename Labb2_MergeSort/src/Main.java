import java.util.Random;

/**
 * Created by Santos on 2017-03-13.
 */
public class Main {

    public static void main(String args[]){

        MergeSort ms = new MergeSort(10000000);

        float[] sortedList = ms.sort();

        for(int i = 0; i < sortedList.length-1; i++){
            if(sortedList[i] > sortedList[i+1]){
                System.out.println("Algorithm error");
            }
        }

        System.out.println("All numbers checked");
    }
}
