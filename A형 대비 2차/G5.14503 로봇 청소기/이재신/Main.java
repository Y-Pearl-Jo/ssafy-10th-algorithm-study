import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 11856 KB, 시간 84 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// 방의 크기
	static int N, M;
	// 청소가 되지 않은 빈 칸 : 0, 청소가 된 곳 : -1, 벽 : 1
	static int[][] room;
	// 로봇청소기 방향
	// 0 : 북, 1 : 동, 2 : 남, 3 : 서
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	// 로봇청소기 위치 및 방향
	static Robot robot;
	static int cnt;

	static boolean move(boolean clean) {
		// 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는 경우
		if (clean) {
			// 방향 유지한채 앞쪽 칸이 청소되지 않은 경우 한 칸 전진
			robot.r += dr[robot.dir];
			robot.c += dc[robot.dir];
		}
		// 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
		else {
			// 방향 유지한 채 한 칸 후진하고 청소 여부 확인
			int dir = (robot.dir + 2) % 4;
			int row = robot.r + dr[dir];
			int col = robot.c + dc[dir];

			// 후진이 불가능하면 작동 멈춤
			if (room[row][col] == 1)
				return false;
			else {
				robot.r = row;
				robot.c = col;
			}
		}

		return true;
	}

	static boolean isClean() {
		// 현재 로봇 청소기의 방향
		int dir = robot.dir;
		for (int i = 0; i < 4; i++) {
			// 다음 탐색 정면은 반시계 방향 90도 회전
			dir = (dir + 3) % 4;
			// 현재 방향에서 이동했을 때 좌표
			int row = robot.r + dr[dir];
			int col = robot.c + dc[dir];
			// 청소하지 않은 공간이라면
			if (room[row][col] == 0) {
				// 로봇청소기의 방향을 바꾸고 true return
				robot.dir = dir;
				return true;
			}
		}
		// 청소가 가능한 공간이 없는 경우
		return false;
	}

	static void solution() {
		boolean flag = true;
		while (flag != false) {
			// 현재 칸이 아직 청소되지 않은 경우 청소
			if (room[robot.r][robot.c] == 0) {
				room[robot.r][robot.c] = -1;
				cnt++;
			}
			// 주변 청소 상태 여부에 따라 이동
			// 이동 가능 여부에 따라 반복문 종료
			flag = move(isClean());

		}
		System.out.println(cnt);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		room = new int[N][M];

		st = new StringTokenizer(br.readLine());
		robot = new Robot(init(st), init(st), init(st));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = init(st);
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

	static class Robot {
		int r;
		int c;
		int dir;

		public Robot(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}
}
