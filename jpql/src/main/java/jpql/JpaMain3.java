package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain3 {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("============= start =============");

            Team team = new Team();
            team.setName("teamB");
            em.persist(team);

            Member member = new Member();
            member.setUsername("memberB");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);

            /**
             * JPQL 타입 표현
             * 문자 : 'HELLO', 'She''s'
             * 숫자 : 10L(Long), 10D(Double), 10F(Float)
             * Boolean : TURE, FALSE
             * ENUM : jpql.MemberType.ADMIN (패키지명 포함)
             * 엔티티 타입 : TYPE(m) = Member (상속 관계에서 사용)
             */
            // ENUM 활용
            List<Object[]> result = em.createQuery("select m.username, 'HELLO', true from Member m " +
                            "where m.type = :type")
                    .setParameter("type", MemberType.ADMIN)
                    .getResultList();
            for (Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }
            // 엔티티 상속관계 타입
            // em.createQuery("select i from Item i where type(i) = BOOK", Item.class).getResultList();

            /**
             * JQPL 기타 ( SQL과 문법이 같은식 )
             * EXISTS, IN
             * AND, OR, NOT
             * =, >, >=, <, <=, <>
             * BETWEEN, LIKE, IS NULL
             */
            List<Object[]> ob1 = em.createQuery("select m.username, 'SHE''is', true from Member m where m.username is not null")
                    .getResultList();
            List<Object[]> ob2 = em.createQuery("select m.username, 'SHE''is', true from Member m where m.age between 0 and 10")
                    .getResultList();

            /**
             * 조건식 - CASE 식
             * 기본 CASE 식
             * - select case when m.age <= 10 then '학생요금' when m.age >= 60 then '경로요금' else '일반요금 end from Member m
             * 단순 CASE 식
             * - select case t.name when '팀A' then '인센티브110%' when '팀B' then '인센티브120%' else '인센티브105%' end from Team t
             * COALESCE : 하나씩 조회해서 null이 아니면 반환
             * - 사용자 이름이 없으면 이름 없는 회원을 반환
             * - select coalesce(m.username, '이름없는회원') from Member m
             * NULLIF : 두값이 같으면 null 반환, 다르면 첫번째 값 반환
             * - 사용자 이름이 '관리자'면 null을 반환하고 나머지는 본인의 이름을 반환
             * - select nullif(m.username, '관리자') from Member m
             */
            // CASE 식
            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금'" +
                            "     when m.age >= 60 then '경로요금'" +
                            "     else '일반요금' " +
                            "end " +
                    "from Member m";
            List<String> result1 = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : result1) {
                System.out.println("s = " + s);
            }
            // COALESCE 식
            String query2 = "select coalesce(m.username, '이름없는회원') as username from Member m";
            List<String> result2 = em.createQuery(query2, String.class)
                    .getResultList();
            for (String s2 : result2) {
                System.out.println("s = " + s2);
            }
            // NULLIF 식
            String query3 = "select nullif(m.username, 'memberB') from Member m";
            List<String> result3 = em.createQuery(query3, String.class)
                    .getResultList();
            for (String s3 : result3) {
                System.out.println("s = " + s3);
            }

            System.out.println("=============  end  ======  =======");
            tx.commit(); // 정상이면 commit (DB에 쿼리 날라감)
        } catch (Exception e) {
            tx.rollback(); // 예외처리면 롤백
        } finally {
            em.close(); // 엔티티 매니저 닫음
        }
        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
