package com.xq.crowd_funding.common;/*
    @auther yangjie
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  返回结果 有返回状态，返回信息，返回数据
 *
 * @param <T>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity<T> {

    private String result;
    private String message;
    private T data;
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MSG = "NO_MSG";
    public static final String NO_DATA = "NO_DATA";
    // 成功返回结果 没有数据
    public static ResultEntity<String> successNoData() {
        return new ResultEntity<>(SUCCESS, NO_MSG, NO_DATA);
    }
    // 成功返回结果 有数据
    public static <T> ResultEntity<T> successWithData(T data) {
        return new ResultEntity<>(SUCCESS, NO_MSG, data);
    }
    // 失败返回结果 有数据
    public static <T> ResultEntity<T> failed(String message) {
        return new ResultEntity<>(FAILED, message, null);
    }
}
