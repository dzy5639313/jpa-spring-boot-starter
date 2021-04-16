package com.liying.boot.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * @author solomon
 * @date 2018/10/15 015 17:31
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class JpaConfig {
    @Bean
    PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}




