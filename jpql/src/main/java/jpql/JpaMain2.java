package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.metal.MetalMenuBarUI;
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
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(5)
//                    .getResultList();
//            System.out.println("result.size() = " + result.size());
//            for (Member m : result) {
//                System.out.println("member = " + m); // 0 ~ 10
//            }

            /**
             * 조인
             * 내부조인 : select m from Member m [INNER] join m.team t
             * 외부조인 : select m from Member m left [OUTER] join m.team t
             * 세타조인 : select count(m) from Member m, Team t where m.username = t.name
             */
//            // 내부 조인
//            List<Member> resultList = em.createQuery("select m from Member m inner join m.team t where t.name = :teamname", Member.class)
//                    .setParameter("teamname", "team1")
//                    .getResultList();
//            for (Member member0 : resultList) {
//                System.out.println("내부조인 = " + member0);
//            }
//            // 외부 조인
//            List<Member> resultList1 = em.createQuery("select m from Member m left outer join m.team t", Member.class)
//                    .getResultList();
//            for (Member member1 : resultList1) {
//                System.out.println("외부조인 = " + member1);
//            }
//            // 세타 조인
//            List<Member> resultList2 = em.createQuery("select m from Member m, Team t where m.username=t.name", Member.class)
//                    .getResultList();
//            for (Member member2 : resultList2) {
//                System.out.println("세타조인 = " + member2);
//            }
            /**
             * 조인 - ON절
             * 1. 조인 대상 필터링
             * - 회원과 팀을 조인하면서, 팀 이름이 A인 팀만 조인
             * - select m, t from Member m left join m.team t on t.name='A'
             * 2. 연관관계 없는 엔티티 외부 조인 (하이버네이트 5.1부터)
             * - 회원의 이름과 팀의 이름이 같은 대상 외부 조인
             * - select m, t from Memeber m left join Team t on m.username = t.name
             */
//            // 조인 대상 필터링
//            List<Member> resultList3 = em.createQuery("select m from Member m left join m.team t on t.name = 'team5'", Member.class)
//                    .getResultList();
//            System.out.println("조인 대상 필터링 = " + resultList3.size());
//
//            // 연관관계 없는 엔티티 외부 조인
//            String query = "select m from Member m left join Team t on m.username = t.name";
//            List<Member> resultList4 = em.createQuery(query, Member.class)
//                    .getResultList();
//            System.out.println("연관관계 없는 엔티티 외부 조인 = " + resultList4.size());

            /**
             * 서브 쿼리
             * 나이가 평균보다 많은 회원
             * - select m from Member m where m.age > (select avg(m2.age) from Member m2)
             * 한 건이라도 주문한 고객
             * - select m from Member m where (select count(o) from Order o where m = o.member) > 0
             * 
             * 서브 쿼리 지원함수
             * [NOT] EXISTS (서브쿼리) : 서브쿼리에 결과가 존재하면 참
             * {ALL | ANY | SOME} (서브쿼리) : (ALL)은 모두 만족하면 참, (ANY, SOME)은 같은 의미, 조건을 하나라도 만족하면 참
             * [NOT] IN (서브쿼리) : 서브쿼리의 결과 중 하나라도 같은 것이 있으면 참
             *
             * '팀A' 소속인 회원
             * - select m from Member m where exists (select t from m.team t where t.name = '팀A')
             * 전체 상품 각각의 재고보다 주문량이 많은 주문들
             * - select o from Order o where o.orderAmount > ALL(select p.stockAmount from Product p)
             * 어떤 팀이든 팀에 소속된 회원
             * select m from Memeber m where m.team = ANY(select t from Team t)
             * 
             * JPA 서브 쿼리 한계
             * JPA는 where, having 절에서만 서브 쿼리 사용 가능
             * select 절도 가능 (하이버네이트에서 지원)
             * FROM 절의 서브쿼리는 현재 JPQL에서 불가능
             * - 조인으로 풀수 있으면 풀어서 해결
             * - 애플리케이션에서 조작하는 방법으로 해결
             */
////             select 절 서브 쿼리 가능
//            List<Object[]> resultList5 = em.createQuery("select (select avg(m1.age) from Member m1) as avgAge from Member m")
//                    .getResultList();



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
