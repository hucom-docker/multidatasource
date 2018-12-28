package com.example.multidatasource.mapper;

import com.example.multidatasource.datasourceconfig.TargetDataSource;
import com.example.multidatasource.model.EmpInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
/**
 * EmpInfoMapper
 * @author
 */
@Mapper
@Component
public interface EmpInfoMapper {

    /**
     * 数据源2
     */
    @TargetDataSource(name = "ds2")
    EmpInfo findEmpById(String empId);
}
