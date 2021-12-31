package hellojpa;

public class ValueMain {
    public static void main(String[] args) {

        /**
         * 자바의 기본 타입은 절대 공유 X
         * 기본 타입은 항상 값을 복사함
         * Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 공유가능한 객체이지만 변경 X
         */
        int a = 10;
        int b = a; // 복사가 되서 넘어감

        a = 20;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        Integer c = new Integer(10);
        Integer d = c;
        // d.setValue(20); // 래퍼런스가 넘어감 (주소값) but 가능은하지만 방법을 막아놈
        c = 20;
        System.out.println("c = " + c);
        System.out.println("d = " + d);

        /**
         * 객체타입의 비교
         * 인스턴스의 참조값을 비교,  == 사용
         * 인스턴스의 값을 비교, equals()를 오버라이드해서 사용
         */
        Address address1 = new Address("city", "street", "zipcode");
        Address address2 = new Address("city", "street", "zipcode");

        // 동일성 비교 == 사용
        System.out.println("address1 == address2 = " + (address1 == address2));

        // 동등성 비교 .equals() 사용, equals를 오버라이드해서 사용
        System.out.println("address1 == address2 = " + (address1.equals(address2)));
    }
}
