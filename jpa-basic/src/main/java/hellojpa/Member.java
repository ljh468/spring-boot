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
