import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 메모리 32868 KB, 시간 180 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K;
	static int[][] map;
	static int score = 0;
	static Dice dice = new Dice();
	// 주사위 초기 위치
	static int r = 1, c = 1;
	// 0 = 동, 1 = 남, 2 = 서, 3 = 북
	static int direct = 0;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	static void move() {
		// 이동 방향에 칸이 없다면, 이동방향을 반대로 바꾸고 한 칸 굴러간다.
		if (!index(r + dr[direct], c + dc[direct]))
			direct = (direct + 2) % 4;

		// 주사위가 이동 방향으로 한 칸 이동한다.
		r = r + dr[direct];
		c = c + dc[direct];
		// 이동한다 = 굴러간다.
		dice.rollDice();

		// 도착한 칸에 대한 점수를 획득한다.
		score += getScore();
	}

	static void rotate() {
		// 주사위의 아랫면에 있는 정수 A, 칸에 해당하는 정수 B
		int A = dice.down;
		int B = map[r][c];
		// A > B 인 경우 이동 방향을 90도 시계 방향
		if (A > B)
			direct = (direct + 1) % 4;
		// A < B 인 경우 이동 방향을 90도 반시계 방향
		else if (A < B)
			direct = (direct + 3) % 4;
		// A = B 인 경우 이동 방향에 변화 없음
	}

	static int getScore() {
		// 칸에 해당하는 정수 B
		int B = map[r][c];
		// 동서남북 방향으로 연속해서 이동할 수 있는 칸의 수 C
		int C = 1;
		boolean[][] visited = new boolean[N + 1][M + 1];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { r, c });
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nR = cur[0] + dr[i];
				int nC = cur[1] + dc[i];

				// 이동할 수 있다의 조건 = 정수 B
				if (index(nR, nC) && !visited[nR][nC] && map[nR][nC] == B) {
					C++;
					q.add(new int[] { nR, nC });
					visited[nR][nC] = true;
				}
			}
		}
		// 최종 획득 점수 B * C
		return B * C;
	}

	static class Dice {
		// 주사위의 육면
		int up, down, front, back, left, right;

		// 주사위 초기 값
		public Dice() {
			up = 1;
			down = 6;
			front = 5;
			back = 2;
			left = 4;
			right = 3;
		}

		// 현재 이동 방향에 따라 굴렸을 때 결과가 다름
		void rollDice() {
			if (direct == 0)
				east();
			else if (direct == 1)
				south();
			else if (direct == 2)
				west();
			else
				north();
		}

		// 오른쪽으로 굴린 경우
		void east() {
			int temp = right;
			right = up;
			up = left;
			left = down;
			down = temp;
		}

		// 아래로 굴린 경우
		void north() {
			int temp = back;
			back = up;
			up = front;
			front = down;
			down = temp;
		}

		// 왼쪽으로 굴린 경우
		void west() {
			int temp = left;
			left = up;
			up = right;
			right = down;
			down = temp;
		}

		// 위로 굴린 경우
		void south() {
			int temp = front;
			front = up;
			up = back;
			back = down;
			down = temp;
		}
	}

	static void solution() {
		for (int i = 0; i < K; i++) {
			move();
			rotate();
		}
		System.out.println(score);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());

		N = init(st);
		M = init(st);
		K = init(st);

		map = new int[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	// 배열의 인덱스를 벗어나는지 확인
	static boolean index(int r, int c) {
		if (r > 0 && r <= N && c > 0 && c <= M) {
			return true;
		}
		return false;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
