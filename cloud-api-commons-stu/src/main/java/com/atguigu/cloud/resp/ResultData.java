package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * ClassName: ResultData
 * Package: com.atguigu.cloud.resp
 * Description:
 *
 * @Author Xu, Luqin
 * @Create 2024/11/8 18:38
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data){
        return new ResultData<T>()
                .setCode(ReturnCodeEnum.RC200.getCode())
                .setMessage(ReturnCodeEnum.RC200.getMessage())
                .setData(data);
    }

    public static <T> ResultData<T> fail(String code, String message){
        return new ResultData<T>()
                .setCode(code)
                .setMessage(message);
    }
}
