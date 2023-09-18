import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] wine;
	static Integer[] dp;
	static int max;

	static int recur(int level) {
		if (dp[level] == null) {
			dp[level] = Math.max(recur(level - 3) + wine[level - 1], recur(level - 2)) + wine[level];
			dp[level] = Math.max(dp[level], recur(level - 1));
		}
		return dp[level];
	}

	static void make() throws IOException {
		N = init(br.readLine());
		wine = new int[N + 1];
		dp = new Integer[N + 1];

		for (int i = 1; i < N + 1; i++) {
			wine[i] = init(br.readLine());
		}
	}

	static void solution() {
		dp[0] = 0;
		dp[1] = wine[1];
		if (N > 1)
			dp[2] = wine[1] + wine[2];

		System.out.println(recur(N));
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
