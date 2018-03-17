package cnic.cjh.algorithm.heapsort;

/**
 *  Binary Max Heap according the book “the Introduction of Algorithm” Chapter 6
 *
 */
public class MaxHeap {
    int left(int i){
        return 2 * ( i + 1) - 1;
    }

    int right(int i){
        return 2 * (i + 1);
    }

    int paren(int i){
        return (i + 1) / 2 - 1;
    }

    /**
     * @param A
     * @param i
     *      0,1,...length-1
     * @return
     *      true : A hased changed!
     *      false: A has not changed!
     */
    boolean max_heapify(int[] A, int i){
        if((i + 1) > A.length / 2)
                return false;
        int max_index = -1,l = -1, r = -1;
        while(((i + 1) <= A.length / 2)){
            /**
             * 1. find the child max index
             */
            l = left(i);
            r = right(i);
            max_index = l;
            if( r < A.length){
                if( A[l] < A[r]){
                    max_index = r;
                }
            }
            /**
             * 2. chose the max index from the current,left child ,right child node
             */
            if (A[max_index] < A[i]){
                break;
            }else {
                int tmp = A[max_index];
                A[max_index] = A[i];
                A[i] = tmp;
                i = max_index;
            }
        }
        return true;
    }

    void build_max_heap(int[] A){
        for(int i = A.length / 2- 1; i >= 0; i--){
            max_heapify(A,i);
        }
        return;
    }

    int maximum(int[] A){
        return A[0];
    }

    void heapsort(int[] A){
        build_max_heap(A);
        for( int i = 0; i < A.length - 1; i ++){

        }
    }

//    void insert(int[] A, int v){ }

//    boolean increase_max_heap(int[] A, int i, int v){return true;}


    public static void main(String[] args){
        MaxHeap m_p = new MaxHeap();
        /**
         * max_heapify test
         */
        int[] A = {0,6,5,4,3,2,1};
        m_p.max_heapify(A,0);

        /**
         * build max heap test
         */
        int[] B = {10,1,8,9,6,7,6,0,9,12,18,2,0,14};
        m_p.build_max_heap(B);
    }

}