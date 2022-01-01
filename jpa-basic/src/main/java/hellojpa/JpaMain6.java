package hellojpa;

import org.hibernate.type.LocalDateTimeType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

// JPA 정석 코드
public class JpaMain6 {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리는 하나만 생성해서 전체 애플리케이션에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 엔티티 매니저는 쓰레드간에 공유 X (사용하고 버려야 한다)
        EntityManager em = emf.createEntityManager();

        // JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            System.out.println("======= start ========");

            /**
             * 임베디드 값타입의 활용
             * 임베디드 타입은 엔티티의 값일 뿐이다.
             * 임베디드 타입을 사용하기 전과 후에 매핑하는 테이블은 같다.
             * 객체과 테이블을 아주 세밀하게 매핑하는 것이 가능
             * 잘 설계한 ORM 애플리케이션은 매핑한 테이블의 수보다 클래스의 수가 더 많음
             */
//            Member4 member = new Member4();
//            member.setName("hello");
//            member.setHomeAddress(new Address("city", "street","zipcode"));
//            member.setWorkAddress(new Address("work", "work", "work"));
//
//            LocalDateTime dateTime = LocalDateTime.now();
//            member.setWorkPeriod(new Period(dateTime, dateTime));
//
//            em.persist(member);

            /**
             * 값타입 공유참조
             * 값 타입의 실제 인스턴스인 값을 공유하는 것은 위험
             * 대신 값(인스턴스)를 복사해서 사용 해야함
             * 문제는 임베디드 타입처럼 직접 정의한 값 타입은 자바의 기본타입이 아니라 객체타입이다.
             * 객체 타입은 참조값을 직접 대입하는것을 막을 방법이 없음 (객체의 공유참조는 피할수 없음)
             * 
             * 객체타입의 한계
             * 기본 타입은 값을 복사, 객체타입은 참조를 전달
             *
             *  불변객체
             *  생성 시점이후 절대 값을 변경할 수 없는 객체
             *  생성자로만 값을 설정하고 수정자를 만들지 않으면 됨 (get ok!, set no!)
             */

//            Address address = new Address("city", "street", "zipcode");
//
//            Member4 member = new Member4();
//            member.setName("member1");
//            member.setHomeAddress(address);
//            em.persist(member);
//
//            Member4 member2 = new Member4();
//            member2.setName("member2");
//
//            // 첫번째 멤버의 address만 바꿧는데 member1, member2 모두가 newCity로 바뀜
//            // 공유하는 Address값이 바뀌면 둘다 바뀜
//            // 새로 인스턴스를 복사해서 사용해야함
//            // member.getHomeAddress().setCity("newCity");
//            Address copyAddress = new Address(address.getCity(), address.getStreet(), "newzipcode");
//            member2.setHomeAddress(copyAddress);
//            em.persist(member2);

            /**
             * 값 타입 컬렉션
             * 값 타입을 하나 이상 저장할 때 사용
             * @ElementCollection, @CollectionTable 사용
             * 데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없다.
             * 컬렉션을 저장하기 위한 별도의 테이블이 필요함
             */

//            // 값타입 컬렉션 저장
//            // 값타입 컬렉션은 생명주기를 엔티티에 의존한다.
//            // 영속성 전이(Cascade) + 고아객체제거기능을 필수로 가진다고 볼 수 있다.
//            System.out.println("============== 값타입 저장 ==============");
//            Member4 member = new Member4();
//            member.setName("member_value1");
//            member.setHomeAddress(new Address("homeCity", "street1", "10000"));
//
//            member.getFavoriteFoods().add("피자");
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("스파게티");
//
//            member.getAddressHistory().add(new AddressEntity("old1", "street1", "10000"));
//            member.getAddressHistory().add(new AddressEntity("old2", "street1", "10000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("============== 값타입 조회 ==============");
//            // 값타입 조회
//            Member4 findMember = em.find(Member4.class, member.getId());
//
//            // 값타입 컬렉션은 지연로딩 전략을 사용
//            List<AddressEntity> addressHistory = findMember.getAddressHistory();
//            for (AddressEntity address : addressHistory) {
//                System.out.println("address = " + address.getAddress());
//            }
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }
//
//            // 값타입 컬렉션 수정
//            // 값타입은 통으로 갈아껴야한다.
//
//            System.out.println("============== 값타입 수정 ==============");
//            // homeCity -> newCity
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));
//
//            // 스파게티 -> 한식 (값을 제거하고 다시 새로 저장)
//            findMember.getFavoriteFoods().remove("스파게티");
//            findMember.getFavoriteFoods().add("한식");
//
//            // old1 -> newCity1
            /**
             * 값타입 컬렉션의 제약사항
             * 값타입은 엔티티와 다르게 식별자 개념이 없다., 값은 변경하면 추적이 어렵다.
             * 값타입 컬렉션에 변경사항이 발생하면, 주인 엔티티와 연관된 모든 데이터를 삭제하고,
             * 값타입 컬렉션에 있는 현재 값을 모두 다시 저장
             * 값타입 컬렉션을 매핑하는 테이블은 모든 컬럼을 묶어서 기본키를 구성해야함 : null입력 X, 중복저장 X
             */
            /**
             * 값타입 컬렉션의 대안
             * 실무에서는 상황에 따라 값타입 컬렉션 대신에 일대다 관계를 고려 (엔티티로 승격)
             * 일대다 관계를 위한 엔티티를 만들고, 여기에서 값타입을 사용
             * 영속성 전이(Cascade) + 고아객체 제거를 사용해서 값타입 컬렉션 처럼 사용
             * Ex) AddressEntity
             * 
             * 값타입은 정말 값타입이라 판단 될 때만 사용
             * 엔티티와 값타입을 혼동해서 엔티티를 값타입으로 만들면 안됨
             * 식별자가 필요하고, 지속해서 값을 추적, 변경해야 한다면 그것은 값타입이 아닌 엔티티
             */

            System.out.println("=======  end  ========");
            tx.commit(); // 정상이면 커밋 (이때 DB에 저장됨)

        } catch (
                Exception e) {
            tx.rollback(); // 예외면 롤백
        } finally {
            em.close(); // 엔티티 매니저를 닫음
        }

        emf.close(); // 엔티티 매니저 팩토리 닫음
    }
}
