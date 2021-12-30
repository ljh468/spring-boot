package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// JPA 정석 코드
public class JpaMain4 {
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
             * 상속 매핑
             * 1. 조인전략
             * 2. 단일테이블 전략 (모든속성을 가지고 DTYPE으로 구분)
             * 3. 구현클래스 마다 테이블전략 (부모 테이블을 없애고 공통속성을 가짐)
             */
//            // 조인 전략
//            Movie movie = new Movie();
//            movie.setDirector("cccc");
//            movie.setActor("dddd");
//            movie.setName("스파이더맨");
//            movie.setPrice(10000);
//                               // 상속만 받고 Entity 설정만하면 모든속성이 들어가는 단일테이블로 생성됨
//                               // 추가로 @Inheritance(strategy = InheritanceType.JOINED)를 부모객체에 설정하면 조인전략으로 생성됨
//            em.persist(movie); // 조인전략은 movie와 item테이블에 각각 데이터가 들어감
//
//            em.flush();
//            em.clear();
//
//            Movie FindMovie = em.find(Movie.class, movie.getId());
//            System.out.println("FindMovie = " + FindMovie);

            /**
             * 슈퍼클래스 (매핑 정보 상속)
             * BaseEntity를 이용한 공통 속성 관리
             */
//            Member3 member = new Member3();
//            member.setName("user1");
//            member.setCreateBy("kim");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();

            /**
             * 프록시와 연관관계 관리
             */
            Member3 member = new Member3();
            member.setName("lee");
            em.persist(member);

            Locker locker = new Locker();
            locker.setName("lee");
            member.setLocker(locker);
            em.persist(locker);

            Member3 member2 = new Member3();
            member2.setName("kim");
            em.persist(member2);

            Locker locker2 = new Locker();
            locker2.setName("kim");
            member2.setLocker(locker2);
            em.persist(locker2);

            em.flush();
            em.clear(); // 비워줌

            /**
             * 프록시와 실제객체의 동일성
             */
//            Member3 m1 = em.find(Member3.class, member2.getId());
//            Member3 m2 = em.getReference(Member3.class, member2.getId()); // 프록시 생성
//
//
//            System.out.println("m1.getClass() = " + m1.getClass()); // 같은 트랜잭션안에서 영속성컨텍스트의 값이 있으면 같은 값이 나옴
//            System.out.println("m2.getClass() = " + m2.getClass()); // ** 동일성 보장 **
//
//            System.out.println("a == a : " + (m1 == m2)); // 같은 영속성컨텍스트에서 가져오면 항상 True가 나와야함
////            logic(m1, m2); // 프록시인지 실제엔티티인지 모르기때문에 타입비교는(==) 하면 안된다.

            /**
             * 준영속 상태시 프록시를 초기화하면 문제 발생
             */
//            Member3 refMember = em.getReference(Member3.class, member2.getId()); // 프록시 생성
//            System.out.println("refMember = " + refMember.getClass()); // Proxy
//
//            em.detach(refMember); // 영속성을 분리하면? (관리가 안됨)
//            em.close(); // could not initialize proxy [hellojpa.Member3#3] - no Session 영속성 컨텍스트 없음
//
//            refMember.getName(); // DB에서 실제 객체를 조회, 프록시 초기화
//            // 영속성 컨텍스트를 관리하지 않기 때문에 !! 더이상 영속성 컨텍스트의 도움을 받지 못함

            /**
             * 프록시 확인
             */
            Member3 refMember2 = em.getReference(Member3.class, member2.getId()); // 프록시 생성

            // 프록시 클래스 확인
            System.out.println("refMember = " + refMember2.getClass().getName()); // Proxy
            // 프록시 강제 초기화
            Hibernate.initialize(refMember2);

            refMember2.getName(); // 프록시 초기화(강제 호출)
            // 프록시 인스턴스의 초기화 여부 확인
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember2));

            /**
             * em.find() : DB에서 실제 객체를 조회
             */
//            Member3 findMember = em.find(Member3.class, member.getId());
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            /**
             * em.getReference() : 프록시(가짜) 엔티티 객체 조회
             */
//            Member3 findMember2 = em.getReference(Member3.class, member.getId());
//            System.out.println("findMember2 = " + findMember2.getClass()); // 프록시 클래스 주소가 나옴
//            System.out.println("findMember.getId() = " + findMember2.getId()); // id는 프록시(가짜)에서 가져옴
//            System.out.println("findMember.getName() = " + findMember2.getName()); // name은 DB에서 실제 객체를 가져옴


            
            System.out.println("=======  end  ========");
            tx.commit(); // 정상이면 커밋 (이때 DB에 저장됨)

        } catch (
                Exception e) {
            tx.rollback(); // 예외면 롤백
            System.out.println("e = " + e);
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }

        emf.close(); // 엔티티 매니저 팩토리 닫음
    }

    private static void logic(Member3 m1, Member3 m2) {
//        System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass())); // 비교 안됨
        // 타입 비교시에는 instanceof 사용
        System.out.println("m1 == m2 " + (m1 instanceof Member3));
        System.out.println("m1 == m2 " + (m2 instanceof Member3));
    }
}
