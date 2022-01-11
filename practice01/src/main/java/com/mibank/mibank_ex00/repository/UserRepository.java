package com.mibank.mibank_ex00.repository;

import com.mibank.mibank_ex00.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 일반 사용자 ID 중복확인
    Optional<User> findByUsername(String username);
}
