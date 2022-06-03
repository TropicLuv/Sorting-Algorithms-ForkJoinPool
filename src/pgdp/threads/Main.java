package pgdp.threads;

import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        Integer[] i = {4,7,8,4,0,7,2,0,5};
        ParallelMergeSort aaa = new ParallelMergeSort(i);
        final ForkJoinPool forkJoinPool = new ForkJoinPool(7);
        forkJoinPool.invoke(aaa);
//        MergeSort.mergesort(i);

        for (Integer a: i) {
            System.out.println(a);
        }

    }
}
