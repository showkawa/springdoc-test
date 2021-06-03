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
public class RedisListController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/redis/list/lpush")
    public ResponseEntity<Long> lpush(String key, String value){
        return new ResponseEntity<>(redisUtil.lLeftPush(key, value), HttpStatus.OK);
    }

    @PostMapping("/redis/list/lpushAll")
    public ResponseEntity<Long> lpushAll(String key, String... value){
        return new ResponseEntity<>(redisUtil.lLeftPushAll(key, value), HttpStatus.OK);
    }

    @GetMapping("/redis/list/lpop")
    public ResponseEntity<Object> lpop(String key){
        return new ResponseEntity<>(redisUtil.lLeftPop(key), HttpStatus.OK);
    }

    @PostMapping("/redis/list/rpoplpush")
    public ResponseEntity<Object> rpoplpush(String sourceKey, String destinationKey){
        return new ResponseEntity<>(redisUtil.lRightPopAndLeftPush(sourceKey, destinationKey), HttpStatus.OK);
    }



}
