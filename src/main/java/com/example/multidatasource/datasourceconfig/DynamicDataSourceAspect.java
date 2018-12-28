package com.example.multidatasource.datasourceconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TargetDataSource
 * @Description: 动态数据源通知
 * @author wy
 * @date 2018-12-27 17:20:03
 */
@Aspect
@Order(-1)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private final static Logger logger =  LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * @Before：在方法执行之前进行执行：
     * @annotation(targetDataSource)：会拦截注解targetDataSource的方法，否则不拦截;
     */
    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        //获取当前的指定的数据源;
        String dbId = targetDataSource.name();
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DynamicDataSourceContextHolder.isContainsDataSource(dbId)) {
            //joinPoint.getSignature() ：获取连接点的方法签名对象
            logger.error("数据源 " + dbId + " 不存在，使用默认数据源 -> " + joinPoint.getSignature());
        } else {
            logger.info("使用数据源：" + dbId);
            DynamicDataSourceContextHolder.setDataSourceType(dbId);
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.info("清除数据源：" + targetDataSource.name() + "!");
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}