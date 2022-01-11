package com.mibank.mibank_ex00.repository;

import com.mibank.mibank_ex00.domain.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository  extends JpaRepository<Dept, Long> {
//    List<User> findAllByUser(String dept_name);
}
