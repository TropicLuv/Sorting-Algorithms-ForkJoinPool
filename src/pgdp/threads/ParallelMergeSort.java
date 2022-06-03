package pgdp.threads;

import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {

    private final int low,high;
    private final Comparable[] helper;
    private final Comparable[] array;


    public ParallelMergeSort(final Comparable[] array) {
        helper = new Comparable[array.length];
        low = 0;
        this.array = array;
        high = this.array.length - 1;


    }
    public ParallelMergeSort(final Comparable[] array, final Comparable[] helper,final int low,final int high) {
        this.low = low;
        this.high = high;
        this.array = array;
        this.helper = helper;
    }

    @Override
    protected void compute() {
        final int middle = (low + high)/2;
        if (low < high){

            invokeAll(new ParallelMergeSort(array,helper,low,middle),new ParallelMergeSort(array,helper,middle + 1,high));

            MergeSort.merge(array,helper,low,middle,high);
        }



    }
}
