package com.brian.demo.controller;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.brian.demo.tool.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RedisStringController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/redis/string/set")
    public ResponseEntity<Boolean> set(String key, String value){
        redisUtil.set(key, value);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/redis/string/setNx")
    public ResponseEntity<Boolean> setNx(String key, String value){
        return new ResponseEntity<>(redisUtil.setIfAbsent(key, value), HttpStatus.OK);
    }

    @PostMapping("/redis/string/setXx")
    public ResponseEntity<Boolean> setXx(String key, String value){
        return new ResponseEntity<>(redisUtil.setIfPresent(key, value), HttpStatus.OK);
    }

    @PostMapping("/redis/string/setEx")
    public ResponseEntity<Boolean> setEx(String key, String value, long timeout){
        redisUtil.setEx(key, value, timeout, TimeUnit.SECONDS);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/redis/string/get")
    public ResponseEntity<String> get(String key){
       String result = redisUtil.get(key).toString();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/redis/string/append")
    public ResponseEntity<Integer> append(String key, String value){
        return new ResponseEntity<>(redisUtil.append(key, value), HttpStatus.OK);
    }

    @GetMapping("/redis/string/getLen")
    public ResponseEntity<Long> geten(String key){
        return new ResponseEntity<>(redisUtil.size(key), HttpStatus.OK);
    }

    @PostMapping("/redis/string/incr")
    public ResponseEntity<Long> incr(String key, long increment){
        return new ResponseEntity<>(redisUtil.incrBy(key, increment), HttpStatus.OK);
    }


    @PostMapping("/redis/string/mset")
    public ResponseEntity<Boolean> mset(Map<String, String> maps){
        redisUtil.multiSet(maps);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/redis/string/mget")
    public ResponseEntity<List<Object>> mget(List<String> keys){
        return new ResponseEntity<>(redisUtil.multiGet(keys), HttpStatus.OK);
    }

    @GetMapping("/redis/string/msetNx")
    public ResponseEntity<Boolean> msetNx(Map<String, String> maps){
        return new ResponseEntity<>(redisUtil.multiSetIfAbsent(maps), HttpStatus.OK);
    }

    @GetMapping("/redis/string/getRange")
    public ResponseEntity<Object> getRange(String key, long start, long end){
        return new ResponseEntity<>(redisUtil.getRange(key, start, end), HttpStatus.OK);
    }

    @PostMapping("/redis/string/setRange")
    public ResponseEntity<Boolean> setRange(String key, String value, long offset){
        redisUtil.setRange(key, value, offset);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }



}
