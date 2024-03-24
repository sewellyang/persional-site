package com.sewell.data.server;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sewell.data.server")
public class AutoMapperConfig {
}
