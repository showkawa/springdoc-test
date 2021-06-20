package com.brian.demo.alg;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Alg004_BinarySearch {
    


    @Test
    public void test(){
        for(int i=0;i<5000;i++){
            int[] arr  = generateArr();
            comparator(arr);
            log.info("arr:{}",arr);
            int target = arr[5];
            log.info("target:{}",target);       
            int index = searchMatch(arr, 0, arr.length-1, target);
            if(index != 5){
                log.info("test fail!");
                break;
            }
        }
        log.info("test success!");
    }



    private int searchMatch(int[] arr, int low, int high, int target){
        if(low > high){
            return -1;
        }
        int mid = low + ((high-low)>>1);

        // 
        if(arr[mid] == target){
            return mid;
        } else if(arr[mid] < target){
            // search in [mid+1, high]
            return searchMatch(arr, mid+1, high, target);
        } else {
            // search in [low, mid-1]
            return searchMatch(arr, low, mid-1, target);
        }
    }


    private void comparator(int[] arr){
        Arrays.sort(arr);
    }

    private int[] generateArr(){
       return new Random().ints(-100, 100).distinct().limit(20).toArray();

    }

}
