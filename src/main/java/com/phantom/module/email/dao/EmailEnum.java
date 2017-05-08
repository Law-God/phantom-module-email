package com.phantom.module.email.dao;

/**
 * Created by Administrator on 2016/9/26.
 */
public enum EmailEnum{
    STATUS_0(0),  //    调用构造函数来构造枚举项
    STATUS_1(1),
    STATUS_2(2),
    STATUS_3(3)
    ;

    private int value = 0;

    private EmailEnum(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static EmailEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return STATUS_0;
            case 1:
                return STATUS_1;
            case 2:
                return STATUS_2;
            case 3:
                return STATUS_3;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
