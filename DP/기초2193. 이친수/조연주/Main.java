//12852kb 108ms

// 첫번째 -> 항상 1
// 두번째 -> 항상 0
// 세번째 ~
// 0으로 끝나는 경우 -> 뒤에 (n-1)개를 만들어야함
// 1로 끝나는 경우 -> 다음은 0 + 그 뒤에 (n-2)개를 만들어야함
// 따라서 N = (N-1) + (N-2)

import java.util.Scanner;

public class Main {
    static long[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new long[N+1];
        System.out.println(func(N));
    }

    static long func(int num) {
        // 저장된 값이 있으면 그대로 리턴
        if(arr[num]!=0){
            return arr[num];
        }

        // num = 1,2일때 -> 1리턴
        if (num <= 2) {
            arr[num] = 1;
            return 1;
        }

        // num이 3이상일 때 -> 배열에 값을 저장하고 재귀
        arr[num] = func(num-1)+func(num-2);
        return arr[num];
    }
    
}
