import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		long[] d = new long[91];

		d[1] = 1;
		d[2] = 1;
		for (int i = 3; i <= N; i++) {
			d[i] = d[i - 1] + d[i - 2];
		}
		
		System.out.print(d[N]);
	}
}
// 첫번째 자리는 항상 1, 두번째 자리는 항상 0으로 고정.
// 이전 자리가 1이라면 그 다음 자리는 무조건 0이 나와야한다.
// 그리고 이전 자리가  0이라면 그다음 자리는 0 또는 1이 나올 수 있다.(분깃점생성)
// 종합하면 이친수의 개수는 피보나치 수열을 따른다는 것을 알 수 있다.
