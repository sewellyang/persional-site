package com.sewell.web.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.sewell.web.user.mapper")
@SpringBootApplication
public class SiteUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(SiteUserApplication.class, args);
    }
}