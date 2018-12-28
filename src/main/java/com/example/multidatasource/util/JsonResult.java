package com.example.multidatasource.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <br>
 * 标题: 自定义JSON响应数据结构<br>
 * 描述: 统一请求响应返回的格式<br>
 * 200 成功
 * 500 错误，错误信息在msg字段中
 * 501 Bean验证错误信息，以map返回
 * 502 拦截器拦截到用户token出错
 * 555：抛出异常信息
 * @author
 * @date 2018/04/19
 */
@Getter
@Setter
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 4997293587553904193L;

    public static final int CODE_SUCCESS = 200;
    public static final String MSG_SUCCESS = "success";

    public static final int CODE_FAIL = 500;
    public static final String MSG_FAIL = "fail";

    public static final int CODE_NULL = 404;
    public static final String MSG_NULL = "null";
    /**
     * 响应状态
     */
    private Integer status;
    /**
     * 响应消息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Object data;


    public static JSONObject build(Integer status, String msg, Object data) {
        JSONObject json = new JSONObject();
        json.put("status",status);
        json.put("msg",msg);
        json.put("data",data);
        return json;
    }


}