package com.brian.demo.controller;


import java.util.Set;

import com.brian.demo.tool.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RedisKeyController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/redis/key/getAllKeys")
    public ResponseEntity<Set<String>> getAllKeys(String pattern){
        Set<String> keys = redisUtil.keys(pattern);
        return new ResponseEntity<Set<String>>(keys, HttpStatus.OK);
    }

    @GetMapping("/redis/key/exist")
    public ResponseEntity<Boolean> exist(String key){
        return new ResponseEntity<>(redisUtil.hasKey(key), HttpStatus.OK);
    }

    @GetMapping("/redis/key/type")
    public ResponseEntity<DataType> keyType(String key){
        return new ResponseEntity<>(redisUtil.type(key), HttpStatus.OK);
    }




   
}
