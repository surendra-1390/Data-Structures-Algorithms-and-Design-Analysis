import java.util.*;

public class Heap_PriorQue {
    private static final int HEAP_SIZE = 10;

    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); 
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < Heap_PriorQue.HEAP_SIZE; i++) {
            int data = new Random().nextInt(100) +1;
            minHeap.add(data);
            maxHeap.add(data);
        }

        System.out.print("\nMIN Heap : ");
        Iterator<Integer> iter = minHeap.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next() + " ");
        }

        System.out.print("\nMAX Heap : ");
        iter = maxHeap.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
    }
}