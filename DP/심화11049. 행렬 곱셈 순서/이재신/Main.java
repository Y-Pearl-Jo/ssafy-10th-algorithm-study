//미완성
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 재귀를 통해서 분할 정복 하고 싶은데...
 * 
 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int max;
	static int[][] dp;
	static int[][] matrix;

	static int multiple(int a, int b) {
		for (int i = a; i < b; i++) {
			int temp = dp[a][i] + dp[i + 1][b] + matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
			dp[a][b] = Math.max(dp[a][b], temp);
		}
		return dp[a][b];
	}

	static void solution() {
		for (int i = 0; i < N - 1; i++) {
			dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
		}
		for (int i = 1; i < N - 1; i++) {
			dp[0][i] = multiple(0, i - 1) + multiple(i + 1, N - 1) + matrix[i][0] * matrix[i][1] * matrix[i + 1][1];
		}
		System.out.println(dp[0][N - 1]);
	}

	static void make() throws IOException {
		N = init(br.readLine());
		matrix = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			matrix[i][0] = init(st.nextToken());
			matrix[i][1] = init(st.nextToken());
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static boolean index(int row, int col) {
		if (row >= 0 && row < N && col >= 0 && col < N)
			return true;
		return false;
	}

	;

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
