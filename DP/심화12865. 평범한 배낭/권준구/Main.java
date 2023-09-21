// Knapsack알고리즘
// https://fbtmdwhd33.tistory.com/60


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N + 1][K + 1];
		int[] w = new int[N + 1]; // 무게
		int[] v = new int[N + 1]; // 가체

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j - w[i] >= 0) { // 무게가 남으면
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]); // 더 큰 값으로 갱신
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
