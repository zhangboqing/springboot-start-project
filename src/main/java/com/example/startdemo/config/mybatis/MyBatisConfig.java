package com.example.startdemo.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangboqing
 * @date 2018/8/3
 * <p>
 * Mybatis配置
 */
@Configuration
@MapperScan(basePackages = {"com.example.startdemo.business.dao"}, sqlSessionFactoryRef = "sqlSessionFactory")
//或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的
public class MyBatisConfig {


}
