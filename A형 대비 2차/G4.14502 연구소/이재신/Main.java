import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Integer> num = new ArrayList<>();
	static ArrayList<Character> operate = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	static int N, M;
	static int[][] map, temp;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	static void dfs(int depth) {
		// 벽이 3개가 설치 된 경우
		if (depth == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(depth + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					q.add(new Point(i, j));
				}
			}
		}

		// 원본 연구소를 바꾸지 않기 위한 카피맵 사용
		temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			temp[i] = map[i].clone();
		}

		// BFS 탐색 시작
		while (!q.isEmpty()) {
			Point cur = q.poll();
			int r = cur.x;
			int c = cur.y;

			for (int j = 0; j < 4; j++) {
				int nR = r + dr[j];
				int nC = c + dc[j];

				// 배열 범위 안이고 빈칸인 경우 바이러스 확산
				if (index(nR, nC) && temp[nR][nC] == 0) {
					q.add(new Point(nR, nC));
					temp[nR][nC] = 2;
				}
			}
		}

		// 안전 영역 크기 확인
		safe();
	}

	static void safe() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 0) {
					cnt++;
				}
			}
		}
		max = Math.max(max, cnt);
	}

	static void solution() {
		dfs(0);
		System.out.println(max);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (0 <= r && r < N && 0 <= c && c < M) {
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
