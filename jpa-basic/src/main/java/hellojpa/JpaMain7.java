package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

// JPA 정석 코드
public class JpaMain7 {
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
             * JPQL
             * 테이블이 아닌 객체를 대상으로 쿼리하는 객체 지향 쿼리
             * SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X
             * JPQL은 결국 SQL로 변환된다.
             * JPQL은 동적쿼리에 약점이 있다.
             * 
             * QueryDSL
             * 문자가 아닌 자바코드로 JPQL을 작성할 수 있음
             * JPQL 빌더 역할, 컴파일 시점에 문법 오류를 찾을 수 있음
             * 동적쿼리를 해결할 수 있음, 실무 사용 권장
             *
             * JDBC, SpringJdbcTemplate 직접 사용시
             * flush -> commit, query (동작할때 flush가 자동으로 실행됨)
             * 그러나, dbconn.executeQuery("select * from member"); -> JDBC템플릿은 flush가 작동하지 않음
             * Jdbc템플릿을 사용할때는 em.flush(); 를 해줘야함
             * 영속성 컨텍스트를 적절한 시점에 강제로 플러시 필요!!!
             */
            // name이 kim인 회원 모두 조회
            List<Member4> result = em.createQuery("select m from Member4 m where m.name like '%kim%'", Member4.class)
                    .getResultList();
            for (Member4 member4 : result) {
                System.out.println("member4 = " + member4.getName());
            }
            // 네이티브 쿼리
            Member4 member = new Member4();
            member.setName("mamber1");
            em.persist(member);

            List<Member4> result2 = em.createNativeQuery("select * from MEMBER4", Member4.class)
                    .getResultList();
            for (Member4 m : result2) {
                System.out.println("member.getName() = " + m.getName());
            }

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
