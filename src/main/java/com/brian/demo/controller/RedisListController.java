package com.brian.demo.controller;


import com.brian.demo.tool.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RedisListController {

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/redis/list/lpush")
    public ResponseEntity<Long> lpush(@RequestParam String key, @RequestParam String value){
        return new ResponseEntity<>(redisUtil.lLeftPush(key, value), HttpStatus.OK);
    }

    @PostMapping("/redis/list/lpushAll")
    public ResponseEntity<Long> lpushAll(@RequestParam String key, @RequestParam String... value){
        return new ResponseEntity<>(redisUtil.lLeftPushAll(key, value), HttpStatus.OK);
    }

    @GetMapping("/redis/list/lpop")
    public ResponseEntity<Object> lpop(@RequestParam String key){
        return new ResponseEntity<>(redisUtil.lLeftPop(key), HttpStatus.OK);
    }

    @PostMapping("/redis/list/rpoplpush")
    public ResponseEntity<Object> rpoplpush(@RequestParam String sourceKey, @RequestParam ssString destinationKey){
        return new ResponseEntity<>(redisUtil.lRightPopAndLeftPush(sourceKey, destinationKey), HttpStatus.OK);
    }



}
