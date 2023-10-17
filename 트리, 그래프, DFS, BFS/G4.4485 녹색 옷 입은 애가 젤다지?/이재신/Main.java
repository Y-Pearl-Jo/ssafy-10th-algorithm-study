import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 메모리 32932 KB, 시간 268 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int[][] cave;
	// boolean 배열 대신에 사용하면 입력 여부 판단 가능
	static Integer[][] dp;
	// 4방향 탐색 : 좌, 상, 우, 하
	static int[][] delta = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } };

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		dp[0][0] = cave[0][0];

		while (!q.isEmpty()) {
			Point cur = q.poll();
			int row = cur.x;
			int col = cur.y;
			// 사방 탐색
			for (int i = 0; i < 4; i++) {
				int r = row + delta[i][0];
				int c = col + delta[i][1];
				// 인덱스 범위 내
				if (index(r, c)) {
					// 아직 방문하지 않았거나 더 좋은 경우의 수이면
					if (dp[r][c] == null || dp[r][c] > dp[row][col] + cave[r][c]) {
						// dp값을 초기화 후 방문
						dp[r][c] = dp[row][col] + cave[r][c];
						q.add(new Point(r, c));
					}
				}
			}
		}
	}

	static void solution() throws IOException {
		bfs();
		sb.append(dp[N - 1][N - 1]);
	}

	static void make() throws IOException {
		cave = new int[N][N];
		dp = new Integer[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cave[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		int cnt = 1;
		while (true) {
			N = init();
			if (N == 0)
				break;
			make();
			sb.append("Problem ").append(cnt++).append(": ");
			solution();
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static boolean index(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N)
			return true;
		return false;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

	static int init(char c) {
		return c - '0';
	}
}
