package com.mibank.mibank_ex00.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Sms {

    @Id @GeneratedValue
    private Long seq;

    private String mobile;

    private String birth;

    private String authcode;

    private LocalDateTime regdt;

}
