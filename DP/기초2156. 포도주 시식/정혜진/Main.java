import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] d = new int[10001][3];

		int N = Integer.parseInt(br.readLine());
		int[] num = new int[10001];

		for (int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		d[1][0] = 0;
		d[1][1] = num[1];
		d[1][2] = 0;
		
		d[2][0] = d[1][1];
		d[2][1] = num[2];
		d[2][2] = d[1][1] + num[2];

		int max = num[1];

		for (int i = 3; i <= N; i++) {
			// 현재 포도주를 마시지 않을 경우
			d[i][0] = Math.max(d[i - 1][1], d[i - 1][2]);
			// 현재 포도주를 마시고, 연속으로 1잔만 마신 경우
			d[i][1] = Math.max(Math.max(d[i-2][1], d[i-2][2]), d[i-2][0]) + num[i];
			// 현재 포도주를 마시고, 지금까지 연속해서 2잔 마신 경우
			d[i][2] = d[i - 1][1] + num[i];

			max = Math.max(Math.max(Math.max(d[i][0], d[i][1]), d[i][2]), max);

		}
		System.out.println(max);
	}
}
