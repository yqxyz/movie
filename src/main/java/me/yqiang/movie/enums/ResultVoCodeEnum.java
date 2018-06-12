package me.yqiang.movie.enums;

public enum  ResultVoCodeEnum {

    UNKONW_ERROR(-1,"未知错误"),
    SUCCESS(0,"成功"),
    LOSE(1,"失败"),
    PARAM_ERROR(17,"参数不正确"),
            ;


    private Integer code;

    private String msg;

    ResultVoCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
