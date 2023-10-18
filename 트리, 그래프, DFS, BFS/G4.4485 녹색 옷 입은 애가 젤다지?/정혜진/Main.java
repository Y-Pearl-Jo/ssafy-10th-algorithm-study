//17112KB 176ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int T = 0;
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			N = Integer.parseInt(br.readLine());

			if (N == 0)
				break;

			T++;
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(0, 0, map[0][0]);

		}
	}

	private static void bfs(int r, int c, int coin) {
		PriorityQueue<Point> q = new PriorityQueue();
		q.add(new Point(r, c, coin));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
					continue;
				}

				int ncoin = p.coin + map[nr][nc];
				q.add(new Point(nr, nc, ncoin));
				visited[nr][nc] = true;

				// 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동
				if (nr == N - 1 && nc == N - 1) {
					System.out.println("Problem " + T + ": " + ncoin);
				}

			}
		}

	}

}

class Point implements Comparable<Point> {
	int r, c, coin;

	public Point(int r, int c, int coin) {
		super();
		this.r = r;
		this.c = c;
		this.coin = coin;
	}

	@Override
	public int compareTo(Point o) {
		return this.coin - o.coin; // 오름차순. 링크가 잃을 수밖에 없는 최소 금액이니까
	}

}
