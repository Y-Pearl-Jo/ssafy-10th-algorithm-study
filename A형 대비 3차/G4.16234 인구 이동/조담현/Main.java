import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
연합을 해체하고, 모든 국경선을 닫는다.
 */

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int total = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		total = 0;

		while (true) {
			boolean moveable = false;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						boolean result = bfs(i, j);
						if (result)
							moveable = true;
					}
				}
			}

			if (!moveable)
				break;
			total++;
		}
		System.out.println(total);
	}

	public static boolean bfs(int r, int c) {
		boolean moveable = false;
		ArrayList<Point> united = new ArrayList<>();
		Queue<Point> queue = new LinkedList<>();
		visited[r][c] = true;
		Point start = new Point(r, c);
		queue.add(start);
		united.add(start);
		int people = map[r][c];
		while (!queue.isEmpty()) {
			Point now = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = now.x + dr[d];
				int nc = now.y + dc[d];

				if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
					if (Math.abs(map[now.x][now.y] - map[nr][nc]) >= L
							&& Math.abs(map[now.x][now.y] - map[nr][nc]) <= R) {
						visited[nr][nc] = true;
						Point next = new Point(nr, nc);
						queue.add(next);
						united.add(next);
						people += map[nr][nc];
					}
				}
			}
		}

		for (Point p : united) {
			map[p.x][p.y] = people / united.size();
		}
		if (united.size() != 1)
			moveable = true;
		return moveable;

	}

}
