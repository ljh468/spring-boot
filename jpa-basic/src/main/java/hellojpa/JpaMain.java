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
            /**
             * 생성, 저장
             */
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloA");
//            em.persist(member);

            /**
             * 수정 (엔티티 클래스, PK)
             */
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            /**
             * 삭제
             */
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);

            /**
             * 수정 ( 컬렉션 처럼 set만 해주면 됨 )
             * JPA를 통해서 값을 가져오면 JPA가 관리, JPA가 트랜잭션을 커밋할때 확인하고 쿼리를 날림
             */
//            Member findMember = em.find(Member.class, 2L);
//            findMember.setName("HelloB");

            /**
             * JPQL은 객체 지향 쿼리 언어 ( SQL을 추상화해서 특정 데이터베이스 SQL에 의존 X )
             * SELECT, FROM, WHERE, GROUP BY, HAVING, JOIN 지원
             * JPQL로 전체 회원 검색 ( 테이블이 아닌 Member객체를 대상으로 가져옴 )
             */
//            List<Member> result = em.createQuery("select m from Member m", Member.class) // 쿼리 , 타입 파라미터
//                    .setFirstResult(0) // 0번부터
//                    .setMaxResults(10) // 10번까지 페이징
//                    .getResultList();
//
//            for (Member member : result) {
//                System.out.println("member.getName = " + member.getName());
//            }

///////////////////////////// 영속성 컨텍스트의 생성주기 /////////////////////////////
            /**
             * 비영속(new)
             */
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");

            /**
             * 영속(managed)
             * 영속성 컨텍스트에서 member객체를 관리하겠다는 표시 (영속 상태)
             */
//            System.out.println("=== BEFORE");
//            em.persist(member); // DB에 저장되지 않음
//            System.out.println("=== AFTER");

            /**
             * 준영속(detached)
             * 영속성 컨텍스트에서 분리
             */
//            em.detach(member);

            /**
             * 삭제(removed)
             * 객체를 삭제한 상태
             */
//            em.remove(member);
 
///////////////////////// 영속성 컨텍스트의 1차캐시 /////////////////////////////
            /**
             * 1차캐시에서 불러옴
             */
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
            
            /**
             * 영속성 컨텍스트에 없을때 1번째는 DB에서 조회, 2번째는 1차캐시에서 조회
             */
//            Member findMember1 = em.find(Member.class, 101L);
//            System.out.println("findMember1.getName() = " + findMember1.getName());
//            Member findMember2 = em.find(Member.class, 101L);
//            System.out.println("findMember1.getName() = " + findMember1.getName());
            
///////////////////////// 영속성 컨텍스트의 동일성 보장 /////////////////////////////
            /**
             * 영속성 엔티티의 동일성 보장
             * 같은 트랜잭션안에서 비교시 마치 같은 객체에서 꺼낸것 처럼 동일성을 보장함
             */
//            System.out.println("findMember1 == findMember2 : ?" + (findMember1 == findMember2)); // 동일성 비교 true

///////////////////////// 영속성 컨텍스트의 쓰기 지연 SQL 저장소 /////////////////////////////
            /**
             * 트랜잭션을 지원하는 쓰기 지연 (쓰기 지연 SQL 저장소에서 쿼리를 flush)
             * 커밋하는 순간 데이터베이스에 쿼리를 보냄
             */

            /**
             * 1차캐시에도 저장, 쓰기지연 SQL저장소에도 저장됨
             */
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//            em.persist(member1);
//            em.persist(member2);
            // 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
            // 버퍼를 사용하여 성능을 높이려면 <property name="hibernate.jdbc.batch_size" value="10"/> 옵션 부여


            /**
             * 엔티티 수정
             * commit시 맨처음 flush()가 호출이되면서 1차캐시의 엔티티와 스냅샷(최초시점)을 비교
             * 변경이 있으면 update SQL을 쓰기지연SQL저장소에 생성
             * flush() 데이터베이스에 반영 (자동으로 변경감지)
             */
//            Member member = em.find(Member.class, 150L); // 조회
//            member.setName("ZZZZZ"); // 수정

            /**
             * 엔티티 삭제
             */
//            Member memberA = em.find(Member.class, 150L); // 삭제 대상 엔티티 조회
//            em.remove(memberA); // 엔티티 삭제

            /**
             * Transaction.commit()시에 SQL 쿼리가 DB로 날라감
             */
            System.out.println("=============");
            tx.commit(); // 정상이면 커밋 (이때 DB에 저장됨)

        } catch (Exception e){
            tx.rollback(); // 예외면 롤백
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }
        
        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
