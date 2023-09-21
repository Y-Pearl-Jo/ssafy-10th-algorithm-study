import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 70820 KB 시간 : 564 ms
/*
 * DFS 횟수를 최소한으로 줄이기 위해 메모이제이션을 해야함
 * 만약 0,0 에서 DFS를 통해 최장 경로를 구했다면,
 * 해당 경로 내의 인덱스들에서의 최장 경로도 알 수 있음.
 * 이를 dp 배열에 메모이제이션 함으로써 DFS 횟수를 최소로 줄이는 것이 가능함.
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int max;
	static int[][] bamboo, dp;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	static int dfs(int row, int col) {
		if (dp[row][col] != 0)
			return dp[row][col];

		// 어디서 시작하던 최소 1일 가능
		dp[row][col] = 1;

		for (int i = 0; i < 4; i++) {
			int r = row + dr[i];
			int c = col + dc[i];
			if (index(r, c) && bamboo[r][c] > bamboo[row][col]) {
				dp[row][col] = Math.max(dp[row][col], dfs(r, c) + 1);
			}
		}

		return dp[row][col];
	}

	static void solution() {
		max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}
		System.out.println(max);
	}

	static void make() throws IOException {
		N = init(br.readLine());
		bamboo = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				bamboo[i][j] = init(st.nextToken());
			}
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

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
