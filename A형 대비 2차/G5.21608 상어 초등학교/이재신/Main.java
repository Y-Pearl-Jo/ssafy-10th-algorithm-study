import java.awt.Point;
import java.io.*;
import java.util.*;
// 메모리 13472 KB, 144 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int sum = 0;
	static int[][] room, shark;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int[] score = { 0, 1, 10, 100, 1000 };

	static void setPosition() {
		student: for (int student = 1; student < N * N; student++) {
			int maxLike = Integer.MIN_VALUE;
			int maxBlank = Integer.MIN_VALUE;
			Point p = new Point();
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					// 이선좌
					if (room[row][col] != 0) {
						continue;
					}
					// 좋아하는 학생의 수
					int cLike = countLike(row, col, student);
					// 비어있는 칸의 수
					int cBlank = countBlank(row, col);
					// 주변이 다 좋아하는 학생이라면 무조건 그자리
					// 좋아하는 학생 수 > 비어있는 칸 수 > 행의 번호 작은 칸 > 열의 번호 작은 칸
					if (cLike == 4) {
						room[row][col] = shark[student][0];
						continue student;
					}
					// 좋아하는 학생 수가 max 값보다 큰 지 확인
					else if (cLike > maxLike) {
						p.x = row;
						p.y = col;
						maxLike = cLike;
						maxBlank = cBlank;
					}
					// 비어있는 칸의 수가 max 값보다 큰 지 확인
					else if (cLike == maxLike) {
						if (cBlank > maxBlank) {
							p.x = row;
							p.y = col;
							maxBlank = cBlank;
						}
					}
				}
			}
			room[p.x][p.y] = shark[student][0];
		}
	}

	// 최종 만족도 확인
	static void satisfy() {
		sum = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int idx = 0;
				// 해당 자리에 있는 학생의 인덱스 정보가 필요함
				for (int i = 0; i < N * N; i++) {
					if (shark[i][0] == room[row][col]) {
						idx = i;
						break;
					}
				}
				// 주변에 좋아하는 학생 수에 따라 점수 획득
				sum += score[countLike(row, col, idx)];
			}
		}
	}

	// 4방에 좋아하는 학생의 수 확인
	static int countLike(int row, int col, int student) {
		int cnt = 0;

		for (int like = 1; like < 5; like++) {
			for (int i = 0; i < 4; i++) {
				int r = row + dr[i];
				int c = col + dc[i];
				if (index(r, c) && room[r][c] == shark[student][like]) {
					cnt++;
					break;
				}
			}
		}

		return cnt;
	}

	// 4방에 아직 빈칸인 곳의 수 확인
	static int countBlank(int row, int col) {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			int r = row + dr[i];
			int c = col + dc[i];
			if (index(r, c) && room[r][c] == 0) {
				cnt++;
			}
		}

		return cnt;
	}

	static void make() throws IOException {
		N = init();
		room = new int[N][N];
		// 0 학생 번호 1 ~ 4 좋아하는 학생 번호
		shark = new int[N * N][5];

		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				shark[i][j] = init(st);
			}
		}
		// 무조건 첫번째는 여기 자리임
		room[1][1] = shark[0][0];
	}

	static void solution() {
		setPosition();
		satisfy();
		System.out.println(sum);
	}

	public static void main(String[] args) throws Exception {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			return false;
		}
		return true;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
