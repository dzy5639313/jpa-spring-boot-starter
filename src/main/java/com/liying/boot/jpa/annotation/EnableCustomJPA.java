package com.liying.boot.jpa.annotation;

import com.liying.boot.jpa.BaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.*;

/**
 * 开启支持自定义jpa
 * @author solomon
 * @date 2020-07-15 10:18:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableAutoConfiguration
@EntityScan
@EnableJpaRepositories
public @interface EnableCustomJPA {
    /**
     * Entity 包扫描路径, e.g. com.liying.**.model
     * @author solomon
     * @date 2020-07-15 10:46:47
     * @return java.lang.String[]
     */
    @AliasFor(annotation = EntityScan.class, attribute = "basePackages")
    String[] entityPackages();

    /**
     * Repositories 包扫描路径, e.g. com.liying.**.repository
     * @author solomon
     * @date 2020-07-15 10:47:07
     * @return java.lang.String[]
     */
    @AliasFor(annotation = EnableJpaRepositories.class, attribute = "basePackages")
    String[] repositoryPackages();

    /**
     * 自定义的repository工厂bean对象
     * @author solomon
     * @date 2020-07-15 13:50:59
     * @return java.lang.Class<?>
     */
    @AliasFor(annotation = EnableJpaRepositories.class, attribute = "repositoryFactoryBeanClass")
    Class<?> repositoryFactoryBeanClass() default BaseRepositoryFactoryBean.class;
}
