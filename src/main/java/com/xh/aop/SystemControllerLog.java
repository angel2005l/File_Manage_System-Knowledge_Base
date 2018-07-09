/**
 * 
 */
package com.xh.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 陈专懂
 * @date 2018年7月4日下午4:20:08
 * @version 1.0
 */


/**
 * 自定义注解  拦截controller
 * @author 陈专懂
 * @date 2018年7月4日下午4:21:43
 * @version 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemControllerLog {  
  
    String description()  default "";  
    String logType() default "";
    String isAdvice() default "false";
//    String logIsRead() default "N";
} 
 
	

/**
 * 自定义注解  拦截service
 * @author 陈专懂
 * @date 2018年7月4日下午4:22:07
 * @version 1.0
 *//*
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SystemServiceLog {  
  
    String description()  default "";  
  
  
} 
*/
	

