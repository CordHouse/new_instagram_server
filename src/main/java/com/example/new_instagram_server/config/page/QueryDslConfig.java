package com.example.new_instagram_server.config.page;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {
    /**
     * EntityManager 를 빈으로 주입할때 사용하는 어노테이션
     * 스프링에서 영속성을 관리하기 위해 스프링 시작시 EntityManager 을 만들어 빈으로 등록
     * EntityManagerFactory 에서 새로운 EntityManager 를 생성
     * 혹은 Transaction 에 의해 기존에 생성된 EntityManager 를 반환
     *
     * 사용하는 이유
     * EntityManager 를 사용할 때 주의해야 할 점은 여러 쓰레드가 동시에 접근하면서 동시성 문제가 발생하여
     * 쓰레드 간에 EntityManager 를 공유해서는 안된다.
     * -> 일반적으로 스프링은 싱글톤 기반으로 동작하기 때문에 빈은 모든 쓰레드가 공유한다.
     * -> @PersistenceContext 로 주입받으면 동시성 문제가 발생하지 않는다.
     *  -> 이유 : 스프링 컨테이너가 초기화되면서 주입받은 EntityManager 를 Proxy 가 감싼다.
     *  -> 그리고 EntityManager 호출 시 마다 Proxy 를 통해 EntityManager 를 생성하여 Thread-Safe를 보장한다.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Bean // Bean 생성
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
