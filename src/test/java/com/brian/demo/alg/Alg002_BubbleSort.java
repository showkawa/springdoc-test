package com.brian.demo.alg;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序
 * 时间复杂度 0(N^2)
 */
@Slf4j
public class Alg002_BubbleSort {


    @Test
    public void test(){
        int[] init = generateArr();

        int[] arr1 =  init;
        bubbleSorted(arr1);
        log.info("brianhaung:{}",arr1);

        int[] arr2 =  init;
        comparator(arr2);
        log.info("comparator:{}", arr2);
    }


    private void bubbleSorted(int[] arr){
        if(arr == null || arr.length <=1){
            return;
        }
        // i ~ N-1
        for(int i=arr.length-1;i>0;i--){
            // i ~ N-1 set max to the end of arr
            for(int j=0;j<i;j++){
                if(arr[j+1] < arr[j]) {
                    swap(arr ,j+1,j);
                } 
            }
            
        }
    }

    // 异或运算
    private void swap(int[] arr, int a, int b){
        arr[a] = arr[a]^arr[b];
        arr[b] = arr[a]^arr[b];
        arr[a] = arr[a]^arr[b];
    }


    private void comparator(int[] arr){
        Arrays.sort(arr);
    }

    private int[] generateArr(){
        int len = (int)(Math.random()*200);
        int[] arr = new int[len];
        for(int i=0;i<len;i++){
            int a =  (int) (Math.random()*10000 - Math.random()*10000);
            arr[i] = a;
        }
        return arr;

    }

}
