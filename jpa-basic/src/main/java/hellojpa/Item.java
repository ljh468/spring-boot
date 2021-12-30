package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일테이블 전략 (성능의 이점)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현클래스 마다 테이블 전략 (DTYPE 필요없음, union조회)
@Inheritance(strategy = InheritanceType.JOINED) // 조인전략
@DiscriminatorColumn // DTYPE을 생성 (엔티티명이 기본값)
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
