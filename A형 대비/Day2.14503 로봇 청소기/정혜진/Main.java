//11840KB	84ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	// 북 동 남 서
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static int N, M;
	static int[][] room;
	static boolean[][] cleanRoom;
	static int count; // 로봇 청소기가 작동을 시작한 후 멈출 때까지 청소하는 칸

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 방의 크기 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		room = new int[N][M];
		cleanRoom = new boolean[N][M];

		// 로봇 청소기 좌표 입력, 방향 입력
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		count = 1;
		vacuum(r, c, d);
		System.out.println(count);

	}

	public static void vacuum(int r, int c, int d) {
		room[r][c] = 2;

		// 현재 칸 청소 되어있는 경우
		int n = 0;
		while (n < 4) {
			d = (d + 3) % 4; // 반시계 방향으로 회전
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			if (range(nr, nc) && room[nr][nc] == 0) {
				count++;
				vacuum(nr, nc, d);
				return;
			}
			n++;
		}

		// 주변 4칸이 청소가 다 되어있는 경우
			// 후진
			int backD = (d + 2) % 4; // 180도 회전
			int nr = r + dr[backD];
			int nc = c + dc[backD];

			// 후진 가능
			if (range(nr, nc) && room[nr][nc] != 1) {
				vacuum(nr, nc, d);
			} else {
				// 후진했는데 벽이면 종료
				return;
			}
	}

	// 배열 범위 내이고 벽이 아닌지 확인하는 메소드
	public static boolean range(int r, int c) {

		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		} else {
			return false;
		}
	}
}
