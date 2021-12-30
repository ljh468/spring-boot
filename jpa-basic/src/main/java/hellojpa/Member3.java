package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member3 extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;
    private int age;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 데이터베이스 관점의 방향
    //    @ManyToOne(fetch = FetchType.LAZY) // FETCH LAZY옵션은 쿼리가 분리되서 나옴(지연로딩 전략)
    // 연관관계의 주인 ( 외래키가 있는곳이 연관관계의 주인이다 )
    // member는 N, team은 1 (N : 1)

//    @ManyToOne(fetch = FetchType.EAGER) // 즉시로딩 : Member클래스와 Team클래스를 DB에서 모두 실제 객체를 조회
//    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩 : 프록시 객체로 조회 (Member클래스만 DB에서 조회하고 Team은 프록시 조회)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
    
    // 일대일 매핑
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    // 다대다 매핑
//    // DB는 연결 테이블을 생성함
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    // 연결테이블로 인한 대다대 풀이
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public List<MemberProduct> getMemberProducts() {
        return memberProducts;
    }

    public void setMemberProducts(List<MemberProduct> memberProducts) {
        this.memberProducts = memberProducts;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public Long getTeamId() {
//        return teamId;
//    }
//
//    public void setTeamId(Long teamId) {
//        this.teamId = teamId;
//    }


//    @Override
//    public String toString() {
//        return "Member3{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", team=" + team +
//                '}';
//    }
}
