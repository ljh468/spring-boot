package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
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

            // 데이터 저장
            Team team = new Team();
            team.setName("team1");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(20);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            /**
             * JPQL 객체 지향 쿼리 문법
             * EX:) select m from Member as m where m.age > 18
             * 엔티티와 속성은 대소문자 구분 O (Member, age)
             * JPQL 키워드는 대소문자 구분 X (SELECT, FROM, where)
             * 엔티티 이름 사용, 테이블 이름이 아님 (Memeber)
             * 별칭은 필수 (m) (as는 생략 가능)
             */

            /**
             * 집합과 정렬
             * select
             *      count(m),   // 회원수
             *      sum(m.age), // 나이 합
             *      avg(m.age), // 평균 나이
             *      max(m.age), // 최대 나이
             *      min(m.age)  // 최소 나이
             * from Member m
             * 
             * GROUP BY, HAVING, ORDER BY 그대로
             */

            /**
             * TypeQuery : 반환 타입이 명확할 때 사용
             * Query : 반환 타입이 명확하지 않을 때 사용
             */
            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            TypedQuery<Integer> query2 = em.createQuery("select m.age from Member m where m.username = :username", Integer.class);
            query2.setParameter("username", "member1");
            Query query3 = em.createQuery("select m.username, m.age from Member m"); // name은 String, age는 int 이기때문에 반환타입이 없음

            /**
             * 결과 조회 API
             * query.getResultList() :  결과가 하나 이상일 때, 리스트 반환 (결과가 없으면 빈 리스트 반환)
             * query.getSingleResult() : 결과가 정확히 하나, 단일 객체 반환
             * - 결과가 없으면 NoResultException, 둘이상이면 NonUniqueResultException
             */
            // 결과가 하나 이상
            List<Member> resultList = query1.getResultList();
            for (Member member1 : resultList) {
                System.out.println("resultList = " + member1.getUsername());
            }
            // 결과가 정확히 하나 (결과가 없어도 Exception이 떠서 잘 안쓰임)
            // Spring Data JPA -> null처리 해주는 로직이 있음
            Integer singleResult = query2.getSingleResult();
            System.out.println("singleResult = " + singleResult);

            /**
             * 이름기준, 위치기준(권장 X) 지원 - 파라미터 바인딩
             */
            List<Member> result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getResultList();
            for (Member m : result) {
                System.out.println("m.getTeam().getName() = " + m.getTeam().getName());
                System.out.println("m.getUsername() = " + m.getUsername());
            }



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
