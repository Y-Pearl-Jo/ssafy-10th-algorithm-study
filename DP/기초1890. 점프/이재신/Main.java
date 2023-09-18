import java.io.*;
import java.util.*;
// Bottom-Up 11816 KB 84 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] board;
	static long[][] dp;

	static void solution() {
		dp[0][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int plus = board[i][j];
				if(plus == 0) break;
				//최단 경로를 찾는 문제가 아니고 경로의 수를 찾는 문제이므로
				//Min 값 비교가 아닌 경우의 수를 계속 더해주는 방식
				if (i + plus < N)
					dp[i + plus][j] += dp[i][j];
				if (j + plus < N)
					dp[i][j + plus] += dp[i][j];
			}
		}

		System.out.println(dp[N - 1][N - 1]);
	}

	static void make() throws IOException {
		N = init(br.readLine());
		board = new int[N][N];
		dp = new long[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = init(st.nextToken());
			}
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
