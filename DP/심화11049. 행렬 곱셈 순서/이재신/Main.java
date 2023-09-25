import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int INF = Integer.MAX_VALUE;
	static int N;
	static int[] matrix;
	static int[][] dp;

	static int multiple(int a, int b) {
		if (a == b) return 0;
		if (dp[a][b] != INF) return dp[a][b];
		for (int i = a; i < b; i++) {
			int temp = multiple(a, i) + multiple(i + 1, b) + matrix[a] * matrix[i + 1] * matrix[b + 1];
			dp[a][b] = Math.min(dp[a][b], temp);
		}
		return dp[a][b];
	}

	static void solution() {
		System.out.println(multiple(0, N - 1));
	}

	static void make() throws IOException {
		N = init(br.readLine());
		matrix = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			matrix[i] = init(st.nextToken());
			matrix[i + 1] = init(st.nextToken());
		}

		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
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
