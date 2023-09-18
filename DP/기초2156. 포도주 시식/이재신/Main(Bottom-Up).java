import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] wine;
	static int[] dp;

	static void solution() {
		dp[0] = 0;
		dp[1] = wine[1];
		
		if (N > 1)
			dp[2] = wine[1] + wine[2];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 3] + wine[i - 1], dp[i - 2]) + wine[i];
			dp[i] = Math.max(dp[i], dp[i - 1]);
		}
		
		System.out.println(dp[N]);
	}
	
	static void make() throws IOException {
		N = init(br.readLine());
		wine = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i < N + 1; i++) {
			wine[i] = init(br.readLine());
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
