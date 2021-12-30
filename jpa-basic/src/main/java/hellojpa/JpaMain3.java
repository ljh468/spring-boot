package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

// JPA 정석 코드
public class JpaMain3 {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("===============");
            /**
             * 객체를 테이블에 맞추어 모델링
             */
//            // 팀저장
//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team); // 영속상태가 되면 기본키 세팅됨
//
//            // 회원 저장
//            Member3 member = new Member3();
//            member.setName("MemberA");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            // 멤버아이디로 소속된 팀을 가져오려면 (객체 지향적이지 않다.)
//            Member3 findMember = em.find(Member3.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            /**
             * 단방향 연관관계
             */
//            // 연관관계를 맺고 팀 저장
//            Team team = new Team();
//            team.setName("TeamB");
//            em.persist(team); // 영속상태가 되면 기본키 세팅됨
//
//            // 회원 저장
//            Member3 member = new Member3();
//            member.setName("MemberB" );
//            member.setTeam(team); // 팀을 그대로 넣어줌
//            em.persist(member);
//
//            // DB에서 쿼리를 가져오려면 영속성컨텍스트 비워줌
////            em.flush();
////            em.clear();
//
//            // 조회 ( 멤버아이디로 소속된 팀을 가져오려면 )
//            Member3 findMember = em.find(Member3.class, member.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam.getName() = " + findTeam.getName());
//
//            // member 아이디로 소속팀을 수정하고 싶다면 (연관관계 수정)
//            // 새로운팀 C
//            Member3 findMember2 = em.find(Member3.class, 2L);
//            System.out.println("findMember2.getId() = " + findMember2.getId());
//            System.out.println("findMember2.getName() = " + findMember2.getName());
//
//            Team teamC = new Team();
//            teamC.setName("TeamC");
//            em.persist(teamC); // 영속상태가 되면 기본키 세팅됨
//
//            // 회원1에 새로운 팀 C 설정 (객체에다가 수정)
//            findMember2.setTeam(teamC);

            /**
             * 양방향 연관관계와 연관관계의 주인
             * 양쪽을 참조에서 둘다 활용할 수 있음
             */
//            Member3 findMember = em.find(Member3.class, 2L);
//            List<Member3> members = findMember.getTeam().getMembers(); // 멤버에서 팀으로 -> 팀에서 멤버를 가져옴
//
//            for (Member3 m :  members){
//                System.out.println("m = " + m.getName());
//            }

            /**
             * 양방향 매핑시 주의해야할 사항
             * 양방향 매핑시 연관관계의 주인에 값을 입력해야 한다.
             *  - em.persist까지는 영속성컨텍스트에 순수 객체로 들어가있음 (DB로 들어가기 전까지)
             *  - 순수 객체 상태를 고려해서 항상 양쪽다 값을 입력해야한다, 또는 DB로 뿌리고 가져와야한다.
             *  - 연관관계 편의메서드를 활용 (주인 객체의 연관객체의 set메서드에 team.getMembers().add(this)를 넣어주면 됨)
             */
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // 영속상태가 되면 기본키 세팅됨

            // 회원 저장
            Member3 member = new Member3();
            member.setName("MemberA");
            member.changeTeam(team); // **
            em.persist(member);

//            team.getMembers().add(member); // ** 팀의 Members에 member를 넣어줘야함
//            em.flush(); // 또는 DB로 값을 보내고 영속성컨텍스트를 비워야함
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
            List<Member3> members = findTeam.getMembers();

            System.out.println("===============");
            for (Member3 m : members) {
                System.out.println("m.getName() = " + m.getName());
            }
            System.out.println("===============");

            System.out.println("===============");
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
