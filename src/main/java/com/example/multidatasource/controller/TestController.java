package com.example.multidatasource.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.multidatasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 测试Controller
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/test")
    public JSONObject test(HttpServletRequest request){
        return  testService.test(request);
    }
}
