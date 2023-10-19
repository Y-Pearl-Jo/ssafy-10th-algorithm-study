import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class ball {
		int x, y, dist;

		public ball(int r, int c, int dist) {
			this.x = r;
			this.y = c;
			this.dist = dist;
		}

	}

	static class ballGroup {
		int rx, ry, bx, by;

		public ballGroup(int rx, int ry, int bx, int by) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
		}
	}

	static int N, M;
	static char[][] map;
	static boolean[][][][] visit;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int result = -1;
	static int rx, ry, bx, by;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				} else if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}

		bfs(new ballGroup(rx, ry, bx, by));
		System.out.println(result);
	}

	static void bfs(ballGroup balls) {

		Queue<ballGroup> queue = new LinkedList<>();
		int time = 1;

		queue.add(balls);
		visit[balls.rx][balls.ry][balls.bx][balls.by] = true;

		while (!queue.isEmpty()) {

			int size = queue.size();
			while (size-- > 0) {
				ballGroup now = queue.poll();
				for (int i = 0; i < 4; i++) {
					ball red = move(now.rx, now.ry, i);
					ball blue = move(now.bx, now.by, i);

					if (map[blue.x][blue.y] == 'O') {
						continue;
					}
					if (map[red.x][red.y] == 'O') {
						result = time;
						return;
					}

					if (red.x == blue.x && red.y == blue.y) {
						if (red.dist > blue.dist) {
							red.x -= dr[i];
							red.y -= dc[i];
						} else {
							blue.x -= dr[i];
							blue.y -= dc[i];
						}
					}

					if (visit[red.x][red.y][blue.x][blue.y]) {
						continue;
					}
					visit[red.x][red.y][blue.x][blue.y] = true;
					queue.add(new ballGroup(red.x, red.y, blue.x, blue.y));
				}
			}
			time++;
			if (time > 10) {
				return;
			}
		}
	}

	private static ball move(int r, int c, int d) {

		int nr = r, nc = c;
		int dist = 0;
		while (true) {
			if (map[nr + dr[d]][nc + dc[d]] == '#' || map[nr][nc] == 'O') {
				break;
			}
			nr += dr[d];
			nc += dc[d];
			dist++;
		}

		return new ball(nr, nc, dist);
	}
}
