package com.liying.boot.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Iterator;

@Slf4j
public class BaseRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    private static final int BATCH_SIZE = 500;
    private EntityManager em;
    private final Class<T> domainClass;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.em = entityManager;
    }

    @Override
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

    @Override
    public <S extends T> S update(S model) {
        return em.merge(model);
    }

    @Override
    public <S extends T> Iterable<S> batchUpdate(Iterable<S> list) {
        Iterator<S> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()){
            em.merge(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                em.flush();
                em.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            em.flush();
            em.clear();
        }
        return list;
    }

    @Override
    public <S extends T> Iterable<S> batchSave(Iterable<S> list) {
        Iterator<S> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()){
            em.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                em.flush();
                em.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            em.flush();
            em.clear();
        }
        return list;
    }
}