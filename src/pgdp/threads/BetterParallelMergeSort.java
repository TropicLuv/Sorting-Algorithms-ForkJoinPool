package pgdp.threads;

import java.util.concurrent.RecursiveAction;

public class BetterParallelMergeSort extends RecursiveAction {

    private final int low,high;
    private final Comparable[] helper;
    private final Comparable[] array;
    private int range = 2048;


    public BetterParallelMergeSort(final Comparable[] array) {
        helper = new Comparable[array.length];
        low = 0;
        this.array = array;
        high = this.array.length - 1;
        if (this.array.length < 1024)
            range = 512;


    }
    public BetterParallelMergeSort(final Comparable[] array, final Comparable[] helper,final int low,final int high) {
        this.low = low;
        this.high = high;
        this.array = array;
        this.helper = helper;
    }

    @Override
    protected void compute() {
        final int middle = (low + high)/2;
        if (low < high & Math.abs(high - low) > range){
            BetterParallelMergeSort newTask1 = new BetterParallelMergeSort(array,helper,low,middle);
            BetterParallelMergeSort newTask2 = new BetterParallelMergeSort(array,helper,middle + 1,high);


            invokeAll(newTask1,newTask2);

            MergeSort.merge(array,helper,low,middle,high);
        }else {
            MergeSort.mergesort(array, helper, low, high);
        }
    }
}
/*
///////////////////////////////////////2//CORES//4//THREADS/////////////////////IDK//WHY//DOESN'T//Work///////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void compute() {
        final int middlequarter = (low + high)/4;
        if (low < high && Math.abs(low - high) > range){
            BetterParallelMergeSort newTask1 = new BetterParallelMergeSort(array,helper,low,middlequarter);
            BetterParallelMergeSort newTask2 = new BetterParallelMergeSort(array,helper,middlequarter + 1,middlequarter * 2);
            BetterParallelMergeSort newTask3 = new BetterParallelMergeSort(array,helper,middlequarter*2 +1, middlequarter*3);
            BetterParallelMergeSort newTask4 = new BetterParallelMergeSort(array,helper,middlequarter* 3 + 1,high);


            invokeAll(newTask1,newTask2,newTask3,newTask4);

            MergeSort.merge(array,helper,low,middlequarter*2,high);

        }else {
            MergeSort.mergesort(array, helper, low, high);
        }
    }

}
*/
