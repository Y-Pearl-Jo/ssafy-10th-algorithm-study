import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, wallCount;

		public Point(int r, int c, int wallCount) {
			this.r = r;
			this.c = c;
			this.wallCount = wallCount;
		}

	}

	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, M, K, cnt;
	static boolean isOk;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		bfs();

		System.out.println(isOk ? cnt : -1);

	}

	public static void bfs() {
		boolean[][][] visit = new boolean[N][M][K + 1]; // 방문 여부 및 벽 부순 횟수를 저장하는 배열
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, 0)); // 시작점을 큐에 추가
		visit[0][0][0] = true;

		cnt = 1;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {

				Point p = queue.poll();

				if (p.r == N - 1 && p.c == M - 1) {
					isOk = true;
					return; // 도착 지점에 도달한 경우 이동 횟수 반환
				}

				for (int d = 0; d < 4; d++) {
					int nr = p.r + dr[d];
					int nc = p.c + dc[d];

					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue; // 범위를 벗어나는 경우 무시
					}

					// 벽이 아니고 방문한 적이 없는 경우
					if (map[nr][nc] == 0 && !visit[nr][nc][p.wallCount]) {
						queue.add(new Point(nr, nc, p.wallCount));
						visit[nr][nc][p.wallCount] = true;
					}

					// 벽이고, 벽을 K번 미만으로 부순 경우, 방문한 적이 없는 경우
					if (map[nr][nc] == 1 && p.wallCount < K && !visit[nr][nc][p.wallCount + 1]) {
						queue.add(new Point(nr, nc, p.wallCount + 1));
						visit[nr][nc][p.wallCount + 1] = true;
					}

				}
			}
			cnt++;
		}
	}
}
