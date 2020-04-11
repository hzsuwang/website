package com.yyq.iterror;
import com.yyq.iterror.common.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.yyq.iterror.controller","com.yyq.iterror.config","com.yyq.iterror.service","com.yyq.iterror.dao"})
@EnableCaching
@EnableConfigurationProperties
public class IterrorApplication extends BaseApplication {
    public static void main(String[] args) {
        System.out.println("hello");
        SpringApplication.run(IterrorApplication.class, args);
    }

}
