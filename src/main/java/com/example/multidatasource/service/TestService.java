package com.example.multidatasource.service;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;

public interface TestService {
    JSONObject test(HttpServletRequest request);
}
