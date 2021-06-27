package com.brian.demo.alg;

import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 归并排序
 * 复杂度T(N) = 2* T(N/2) + O(N) 
 * a=2 b=2 d=1, log(2,2)=1
 * => NlogN
 */
@Slf4j
public class Alg006_MergeSort {


    @Test
    public void test(){
        for(int i=0;i<5000;i++){
            int[] arr  = generateArr();
            mergeSort(arr, 0, arr.length-1);    
            log.info("arr:{}",arr);
        }
    }


    private void mergeSort(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        int mid = L + ((R-L)>>1);
        // left part merge sort
        mergeSort(arr, L, mid);
        // right part merge sort
        mergeSort(arr,mid+1,R);

        merge(arr, L, mid, R);

    }


    private void merge(int[] arr, int L, int M, int R){
        // additional array
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = M+1;
        // 
        while(p1<=M && p2<=R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
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
    }


    private int[] generateArr(){
        return new Random().ints(-100, 100).distinct().limit(20).toArray();
 
    }
    
}
