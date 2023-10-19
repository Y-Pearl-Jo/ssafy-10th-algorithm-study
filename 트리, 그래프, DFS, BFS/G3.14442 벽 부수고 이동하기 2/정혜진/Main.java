//	339484KB	3004ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;
	static int res;

	static int[] dr = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken()); // 벽을 K개 까지 부수고 이동가능

		map = new int[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1][K + 1]; //메모리아끼자..
		

		// 맵 정보 입력
		for (int i = 1; i <= N; i++) {
			String tmp = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = tmp.charAt(j - 1) - '0';
			}
		}

		res = Integer.MAX_VALUE;

		// (1, 1)에서 (N, M)의 위치까지 이동
		bfs(1, 1);

		// 출력
		if (res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}

	}

	private static void bfs(int r, int c) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(r, c, 1, 0));

		while (!pq.isEmpty()) {
			Point p = pq.poll();

			// (N,M) 도착
			if (p.r == N && p.c == M) {
				res = Math.min(res, p.dist);
				return;
			}

			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];
				int ndist = p.dist + 1;
				int nwall = p.wall;

				// 범위 밖이거나 이미 방문했던 곳이면 패스
				if (nr <= 0 || nr > N || nc <= 0 || nc > M) {
					continue;
				}

				// 벽이 아니면
				if (map[nr][nc] == 0) {
					// 여태 벽을 부순 적이 없었으면
					if (!visited[nr][nc][nwall]) { //현재까지 부순 벽의 개수를 인덱스로 표시
						visited[nr][nc][nwall] = true; // 현재 좌표 방문 표시
						pq.add(new Point(nr, nc, ndist, nwall)); // 큐에 좌표와 최단거리, 부순벽의 개수를 저장
					}

				}
				// 벽이면
				else if (map[nr][nc] == 1) {
					// 아직 부순 벽의 개수가 K개보다 적다면
					if (nwall < K && !visited[nr][nc][nwall]) {
						visited[nr][nc][nwall] = true; // 현재 좌표에서 이전에 부순 벽이 있음을 표시
						pq.add(new Point(nr, nc, ndist, nwall + 1)); // 큐에 좌표와 최단거리, 부순벽의 개수를 저장
					}
				}
			}
		}
	}
}

class Point implements Comparable<Point> {
	int r, c, dist, wall;
	// wall은 현재까지 부순 벽의 수

	public Point(int r, int c, int dist, int wall) {
		super();
		this.r = r;
		this.c = c;
		this.dist = dist;
		this.wall = wall;
	}

	@Override
	public int compareTo(Point o) {
		return this.dist - o.dist;
	}

}
