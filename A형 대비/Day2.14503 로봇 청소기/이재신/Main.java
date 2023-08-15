import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] room;
	static boolean[][] clean;
	static int row;
	static int col;
	static int direction;

	static int cleanroom() {
		int cnt = 0;

		loop: while (true) {
			// 1. 현재 칸 청소
			if (!clean[row][col]) {
				clean[row][col] = true;
				cnt++;
			}
			// 2. 주변 4칸 탐색
			boolean check = false;
			if (!clean[row - 1][col]) {
				check = true;
			}
			if (!clean[row][col - 1]) {
				check = true;
			}
			if (!clean[row + 1][col]) {
				check = true;
			}
			if (!clean[row][col + 1]) {
				check = true;
			}
			// 3. 청소되지 않은 빈 칸이 없는 경우
			if (!check) {
				switch (direction) {
				// 방향 북쪽 -> 행 증가
				case 0:
					if (room[row + 1][col] != 1) {
						row++;
					} else {
						break loop;
					}
					break;
				// 방향 동쪽 -> 열 감소
				case 1:
					if (room[row][col - 1] != 1) {
						col--;
					} else {
						break loop;
					}
					break;
				// 방향 남쪽 -> 행 감소
				case 2:
					if (room[row - 1][col] != 1) {
						row--;
					} else {
						break loop;
					}
					break;
				// 방향 서쪽 -> 열 증가
				case 3:
					if (room[row][col + 1] != 1) {
						col++;
					} else {
						break loop;
					}
					break;
				}
			}
			// 4. 청소되지 않은 빈 칸이 있는 경우
			if (check) {
				direction--;
				if (direction == -1) {
					direction = 3;
				}
				switch (direction) {
				// 방향 북쪽
				case 0:
					if (room[row - 1][col] == 0 && !clean[row - 1][col]) {
						row--;
					}
					break;
				// 방향 동쪽
				case 1:
					if (room[row][col + 1] == 0 && !clean[row][col + 1]) {
						col++;
					}
					break;
				// 방향 남쪽
				case 2:
					if (room[row + 1][col] == 0 && !clean[row + 1][col]) {
						row++;
						;
					}
					break;
				// 방향 서쪽
				case 3:
					if (room[row][col - 1] == 0 && !clean[row][col - 1]) {
						col--;
					}
					break;
				}
			}
		}

		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		// 행 개수
		N = init(st.nextToken());
		// 열 개수
		M = init(st.nextToken());
		room = new int[N][M];
		clean = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		// 로봇 청소기 위치 [0]: 행, [1]:열
		row = init(st.nextToken());
		col = init(st.nextToken());
		// 로봇 청소기가 바라보는 방향
		direction = init(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = init(st.nextToken());
				if (room[i][j] == 1) {
					clean[i][j] = true;
				}
			}
		}

		System.out.println(cleanroom());

	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
