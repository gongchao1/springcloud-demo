package com.sckj.auth.exception;

/**
 * 异常枚举(错误码和错误信息，组装起来统一管理)
 * Created by wangheduo on 2018/9/6
 */
public enum  ExceptionEnum {

    UNKONW_ERROR(-1,"未知错误"),
    /**
     * 字典
     */
    DICT_EXISTED(-1,"字典已经存在"),
    ERROR_CREATE_DICT(-1,"创建字典失败"),
    ERROR_WRAPPER_FIELD(-1,"包装字典属性失败"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(-1,"FILE_READING_ERROR!"),
    FILE_NOT_FOUND(-1,"FILE_NOT_FOUND!"),
    UPLOAD_ERROR(-1,"上传图片出错"),

    /**
     * 权限和数据问题
     */
    DB_RESOURCE_NULL(-1,"数据库中没有该资源"),
    NO_PERMITION(-1, "权限异常"),
    REQUEST_INVALIDATE(-1,"请求数据格式不正确"),
    INVALID_KAPTCHA(-1,"验证码不正确"),
    CANT_DELETE_ADMIN(-1,"不能删除超级管理员"),
    CANT_FREEZE_ADMIN(-1,"不能冻结超级管理员"),
    CANT_CHANGE_ADMIN(-1,"不能修改超级管理员角色"),
    ROLE_ALREADY_EXIST(-1,"该角色已经存在！！"),
    AUTH_ALREADY_EXIST(-1,"该权限已经存在！！"),

    /**
     * 账户问题
     */
    USER_ALREADY_REG(-1,"该账号或邮箱已经注册"),
    NO_THIS_USER(-1,"没有此用户"),
    USER_NOT_EXISTED(-1, "没有此用户"),
    ACCOUNT_FREEZED(-1, "账号被冻结"),
    OLD_PWD_NOT_RIGHT(-1, "原密码不正确"),
    TWO_PWD_NOT_MATCH(-1, "两次输入密码不一致"),

    /**
     * 错误的请求
     */
    MENU_PCODE_COINCIDENCE(-1,"菜单编号和副编号不能一致"),
    EXISTED_THE_MENU(-1,"菜单编号重复，不能添加"),
    DICT_MUST_BE_NUMBER(-1,"字典的值必须为数字"),
    REQUEST_NULL(-1,"请求有错误"),
    SESSION_TIMEOUT(-1, "会话超时"),
    SERVER_ERROR(-1, "服务器异常");

    private Integer code;
    private String msg;

    ExceptionEnum(Integer code,String msg) {
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
