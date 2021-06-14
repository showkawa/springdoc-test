package com.brian.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class BrianEnvironment implements CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment env;

    @Override
    public void run(String... args) throws Exception {
        String serviceVersion = env.getProperty("project.version");
        log.info("===== service version: {} =====", serviceVersion);
        MutablePropertySources ps = env.getPropertySources();
        PropertiesPropertySource mapProp = (PropertiesPropertySource) ps.get("systemProperties");
        String property =  (String) mapProp.getProperty("spring.redis.host");
        log.info("===  spring.redis.host  ===:{}", property); 

        // get the property from application.propertie
        // OriginTrackedMapPropertySource propertySource =  (OriginTrackedMapPropertySource) propertySources.get("applicationConfig: [classpath:/application.properties]");
        log.info("================= beauty border line =================");
    }
}
