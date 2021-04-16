package com.liying.boot.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * 批量操作需在配置中加上
 * <pre>
 * spring.jpa.properties.hibernate.jdbc.batch_size=5000
 * spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true
 * spring.jpa.properties.hibernate.order_inserts=true
 * spring.jpa.properties.hibernate.order_updates =true
 * </pre>
 * @author solomon
 * @date 2018/10/16 016 9:27
 */

@NoRepositoryBean
public interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T,ID>, JpaSpecificationExecutor<T> {
    boolean support(String modelType);

    /**
     * 更新
     * @param model
     * @return
     */
    @Transactional
    <S extends T> S update(S model);

    /**
     * 批量更新
     * @param list 
     * @author solomon
     * @date 2020-07-15 15:48:39
     * @return java.lang.Iterable<S>
     */
    @Transactional
    <S extends T> Iterable<S> batchUpdate(Iterable<S> list);

    /**
     * 批量保存
     * @param list
     * @author solomon
     * @date 2020-07-15 15:51:02
     * @return java.util.List<S>
     */
    @Transactional
    <S extends T> Iterable<S> batchSave(Iterable<S> list);
}
