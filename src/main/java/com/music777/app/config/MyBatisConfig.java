package com.music777.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

// 이 클래스는 MyBatis와 관련된 설정을 담당하는 설정 클래스입니다.
@Configuration
// MyBatis 매퍼 인터페이스가 위치한 패키지를 스캔하도록 지정합니다.
@MapperScan(basePackages = "com.music777.app.user.mapper")
public class MyBatisConfig {

    // SqlSessionFactory를 생성하는 Bean을 정의합니다.
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource); // 데이터 소스를 설정합니다.

        // XML 매퍼 설정을 제거합니다. (애노테이션 기반 매퍼를 사용하기 때문)
        // sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));

        return sqlSessionFactoryBean.getObject(); // SqlSessionFactory 객체를 반환합니다.
    }

    // DataSourceTransactionManager를 생성하는 Bean을 정의합니다.
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource); // 데이터 소스를 사용하여 트랜잭션 매니저를 생성하고 반환합니다.
    }
}
