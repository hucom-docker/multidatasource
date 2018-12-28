package com.example.multidatasource.mapper;

import com.example.multidatasource.datasourceconfig.TargetDataSource;
import com.example.multidatasource.model.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * CompanyMapper
 * @author
 */
@Mapper
@Component
public interface CompanyMapper {

    /**
     *数据源1
     */
    @TargetDataSource(name = "ds1")
    Company findCompanyById(String coId);
}
