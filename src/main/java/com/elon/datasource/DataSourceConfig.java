package com.elon.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 配置数据源
 *
 * @author elon
 */
@Configuration
@MapperScan(basePackages = "com.elon.mapper", sqlSessionFactoryRef = "mySqlSessionFactory")
public class DataSourceConfig {

    @Bean(name = "myDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource buildDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory buildSqlSessionFactory(@Qualifier("myDataSource") DataSource dataSource,
                                                    org.apache.ibatis.session.Configuration config) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setConfiguration(config);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mapper/*.xml"));
        return factory.getObject();
    }

    @Bean
    @ConfigurationProperties("mybatis.configuration")
    public org.apache.ibatis.session.Configuration globalConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    @Bean(name = "myTransactionManager")
    public DataSourceTransactionManager buildTransactionManager(@Qualifier("myDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
