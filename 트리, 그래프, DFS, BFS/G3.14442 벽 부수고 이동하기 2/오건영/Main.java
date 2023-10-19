import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int dist;
	int wall;

	public Point(int x, int y, int dist, int wall) {
		super();
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.wall = wall;
	}

}

public class Main {
	static int N, M, K;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][][] visit;
	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M][K + 1];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs(new Point(0, 0, 1, 0));
		System.out.println(result);

	}

	static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(p);
		visit[p.x][p.y][p.wall] = true;
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			if (now.x == N - 1 && now.y == M - 1) {
				result = now.dist;
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];
				int ndist = now.dist + 1;
				int nwall = now.wall;

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc][nwall]) {
					continue;
				}

				if (map[nr][nc] == 0 && !visit[nr][nc][nwall]) {
					visit[nr][nc][nwall] = true;
					queue.add(new Point(nr, nc, ndist, nwall));
				}
				if (map[nr][nc] == 1 && !visit[nr][nc][nwall]) {
					if (nwall < K) {
						visit[nr][nc][nwall] = true;
						queue.add(new Point(nr, nc, ndist, nwall + 1));
					}
				}
			}
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
