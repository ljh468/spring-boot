package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * JPA가 관리하는 엔티티
 * 기본 생성자 필수 (파라미터가 없는 public 또는 protected 생성자)
 * 저장할 필드에 final 사용 X
  */

@Entity
// 테이블 이름이 다르면 작성
// name, catalog, schema, uniqueConstraints(DDL 유니크제약조건) 옵션이 있음
//@Table(name = "MBR", uniqueConstraints = ?)
public class Member {

    // Member 기본 생성자
    public Member(){
    }
    
    // Member객체를 여러개 사용하기 위한 생성자
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private Long id;

    private String name;
    // 컬럼이름이 다르면 작성
    // 유니크제약조건? 길이? 컬럼이름?
    // @Column(unique = true, length = 10, name = "USERNAME")
//    private String name;

    // 요구사항 추가
    // 회원은 일반 회원과 관리자로 구분해야한다.
    // 회원가입일과 수정일이 있어야 한다.
    // 회원을 설명할 수 있는 필드가 있어야한다. 이 필드는 길이 제한이 없다.
//    @Column(name = "name")
//    private String username;

    /**
     * 컬럼 매핑
     * name :  필드와 매핑할 테이블의 컬럼 이름
     * insertable , updatable : 업데이트할때 등록, 변경 가능여부 // 기본값은 true
     * nullable : null 값 허용 여부 // false면 not null제약조건이 붙음
     * length : 문자 길이 조정, String 타입에만 사용
     * columnDefinition : 컬럼 정보를 직업 부여 // ex) varchar(100) default 'EMPTY'
     * precision : BigDecimal 타입에서 사용, 소수점을 포함한 전체 자릿수
     * scale : 소수인 자릿수 (정밀한 소수점을 다룰때 쓰임)
     */
    @Column(name = "age")
    private Integer age;

    /**
     * enum 타입 매핑
     * 자바 enum 타입을 매핑할때 사용
     * EnumType.ORDINAL : enum 순서를 데이터베이스에 저장 // enum이 추가시 순서가 변함, ORIDINAL 은 사용 X
     * EnumType.STRING : enum 이름을 데이터베이스에 저장
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    /**
     * 날짜 타입 매핑
     * 날짜 : DATE , 시간 : TIME , 날짜/시간 : TIMESTAMP
     * 최신 하이버네이트는 LocalDate, LocalDateTime을 사용할 때는 생략 가능
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate; // 년,월,일 (날짜)
    private LocalDateTime testLocalDateTime; // 년,월,일,시간 (날짜/시간)

    /**
     * BLOB, CLOB 매핑
     * 매핑하는 필드타입이 문자면 CLOB, 나머지는 BLOB
     */
    @Lob
    private String description;

    /**
     * 필드 매핑 X
     * DB와 매핑을 하고 싶지 않을때 (메모리에서만 사용)
     */
    @Transient
    private int temp;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
