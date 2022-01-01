package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            System.out.println("============= start =============");
            
//            // 데이터 저장
//            for (int i = 0; i < 50; i++) {
//                Team team = new Team();
//                team.setName("team"+i);
//                em.persist(team);
//
//                Member member = new Member();
//                member.setUsername("member"+i);
//                member.setAge(i);
//                member.setTeam(team);
//                em.persist(member);
//            }
//            em.flush();
//            em.clear();

            /**
             * 페이징 쿼리 API
             * JPA는 페이징을 다음 두 API로 추상화
             * setFirstResult(int startPosition) : 조회 시작 위치 (0부터 시작)
             * setMaxResults(int maxResult) : 조회할 데이터 수
             */
            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(5)
                    .getResultList();
            System.out.println("result.size() = " + result.size());
            for (Member member : result) {
                System.out.println("member = " + member); // 0 ~ 10
            }

            /**
             *
             */

            System.out.println("=============  end  ======  =======");
            tx.commit(); // 정상이면 commit (DB에 쿼리 날라감)
        }catch (Exception e){
            tx.rollback(); // 예외처리면 롤백
        } finally {
            em.close(); // 엔티티 매니저 닫음
        }
        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
