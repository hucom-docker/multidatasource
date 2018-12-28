package com.example.multidatasource.datasourceconfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: DynamicDataSourceContextHolder
 * @Description: 动态数据源上下文管理
 * @author wy
 * @date 2018-12-27 17:17:41
 */
public class DynamicDataSourceContextHolder {

    /**
     *  线程级别的私有变量。当使用ThreadLocal维护变量时,ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 管理所有的数据源id,主要是为了判断数据源是否存在;
     */
    public static List<String> dataSourceIds = new ArrayList<String>();

    /**
     * 使用setDataSourceType设置当前的
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断当前数据源是否存在
     * @param dataSourceId
     * @return
     */
    public static boolean isContainsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
