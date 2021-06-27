package com.brian.demo.alg;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序拓展 - 求小和 
 */
@Slf4j
public class Alg007_SmallSort {


    @Test
    public void test(){
        for(int i=0;i<5000;i++){
            // int[] arr = new int[]{1,2,3,4,5};
            // 4*1+3*2+2*3+4*1
            int[] arr  = generateArr();
            int[] arr2 = Arrays.copyOf(arr, arr.length);
            int val = mergeSort(arr, 0, arr.length-1);    
            int compareVal = compareVal(arr2);
            log.info("avl:{}, compareVal:{}", val, compareVal);
        }
    }


    private int mergeSort(int[] arr, int L, int R){
        if(L == R){
            return 0 ;
        }
        int mid = L + ((R-L)>>1);
		// lfet part sort and get small sum
		// right part sort and get small sum
		// merge left and right part and get small sum
       return mergeSort(arr, L, mid) + mergeSort(arr,mid+1,R) + merge(arr, L, mid, R);
    }


    private int merge(int[] arr, int L, int M, int R){
        // additional array
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        int val = 0;
        // 
        while(p1<=M && p2<=R){
            // if the p1< p2, add the value (r-p2+1)* arr[p1], because alrady sorted both the left and right part 
			// if p1 >= p2, add 0
            val += arr[p1] < arr[p2] ? (R-p2+1)* arr[p1]: 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // if the point moved to end in the right part
        while(p1<=M){
            help[i++] = arr[p1++];
        }
        // if the point moved to end in the left part
        while(p2<=R){
            help[i++] = arr[p2++];
        }
        // copy the array to arr
        for(i = 0;i<help.length;i++){
            arr[L+i] = help[i];
        }
        return val;
    }


    private int[] generateArr(){
        return new Random().ints(-100, 100).distinct().limit(20).toArray();
 
    }

    private int compareVal(int[] arr){
        int val = 0;
        for(int i = arr.length-1;i>0;i--){
            for(int j = i-1;j>=0 && i>0;j--){
                val += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return val;
    }
    
}
