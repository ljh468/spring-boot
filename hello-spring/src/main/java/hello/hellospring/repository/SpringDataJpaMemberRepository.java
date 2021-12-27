package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 상속 받으려면 extends
// 인터페이스는 다중상속 가능
// 스프링데이터 JPA가 SpringDataJpaMemberRepository를 스프링 빈으로 자동 등록 해줌
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}
