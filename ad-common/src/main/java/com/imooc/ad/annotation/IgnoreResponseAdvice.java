package com.imooc.ad.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chenkaihua on 2019-04-19 10:15
 * 应有一些特殊情况不需要统一的返回格式，此注解可以使用在方法，类级别上
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/**
 * class：这种类型的Annotation编译时会被保留，在class文件中存在，但是不被JVM读取。
 *
 * source：这种类型的Annotation只会在源代码级别保留，编译后就会被忽略，所以不会保留在class文件中。
 *
 * runtime：这种类型的Annotation将会被JVM保留，所以它们能在运行的时候被JVM或者是其他反射机制的代码进行读取和使用。
 */
public @interface IgnoreResponseAdvice {
}
