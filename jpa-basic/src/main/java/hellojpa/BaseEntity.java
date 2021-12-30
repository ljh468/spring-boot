package hellojpa;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 테이블과 관계없고 객체의 매핑정보만 제공하는 슈퍼클래스
// 상속이 아님, 추상클래스로 사용
// 조회, 검색 불가
// 등록일, 수정일, 등록자, 수정자 같은 공통으로 적용하는 정보를 모을때 사용
// @Entity 클래스는 엔티티나 @MappedSuperclass로 지정한 클래스만 상속이 가능
@MappedSuperclass
public abstract class BaseEntity {

    // Mapped Superclass (공통적으로 들어가는 속성)
    private String createBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
