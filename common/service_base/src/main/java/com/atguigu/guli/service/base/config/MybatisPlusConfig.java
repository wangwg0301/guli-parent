package com.atguigu.guli.service.base.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wxg
 * @create 2021 -07 -01 16:00
 */
@MapperScan("com.atguigu.guli.service.*.mapper")
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {
    /*分页
    * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}

