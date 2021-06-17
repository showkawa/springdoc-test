package com.brian.demo.alg;

import java.util.Arrays;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 插入排序
 * 从后向前比较
 * 时间复杂度 0(N^2)
 */
@Slf4j
public class Alg003_InsertionSort {
    
    @Test
    public void test(){
        int[] init = generateArr();

        int[] arr1 =  init;
        insertSorted(arr1);
        log.info("brianhaung:{}",arr1);

        int[] arr2 =  init;
        comparator(arr2);
        log.info("comparator:{}", arr2);
        
        for(int k=0;k<arr1.length;k++){
            if(arr1[k] != arr2[k]){
                log.info("isEquals:false");
                break;
            }
        }
        log.info("isEquals:true");
        
    }


    private void insertSorted(int[] arr){
        if(arr == null || arr.length <=1){
            return;
        }
        // 0 ~1 compare and swap
        // 0 ~2 compare and swap
        for(int i=0;i<arr.length;i++){
            // i ~ N-1 compare and swap
            for(int j=i;j>0;j--){
                if(arr[j-1] > arr[j]) {
                    swap(arr ,j-1,j);
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
