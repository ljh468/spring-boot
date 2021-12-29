package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    // 팀에서 멤버로 ( 1 : N )
    // 양방향에 매핑을 위해 team을 연관 매핑함
    // 연관관계의 주인이 아님 , 조회만 가능
    @OneToMany(mappedBy = "team") // mappedBy : 연관관계의 주인이 자신을 team이라고 참조하고있다.
    private List<Member3> members = new ArrayList<>();

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

    public List<Member3> getMembers() {
        return members;
    }

    public void setMembers(List<Member3> members) {
        this.members = members;
    }
}
