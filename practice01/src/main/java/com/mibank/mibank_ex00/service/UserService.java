package com.mibank.mibank_ex00.service;

import com.mibank.mibank_ex00.domain.User;
import com.mibank.mibank_ex00.domain.UserRoleEnum;
import com.mibank.mibank_ex00.dto.UserRequestDto;
import com.mibank.mibank_ex00.repository.DeptRepository;
import com.mibank.mibank_ex00.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DeptRepository deptRepository;

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";

    @Autowired
    public UserService(UserRepository userRepository, DeptRepository deptRepository) {
        this.userRepository = userRepository;
        this.deptRepository = deptRepository;
    }

    public User registerUser(UserRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUser_name();
        System.out.println("username = " + username);
        Optional<User> foundUser = userRepository.findByUsername(username);
        if(foundUser.isPresent()){
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }

        // 패스워드 암호화
        String password = requestDto.getUser_pwd();
        System.out.println("password = " + password);
        String email = requestDto.getEmail();
        System.out.println("email = " + email);
        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)){
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        System.out.println("role = " + role);

        // 부서명 조회
        String deptname = requestDto.getDept_name();
        System.out.println("deptname = " + deptname);
//        List<User> userList =  deptRepository.findAllByUser(deptname);
//        for (User user : userList){
//            if(username.equals(username)){
//                throw new IllegalArgumentException("이미 부서에 가입된 화원입니다.");
//            }
//        }
        User user = new User(username, password, email, deptname, role);
        userRepository.save(user);
        return user;
    }
}
