package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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
            // 생성
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloA");
//            em.persist(member);

            // 수정 (엔티티 클래스, PK)
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            // 삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

            // 수정 ( 컬렉션 처럼 set만 해주면 됨 )
            // JPA를 통해서 값을 가져오면 JPA가 관리, JPA가 트랜잭션을 커밋할때 확인하고 쿼리를 날림
//            Member findMember = em.find(Member.class, 2L);
//            findMember.setName("HelloB");
            
            // JPQL은 객체 지향 쿼리 언어 ( SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X )
            // SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
            // JPQL로 전체 회원 검색 ( 테이블이 아닌 Member객체를 대상으로 가져옴 )
            List<Member> result = em.createQuery("select m from Member m", Member.class) // 쿼리 , 타입 파라미터
                    .setFirstResult(0) // 0번부터
                    .setMaxResults(10) // 10번까지 페이징
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.getName = " + member.getName());
            }

            tx.commit(); // 정상이면 커밋
        } catch (Exception e){
            tx.rollback(); // 예외면 롤백
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }
        
        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
