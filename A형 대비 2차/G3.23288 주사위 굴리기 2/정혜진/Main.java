//33008KB 188ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, d;
	static int[][] map;

	static int[] dr = { 0, 1, 0, -1 }; // 동남서북(시계방향)
	static int[] dc = { 1, 0, -1, 0 };
	static int[] dice = { 1, 2, 3, 4, 5, 6 }; // 주사위 면. 위 북 동 서 남 아래

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
		M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
		K = Integer.parseInt(st.nextToken()); // 주사위의 이동 횟수

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		d = 0;
		int nr = 0; // 시작 행 좌표
		int nc = 0; // 시작 열 좌표

		int score = 0; // 주사위가 이동하면서 얻은 점수의 합
		int count = 1;

		while (count++ <= K) {
			nr += dr[d];
			nc += dc[d];

			// 범위 밖이라면
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				// 다시 원위치시키고
				nr -= dr[d];
				nc -= dc[d];
				// 이동방향 반대로 해서 한칸 전진
				d = (d + 2) % 4;
				nr += dr[d];
				nc += dc[d];
			}

			dice(); // 주사위의 면 변화
			int A = dice[5]; // 주사위 아랫면 숫자

			int B = map[nr][nc]; // 주사위가 도착한 칸의 점수
			int C = bfs(nr, nc); // 연속해서 이동할 수 있는 칸의 수
			score += (B * C);

			// A랑 B랑 비교해서 다음 이동 방향 결정
			if (A > B) {
				d = (d + 1) % 4; // A>B -> 이동방향 90도 시계방향 회전
			} else if (A < B) { // A<B -> 이동방향 90도 반시계방향 회전
				d = (d + 3) % 4;
			} // A=B -> 이동방향 유지

		}

		System.out.println(score);
	}

	private static int bfs(int r, int c) {
		int moveCount = 1;
		boolean[][] visited = new boolean[N][M];
		Queue<Node> que = new LinkedList<>();
		visited[r][c] = true;
		que.offer(new Node(r, c));

		while (!que.isEmpty()) {
			Node n = que.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nr = n.getR() + dr[dir];
				int nc = n.getC() + dc[dir];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
					visited[nr][nc] = true;
					que.offer(new Node(nr, nc));
					moveCount++;
				}
			}
		}
		return moveCount;

	}

	// 주사위가 굴러가는 방향에 따라 주사위 면을 바꿔주는 메서드
	// 위(0) 북(1) 동(2) 서(3) 남(4) 아래(5)
	private static void dice() {
		// 동쪽
		if (d == 0) {
			int tmp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = tmp;
		}
		// 서쪽
		else if (d == 2) {
			int tmp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = tmp;
		}
		// 남쪽
		else if (d == 1) {
			int tmp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
		}
		// 북쪽
		else if (d == 3) {
			int tmp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
		}

	}
}

class Node {
	int r, c;

	public Node(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

	public int getR() {
		return r;
	}

	public int getC() {
		return c;
	}

}
