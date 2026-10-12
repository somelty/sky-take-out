package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，用于标识某个方法进行字段自动填充处理
 */
@Target(ElementType.METHOD) // 用于标识加在什么类型上
@Retention(RetentionPolicy.RUNTIME) // lifecycle
public @interface AutoFill {

    OperationType value(); // 定义属性
}
