package com.example.multidatasource;

import com.example.multidatasource.datasourceconfig.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


//注册动态多数据源
@Import({DynamicDataSourceRegister.class})
@SpringBootApplication
public class MultidatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultidatasourceApplication.class, args);
    }
}

