package hellojpa;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * JPA가 관리하는 엔티티
 * 기본 생성자 필수 (파라미터가 없는 public 또는 protected 생성자)
 * 저장할 필드에 final 사용 X
  */

@Entity
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1, allocationSize = 50)
        // allocationSize : 속도 향상을 위해 미리 시퀀스값을 확보함

//@TableGenerator(name = "MEMBER_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1))
public class Member2 {

    /**
     * 기본키 매핑
     * 직접 할당 : @Id만 사용
     * 자동 생성 : @GeneratedValue
     * IDENTITY : 기본키 생성을 데이터베이스에 위임, MYSQL (null값으로 들어감)
     * SEQUENCE : 데이터베이스 시퀀스 오브젝트 사용, 오라클DB는 @SequenceGenerator 필요
     * TABLE : 키 생성용 테이블 사용, 모든 DB에서 사용, @TableGenerator 필요
     * AUTO : 방언에 따라 자동 지정
     * 권장은 Long형 + 대체키 + 키 생성전략 사용
     */
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name="name", nullable = false)
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

    public Member2(){

    }
}
