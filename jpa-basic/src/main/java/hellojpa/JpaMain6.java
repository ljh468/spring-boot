package hellojpa;

import org.hibernate.type.LocalDateTimeType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

// JPA 정석 코드
public class JpaMain6 {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("======= start ========");

            /**
             * 임베디드 값타입의 활용
             * 임베디드 타입은 엔티티의 값일 뿐이다.
             * 임베디드 타입을 사용하기 전과 후에 매핑하는 테이블은 같다.
             * 객체과 테이블을 아주 세밀하게 매핑하는 것이 가능
             * 잘 설계한 ORM 애플리케이션은 매핑한 테이블의 수보다 클래스의 수가 더 많음
             */
            Member4 member = new Member4();
            member.setName("hello");
            member.setHomeAddress(new Address("city", "street","zipcode"));
            member.setWorkAddress(new Address("work", "work", "work"));

            LocalDateTime dateTime = LocalDateTime.now();
            member.setWorkPeriod(new Period(dateTime, dateTime));

            em.persist(member);

            System.out.println("=======  end  ========");
            tx.commit(); // 정상이면 커밋 (이때 DB에 저장됨)

        } catch (
                Exception e) {
            tx.rollback(); // 예외면 롤백
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }

        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
