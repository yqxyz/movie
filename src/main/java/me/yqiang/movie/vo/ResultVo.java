package me.yqiang.movie.vo;

import lombok.Data;

@Data
public class ResultVo<T> {

    //错误码
    private Integer code;

    //错误信息
    private String msg;

    //返回数据
    private T Data;


}