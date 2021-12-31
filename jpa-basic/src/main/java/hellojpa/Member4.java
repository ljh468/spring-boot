package hellojpa;

import javax.persistence.*;
import javax.xml.namespace.QName;
import java.time.LocalDateTime;

@Entity
public class Member4{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    // 기간 Period
    // 값 타입을 사용하는곳
    @Enumerated
    private Period workPeriod;

    // 주소 adrdess
    // 값 타입을 사용하는곳
    @Enumerated
    private Address homeAddress;

    // 임베디드 타입을 중복으로 사용하려면?
    // 컬럼명이 중복됨, @AttributeOverride를 사용하여 컬럼명 속성을 재정의
    @Enumerated
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE")),
    })
    private Address workAddress;

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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }
}
