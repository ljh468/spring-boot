package hellojpa;

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
             *
             */
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
