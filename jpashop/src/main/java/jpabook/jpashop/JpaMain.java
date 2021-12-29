package jpabook.jpashop;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// JPA 정석 코드
public class JpaMain {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("===============");

            /**
             * 데이터 중심 설계의 문제점
             * 현재방식은 객체 설꼐를 테이블 설계에 맞춘 방식
             * 테이블의 외래키를 객체에 그대로 가져옴
             * 객체 그래프 탐색이 불가능
             * 참조가 없으므로 UML도 잘못됨
             */

            Order order = em.find(Order.class, 1L);
            Member findMember = order.getMember();




            System.out.println("===============");
            tx.commit(); // 정상이면 커밋 (이때 DB에 저장됨)

        } catch (Exception e){
            tx. rollback(); // 예외면 롤백
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }

        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
