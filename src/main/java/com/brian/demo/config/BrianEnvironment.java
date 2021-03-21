package com.brian.demo.config;

import com.brian.demo.controller.CheckController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Map;
import java.util.Properties;

@Component
public class BrianEnvironment implements CommandLineRunner, ResourceLoaderAware,
        BeanFactoryAware, BeanClassLoaderAware {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    private ResourceLoader resourceLoader;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(environment);
        MutablePropertySources propertySources1 = environment.getPropertySources();
        String property = environment.getProperty("project.version");
        Resource resource = resourceLoader.getResource("file:pom.xml");
        System.out.println(resource);
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (Exception e) {
            Throwable mostSpecificCause = NestedExceptionUtils.getMostSpecificCause(e);
            System.out.println(mostSpecificCause.getMessage());
            Throwable rootCause = NestedExceptionUtils.getRootCause(e);
            System.out.println(rootCause.getMessage());
        }

        CheckController bean = this.beanFactory.getBean(CheckController.class);
        System.out.println(bean);

        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();

        Map<String, Object> systemProperties = configurableEnvironment.getSystemProperties();

        Map<String, Object> systemEnvironment = configurableEnvironment.getSystemEnvironment();

        String[] defaultProfiles = configurableEnvironment.getDefaultProfiles();


    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


    private ClassLoader getClassLoader() {
        if (this.resourceLoader != null) {
            return this.resourceLoader.getClassLoader();
        }
        return ClassUtils.getDefaultClassLoader();
    }


}
