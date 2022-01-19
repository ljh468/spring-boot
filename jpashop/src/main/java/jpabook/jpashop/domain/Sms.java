package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Sms {

    @Id
    @GeneratedValue
    private Long seq;

    private String mobile;

    private String birth;

    private String authcode;

    private LocalDateTime regdt;

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public LocalDateTime getRegdt() {
        return regdt;
    }

    public void setRegdt(LocalDateTime regdt) {
        this.regdt = regdt;
    }
}
