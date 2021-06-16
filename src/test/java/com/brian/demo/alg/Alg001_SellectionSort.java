package com.brian.demo.alg;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 选择排序
 * 时间复杂度 0(N^2)
 * 额外空间复杂度0(1)
 */
@Slf4j
public class Alg001_SellectionSort {


    @Test
    public void test(){
        int[] arr1 =  {2,4,6,23,2,1,34,-7,5,0,23,24,-90};
        sellectionSorted(arr1);
        log.info("brianhaung:{}",arr1);

        int[] arr2 =  {2,4,6,23,2,1,34,-7,5,0,23,24,-90};
        comparator(arr2);
        log.info("comparator:{}", arr2);

    }


    private void sellectionSorted(int[] arr){
        if(arr == null || arr.length <=1){
            return;
        }
        // i ~ N-1
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            // i ~ N-1 get min
            for(int j=i+1;j<arr.length;j++){
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;    
            }
            swap(arr ,minIndex,i);
        }
    }


    private void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }


    private void comparator(int[] arr){
        Arrays.sort(arr);
    }

}
