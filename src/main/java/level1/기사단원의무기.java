package level1;

import java.util.stream.IntStream;

public class 기사단원의무기 {
    /*
     * [문제설명]
     * 숫자나라 기사단의 각 기사에게는 1번부터 number까지 번호가 지정되어 있습니다. 기사들은 무기점에서 무기를 구매하려고 합니다.
     * 각 기사는 자신의 기사 번호의 약수 개수에 해당하는 공격력을 가진 무기를 구매하려 합니다.
     *
     * 단, 이웃나라와의 협약에 의해 공격력의 제한수치를 정하고,
     * 제한수치보다 큰 공격력을 가진 무기를 구매해야 하는 기사는 협약기관에서 정한 공격력을 가지는 무기를 구매해야 합니다.
     *
     * 예를 들어, 15번으로 지정된 기사단원은 15의 약수가 1, 3, 5, 15로 4개 이므로, 공격력이 4인 무기를 구매합니다.
     * 만약, 이웃나라와의 협약으로 정해진 공격력의 제한수치가 3이고 제한수치를 초과한 기사가 사용할 무기의 공격력이 2라면,
     * 15번으로 지정된 기사단원은 무기점에서 공격력이 2인 무기를 구매합니다.
     *
     * 무기를 만들 때, 무기의 공격력 1당 1kg의 철이 필요합니다.
     * 그래서 무기점에서 무기를 모두 만들기 위해 필요한 철의 무게를 미리 계산하려 합니다.
     *
     * 기사단원의 수를 나타내는 정수 number와 이웃나라와 협약으로 정해진 공격력의 제한수치를 나타내는 정수 limit와 제한수치를
     * 초과한 기사가 사용할 무기의 공격력을 나타내는 정수 power가 주어졌을 때,
     * 무기점의 주인이 무기를 모두 만들기 위해 필요한 철의 무게를 return 하는 solution 함수를 완성하시오.
     *
     * [제한사항]
     * 1. 1 ≤ number ≤ 100,000
     * 2. 2 ≤ limit ≤ 100
     * 3. 1 ≤ power ≤ limit
     */
    public static void main(String[] args) {
        System.out.println(solution(5,3,2)); // 10
        System.out.println(solution(10, 3, 2)); // 21

        System.out.println(solution2(5,3,2)); // 10
        System.out.println(solution2(10, 3, 2)); // 21
    }

    // 시간초과 : number 의 약수를 찾을때 1~N의 모든 약수를 찾으면 시간 초과
    // 소인수 분해를 이용하거나 약수의 개수를 계산하는 방식으로 사용.
    public static int solution(int number, int limit, int power) {
        return IntStream.rangeClosed(1, number)
                .map(x -> {
                    return IntStream.rangeClosed(1, x)
                            .filter(i -> (x % i) == 0)
                            .toArray()
                            .length;
                })
                .reduce(0, (total, n) -> {
                    return (n > limit) ? total + power : total + n;
                });
    }

    // 제곱근을 기준으로 순서만 바뀐 똑같은 연산이 발생함.
    // 제곱근을 기준으로 약수를 구하고 동일한 값은 제거해줌.
    public static int solution2(int number, int limit, int power) {
        return IntStream.rangeClosed(1, number)
                .map(x -> {
                    int count = 0;
                    int sqrt = (int) Math.sqrt(x);
                    int dup = 0;
                    for(int i=1; i<=sqrt; i++) {
                        count = (x % i) == 0 ? count+1 : count;
                        dup = (i*i == x) ? dup+1 : dup;
                    }
                    return (count * 2) - dup;
                })
                .reduce(0, (total, n) -> {
                    return (n > limit) ? total + power : total + n;
                });
    }
}
