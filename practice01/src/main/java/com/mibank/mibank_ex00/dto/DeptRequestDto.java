package com.mibank.mibank_ex00.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeptRequestDto {
    List<String> deptNames;

    public DeptRequestDto() {
        this.deptNames = deptNames;
    }
}
