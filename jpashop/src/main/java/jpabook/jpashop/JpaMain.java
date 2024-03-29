package jpabook.jpashop;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Sms;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

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
             * 현재방식은 객체 설계를 테이블 설계에 맞춘 방식
             * 테이블의 외래키를 객체에 그대로 가져옴
             * 객체 그래프 탐색이 불가능
             * 참조가 없으므로 UML도 잘못됨
             */
//            Order order = em.find(Order.class, 1L);
//            Member findMember = order.getMember();

            /**
             * 연관관계 매핑 (참조를 사용하도록 변경)
             */
//            Order order = new Order();
//            em.persist(order);
////            order.addOrderItem(new OrderItem());
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            em.persist(orderItem);


            /**
             * 상속매핑, 슈퍼클래스 실습
             * 상속매핑은 Join전략
             * @MappedSuperclass를 사용한 등록일, 수정일, 등록자, 수정자 공통속성 상속
             */
//            Book book = new Book();
//            book.setName("JPA");
//            book.setAuthor("김영한");
//
//            em.persist(book);

            /**
             * 값타입 컬렉션 실습
             */

            /**
             * TEST SMS 1만데이터
             */


            LocalDateTime now = LocalDateTime.now();

            for(int i = 0; i < 100000; i++){
                Sms sms = new Sms();

                sms.setAuthcode("000" + i);
                sms.setBirth("90" + i);
                sms.setMobile("01058" + i);

                now = now.plusMinutes(1);
                sms.setRegdt(now);

                em.persist(sms);
            }
            em.flush();
            em.clear();

            for (int i = 0; i < 50; i++) {
                Member team = new Member();
                team.setName("team"+i);
                em.persist(team);

            }
            em.flush();
            em.clear();



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
