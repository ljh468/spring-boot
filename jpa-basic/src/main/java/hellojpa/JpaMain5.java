package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// JPA 정석 코드
public class JpaMain5 {
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

//            Team teamA = new Team();
//            teamA.setName("teamA");
//            em.persist(teamA);
//
//            Team teamB = new Team();
//            teamB.setName("teamA");
//            em.persist(teamB);
//
//            Member3 member1 = new Member3();
//            member1.setName("choi");
//            member1.setTeam(teamA);
//            em.persist(member1);
//
//            Member3 member2 = new Member3();
//            member2.setName("lee");
//            member2.setTeam(teamB);
//            em.persist(member2);
//
//            Locker locker1 = new Locker();
//            locker1.setName("choi");
//            member1.setLocker(locker1);
//            em.persist(locker1);
//
//            Locker locker2 = new Locker();
//            locker2.setName("lee");
//            member1.setLocker(locker2);
//            em.persist(locker2);
//
//
//            em.flush();
//            em.clear(); // 비워줌

            /**
             * 즉시 로딩과 지연 로딩
             */
//            // Team은 fetch = FetchType.LAZY 옵션이 달려서
//            // Member만 DB에서 조회 (Team 객체는 지연로딩)
//            Member3 m = em.find(Member3.class, member1.getId());
//            System.out.println("m = " + m.getTeam().getClass()); // 프록시 객체를 조회
//
//            System.out.println("========");
//            m.getTeam().getName(); // 지연로딩시 실제 Team을 사용하는 시점에 프록시 초기화(강제호출) // DB호출
//            System.out.println("========");

            /**
             * 프록시와 즉시로딩 주의
             * 가급적 지연 로딩만 사용( 특히 실무에서는 LAZY를 다 발라야함 )
             * 즉시로딩을 적용하면 예상하지 못한 SQL이 발생
             * 즉시 로딩은 JPQL에서 N+1 문제를 일으킨다. ( 첫쿼리에서 추가 쿼리 N개가 나옴 )
             * @ManyToOne @OneToOne 은 기본이 즉시로딩 --> LAZY로 설정 해야함
             * @OneToMany @ManyToMany는 기본이 지연로딩
             */
//            // JPQL은 SQL로 변환되어 즉시로딩시 쿼리가 두번나옴
//            em.createQuery("select m from Member3 m join fetch m.team", Member3.class)
//                    .getResultList();
//            // SQL : select * from MEMBER3; -> select * from TEAM where TEAM_ID = XX;

            /**
             * 지연 로딩 활용
             * Member와 Team은 자주 함께 사용 -> 즉시로딩
             * Memver와 Order는 가끔 사용 -> 지연로딩
             * Order와 Product는 자주 함께 사용 -> 즉시 로딩
             *
             * 지연 로딩 활용 - 실무
             * 모든 연관관계에 지연로딩을 사용해라. !!!
             * 실무에서는 즉지로딩을 사용하지 마라. !!
             * JPQL fetch 조인이나, 엔티티 그래프 기능을 사용해라. !!
             * 즉시 로딩은 상상하지 못한 쿼리가 나간다.
             */

            /**
             * 영속성 전이 (cascade = CascadeType.ALL)
             * 특정엔티티를 영속상태로 만들때 연관된 엔티티도 함께 영속상태로 만들고 싶을때 사용
             * EX) 부모엔티티를 저장할때 자식엔티티도 함께 저장
             * 엔티티를 영속화할때 연관된 엔티티도 함께 영속화하는 편리함을 제공할 뿐
             */
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent); // cascade = CascadeType.ALL 자식엔티티도 저장하고 싶을때
//            em.persist(child1);
//            em.persist(child2);
            em.flush();
            em.clear();
            /**
             * 고아객체 (orphanRemoval = true)
             * 부모엔티티와 연관관계가 끊어진 자식엔티티를 자동으로 삭제
             * 참조가 제거된 엔티티는 다른 곳에서 참조하지 않는 고아 객체로 보고 삭제하는 기능
             * !!! 주의 !!!
             * 참조하는 곳이 하나일 때 사용해야함
             * 특정 엔티티가 개인 소유할 때 사용
             */
            Parent findParent = em.find(Parent.class, parent.getId());
//            findParent.getChildList().remove(0); // 컬렉션에서 빠지면 삭제됨
            em.remove(findParent); // 연관된 자식엔티티 자동 제거
//

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
