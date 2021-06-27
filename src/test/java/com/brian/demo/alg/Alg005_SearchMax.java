package com.brian.demo.alg;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 递归排序
 * 复杂度T(N) = 2* T(N/2) + O(1) 
 *  a=2 b=2 d=0, log(2,2) > 0
 *  => N
 */
@Slf4j
public class Alg005_SearchMax {


    @Test
    public void test(){
        for(int i=0;i<5000;i++){
            int[] arr  = generateArr();
            log.info("arr:{}",arr);
            int max = getMax(arr, 0, arr.length-1);
            int matchVal = Arrays.stream(arr).max().getAsInt();
            if(max != matchVal){
                arr =null;
                log.info("test failed!");
                return;
            }
            log.info("max:{}",max);
            arr =null;
        }
        log.info("test success!");
    }



    public int getMax(int[] arr, int left, int right){
        if(left == right){
            return arr[left];
        }
        int mid = left+ ((right-left) >> 1);
        int lMax = getMax(arr,left,mid);
        int rMax = getMax(arr, mid+1,right);
        return Math.max(lMax, rMax);
    }
    

    private int[] generateArr(){
        return new Random().ints(-100, 100).distinct().limit(20).toArray();
 
    }
}
