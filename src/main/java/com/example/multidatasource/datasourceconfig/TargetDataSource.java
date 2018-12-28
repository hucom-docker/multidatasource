package com.example.multidatasource.datasourceconfig;

import java.lang.annotation.*;

/**
 * @ClassName: TargetDataSource
 * @Description: 在方法上使用，用于指定使用哪个数据源(作用于类、接口或者方法上)
 * @author wy
 * @date 2018-12-27 17:20:03
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
