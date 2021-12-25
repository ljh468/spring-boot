package lamda;

@FunctionalInterface
interface BigNumber {
    int getBigNumber(int num1, int num2);
}

public class lamdaExample01 {

    public static void main(String[] args) {
        
        // BigNumber 인터페이스를 람다식으로 정의함
        BigNumber bigNumber = (x, y) -> {
            if (x > y) {
                return x;
            } else {
                return y;
            }
        };

        int result = bigNumber.getBigNumber(2156, 12382);
        System.out.println(result);
    }
}
