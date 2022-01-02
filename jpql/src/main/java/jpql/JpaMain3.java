package jpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("memberA");
//            member.setAge(20);
//            member.setTeam(team);
//            member.setType(MemberType.ADMIN);
//            em.persist(member);
//
//            em.flush();
//            em.clear();
            /**
             * JPQL 타입 표현
             * 문자 : 'HELLO', 'She''s'
             * 숫자 : 10L(Long), 10D(Double), 10F(Float)
             * Boolean : TURE, FALSE
             * ENUM : jpql.MemberType.ADMIN (패키지명 포함)
             * 엔티티 타입 : TYPE(m) = Member (상속 관계에서 사용)
             */
//            // ENUM 활용
//            List<Object[]> result = em.createQuery("select m.username, 'HELLO', true from Member m " +
//                            "where m.type = :type")
//                    .setParameter("type", MemberType.ADMIN)
//                    .getResultList();
//            for (Object[] objects : result) {
//                System.out.println("objects[0] = " + objects[0]);
//                System.out.println("objects[1] = " + objects[1]);
//                System.out.println("objects[2] = " + objects[2]);
//            }
//            // 엔티티 상속관계 타입
//            // em.createQuery("select i from Item i where type(i) = BOOK", Item.class).getResultList();

            /**
             * JQPL 기타 ( SQL과 문법이 같은식 )
             * EXISTS, IN
             * AND, OR, NOT
             * =, >, >=, <, <=, <>
             * BETWEEN, LIKE, IS NULL
             */
//            List<Object[]> ob1 = em.createQuery("select m.username, 'SHE''is', true from Member m where m.username is not null")
//                    .getResultList();
//            List<Object[]> ob2 = em.createQuery("select m.username, 'SHE''is', true from Member m where m.age between 0 and 10")
//                    .getResultList();

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
//            // CASE 식
//            String query =
//                    "select " +
//                            "case when m.age <= 10 then '학생요금'" +
//                            "     when m.age >= 60 then '경로요금'" +
//                            "     else '일반요금' " +
//                            "end " +
//                    "from Member m";
//            List<String> result1 = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result1) {
//                System.out.println("s = " + s);
//            }
//            // COALESCE 식 (사용자 이름이 없으면 이름 없는 회원을 반환)
//            String query2 = "select coalesce(m.username, '이름없는회원') from Member m";
//            List<String> result2 = em.createQuery(query2, String.class)
//                    .getResultList();
//            for (String s2 : result2) {
//                System.out.println("COALESCE 식 = " + s2);
//            }
//            // NULLIF 식 (사용자 이름이 '관리자'면 null을 반환하고 나머지는 본인의 이름을 반환)
//            String query3 = "select nullif(m.username, '관리자') from Member m";
//            List<String> result3 = em.createQuery(query3, String.class)
//                    .getResultList();
//            for (String s3 : result3) {
//                System.out.println("NULLIF 식 = " + s3);
//            }

            /**
             * JPQL 기본 함수
             * CONCAT (문자 더하기) : "update Member m set m.name = concat(m.name, 'go')"
             * SUBSTRING (문자 잘라내기) : select substring(m.username, 2, 3) from Member m
             * TRIM (공백제거)
             * LOWER, UPPER (소문자, 대문자)
             * LENGTH (문자의 길이)
             * LOCATE : "select locate('de', 'abcdefg') from Member m" 는 4반환 (1부터 시작)
             * ABS (절대값), SQRT (제곱근), MOD(나머지)
             * SIZE (컬렉션의 크기), INDEX (위치) == JPA용도
             */
//            // CONCAT 함수
//            String basic_query = "select concat('a', 'b') from Member m";
//            // String basic_query = "select 'a' || 'b' from Member m";
//            List<String> basic_result = em.createQuery(basic_query, String.class)
//                    .getResultList();
//            for (String basic : basic_result) {
//                System.out.println("CONCAT 함수 = " + basic);
//            }
//            // SUBSTRING 함수 (2번째 부터 3개를 자름)
//            String basic_query1 = "select substring(m.username, 2, 3) from Member m";
//            List<String> basic_result1 = em.createQuery(basic_query1, String.class)
//                    .getResultList();
//            for (String basic : basic_result1) {
//                System.out.println("SUBSTRING 함수 = " + basic);
//            }
//            // LOCATE 함수 ('abcdefg' 문자열에서 'de'는 몇번째 문자인지)
//            String basic_query2 = "select locate('de', 'abcdefg') from Member m";
//            List<Integer> basic_result2 = em.createQuery(basic_query2, Integer.class)
//                    .getResultList();
//            for (Integer basic : basic_result2) {
//                System.out.println("LOCATE 함수 = " + basic);
//            }
//            // SIZE 함수
//            String basic_query3 = "select size(t.members) from Team t";
////            String basic_query3 = "select count(m) from Member m";
//            List<Object[]> basic_result3 = em.createQuery(basic_query3)
//                    .getResultList();
//            System.out.println("basic_result3 = " + basic_result3);

            /**
             * 사용자 정의 함수 호출
             * 하이버네이트는 사용전 방언에 추가해야 한다.
             * 사용하는 DB방언을 상속받고, 사용자 정의 함수를 등록한다.
             * "select function('group_concat', m.username) from Member m"
             */

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
