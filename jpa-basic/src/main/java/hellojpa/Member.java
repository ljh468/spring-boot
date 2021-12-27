package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
// 테이블 이름이 다르면 작성
//@Table(name = "USER")
public class Member {

    @Id
    private Long id;

    // Member 기본 생성자
    public Member(){
    }
    
    // Member객체를 여러개 사용하기 위한 생성자
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 컬럼이름이 다르면 작성
    // @Column(name = "username")
    private String name;

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
}
