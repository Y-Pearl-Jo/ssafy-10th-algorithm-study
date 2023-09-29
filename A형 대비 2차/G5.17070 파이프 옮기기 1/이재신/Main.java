import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 11756 KB, 시간 76 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] map;
	// 0 : 가로, 1 : 세로, 2 : 대각선 2차원 배열을 3종류로 나누기 위해 3차원 배열 사용
	static int[][][] dp;

	static void solution() {
		// 가장 처음 파이프는 (1, 1)(1, 2)를 차지한 가로 파이프
		dp[1][2][0] = 1;
		// 세로 탐색
		for (int i = 1; i < N + 1; i++) {
			// 첫 파이프가 무조건 가로 파이프 이기 때문에 1열 2열에는 파이프가 들어가는 경우의 수가 없음
			for (int j = 3; j < N + 1; j++) {
				// 가로 파이프 추가
				// 왼쪽 칸이 가로 파이프이거나, 대각선 파이프이거나
				if (map[i][j] == 0) {
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
				}
				// 세로 파이프 추가
				// 윗 칸이 세로 파이프이거나, 대각선 파이프이거나
				if (map[i][j] == 0) {
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
				}
				// 대각선 파이프 추가
				// 좌상단 칸만 확인하면 되며, 어떤 칸이든 상관 없음
				if (map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
				}
			}
		}
		int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
		System.out.println(result);
	}

	static void make() throws IOException {
		N = init();
		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1][3];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
