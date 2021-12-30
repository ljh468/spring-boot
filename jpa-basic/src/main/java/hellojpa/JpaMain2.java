package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// JPA 정석 코드
public class JpaMain2 {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
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
             * 플러시 (변경감지, 수정된 엔티티 쓰기지연SQL저장소에 등록, 쓰기지연SQL저장소의 쿼리를 데이터베이스에 전송)
             * 커밋이나 쿼리를 실행할 때 플러시 (기본값)
             * 영속성 컨텍스트를 비우지 않고 변경내용을 데이터베이스에 동기화
             * 트랜잭션이라는 작업 단위가 중요 -> 커밋 직전에만 동기화 하면됨
             */
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//            // 미리 DB에 반영하거나 쿼리를 보고 싶으면 강제로 flush 호출
//            // 1차캐시는 유지, 쓰기지연SQL저장소의 변경사항이 반영
//            em.flush();

            /**
             * 준영속 상태 (em.detach(entity), em.clear(), em.close()
             * 영속성 컨텍스트에서 엔티티를 분리하는 상태(모두 뺌)
             */
//            Member member = em.find(Member.class, 150L);
//            member.setName("AB");
            // 특정 엔티티만 준영속상태로 전환
//            em.detach(member);
            // 모든 영속성컨텍스트를 모두 제거
//            em.clear();
            // 영속성 컨텍스트를 종료
            // 5.4.1 버전부터 ~ 트랜잭션이 살아있으면 반영되는 것으로 변경됨
//            em.close();

            /**
             * 요구사항 실습
             */
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("JPA");
//            member.setRoleType(RoleType.USER);
//
//            member.setId(2L);
//            member.setName("JPB");
//            member.setRoleType(RoleType.ADMIN);
//
//            em.persist(member);

            /**
             * 기본키 매핑
             */
            Member2 member1 = new Member2();
            // 기본키 ID를 자동으로 생성
            member1.setName("A");

            Member2 member2 = new Member2();
            member2.setName("B");

            Member2 member3 = new Member2();
            member3.setName("C");

            System.out.println("===============");

            // call next value for MEMBER_SEQ로 시퀀스 정보 얻어옴
            // 그다음에 영속성 컨텍스트에 저장
            em.persist(member1); // 1, 51
            em.persist(member2); // MEMORY
            em.persist(member3); // MEMORY
            System.out.println("member1.getId() = " + member1.getId());
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("member3.getId() = " + member3.getId());

            /**
             * Transaction.commit()시에 SQL 쿼리가 DB로 날라감
             */
            System.out.println("===============");
            tx.commit(); // 정상이면 커밋 (이때 DB에 저장됨)

        } catch (Exception e) {
            tx.rollback(); // 예외면 롤백
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }

        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
