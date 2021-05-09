package com.brian.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
@Component
public class BrianEnvironment implements CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    public void run(String... args) throws Exception {
        String serviceVersion = environment.getProperty("project.version");
        log.info("===== service version: {} =====", serviceVersion);

        MutablePropertySources propertySources = environment.getPropertySources();
        OriginTrackedMapPropertySource propertySource =  (OriginTrackedMapPropertySource) propertySources.get("applicationConfig: [classpath:/application.properties]");
        Stream.of(propertySource.getPropertyNames()).forEach(name -> log.info(">>>>> application property name:{}", name)); 
    }


}
