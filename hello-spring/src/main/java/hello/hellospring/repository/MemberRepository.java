package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 데이터베이스에 접근하기 위한 인터페이스
public interface MemberRepository {
    // 멤버를 저장
    Member save(Member member);

    // ID를 찾아서 반환( Null값이면 Optional로 감싸서 반환)
    Optional<Member> findById(Long id);

    // Name을 찾아서 반환
    Optional<Member> findByName(String name);

    // 모든 회원리스트 반환
    List<Member> findAll();
}
