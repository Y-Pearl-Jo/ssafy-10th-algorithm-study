import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, T;
	static int[][] dust, temp;
	static int[] Airconditioner;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	// 시계 방향 돌리기
	static void clockWise() {
		int air = Airconditioner[1];
		int tempLD = dust[N - 1][0];
		int tempRU = dust[air][M - 1];
		int tempRD = dust[N - 1][M - 1];

		for (int i = air + 1; i < N - 2; i++) {
			dust[i][0] = dust[i + 1][0];
		}
		dust[N - 2][0] = tempLD;
		for (int i = 0; i < M - 2; i++) {
			dust[N - 1][i] = dust[N - 1][i + 1];
		}
		dust[N - 1][M - 2] = tempRD;
		for (int i = N - 1; i > air + 1; i--) {
			dust[i][M - 1] = dust[i - 1][M - 1];
		}
		dust[air + 1][M - 1] = tempRU;
		for (int i = M - 1; i > 0; i--) {
			dust[air][i] = dust[air][i - 1];
		}
	}

	// 반시계 방향 돌리기
	static void counterClockWise() {
		int air = Airconditioner[0];
		int tempLU = dust[0][0];
		int tempRU = dust[0][M - 1];
		int tempRD = dust[air][M - 1];

		for (int i = air - 1; i > 1; i--) {
			dust[i][0] = dust[i - 1][0];
		}
		dust[1][0] = tempLU;
		for (int i = 0; i < M - 1; i++) {
			dust[0][i] = dust[0][i + 1];
		}
		dust[0][M - 1] = tempRU;
		for (int i = M - 1; i > 0; i--) {
			dust[air][i] = dust[air][i - 1];
		}
		for (int i = 0; i < air - 1; i++) {
			dust[i][M - 1] = dust[i + 1][M - 1];
		}
		dust[air - 1][M - 1] = tempRD;
	}

	// 미세먼지 확산
	static void spread(int x, int y) {
		// 확산되는 양은 값 / 5 (소숮점 버림)
		int spDust = dust[x][y] / 5;
		int cnt = 0;
		// 인접한 4반향으로 확산
		for (int i = 0; i < 4; i++) {
			int r = x + dr[i];
			int c = y + dc[i];

			// 인접한 방향에 공기청정기가 있거나, 칸이 없으면 확산 X
			if (r >= 0 && r < N && c >= 0 && c < M) {
				if ((r == Airconditioner[0] || r == Airconditioner[1]) && c == 0)
					continue;
				temp[r][c] += spDust;
				cnt++;
			}
		}
		// 남은 미세먼지의 양은 (x,y) - (x,y) / 5 * 확산된 방향의 개수
		temp[x][y] += (dust[x][y] - (cnt * spDust));
	}
	// 공기 청정기의 위치
	static void findAir() {
		int idx = 2;

		while (true) {
			if (dust[idx][0] == -1) {
				Airconditioner = new int[] { idx, idx + 1 };
				dust[idx][0] = 0;
				dust[idx + 1][0] = 0;
				break;
			}
			idx++;
		}
	}

	static void cycle() {
		temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (dust[i][j] != 0) {
					spread(i, j);
				}
			}
		}

		dust = temp.clone();

		clockWise();
		counterClockWise();
	}

	static int result() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sum += dust[i][j];
			}
		}
		return sum;
	}

	static void solution() {
		for (int i = 0; i < T; i++) {
			cycle();
		}
		System.out.println(result());
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		T = init(st);

		dust = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				dust[i][j] = init(st);
			}
		}

		findAir();
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
