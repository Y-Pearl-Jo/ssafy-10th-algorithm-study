import java.util.Scanner;

/*
 * d[n]의 이친수의 개수
 * d[n-1]이 가진 0으로 끝나는 수의 개수 *2 + 1로 끝나는 수의 개수
 * d[n][0] = d[n-1][0] + d[n-1][1]
 * d[n][1] = d[n-1][0]
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		long [][] d = new long[N+1][2];
		
		d[1][0] = 0;
		d[1][1] = 1;
		for(int i=2; i<=N; i++) {
			d[i][0] = d[i-1][0] + d[i-1][1];
			d[i][1] = d[i-1][0];
		}
		
		System.out.println(d[N][0] + d[N][1]);
		
		sc.close();
	}

}
