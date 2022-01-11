package com.mibank.mibank_ex00.domain;


public enum UserRoleEnum {
    ADMIN(Authority.USER), // 사용자
    USER(Authority.ADMIN);  // 관리자

    private final String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority{
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
