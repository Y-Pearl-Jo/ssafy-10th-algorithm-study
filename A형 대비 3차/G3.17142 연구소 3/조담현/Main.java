import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 바이러스의 위치인 모든 2 중에서 M개를 골라 조합으로 경우의 수를 구한다.
 * 2. M개의 바이러스를 시작점으로 BFS를 시작하여(배열 복사) 걸리는 시간을 측정하고, 모든 빈칸을 채웠을 경우 최소 시간을 구한다.
 */
public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N, M;
	static int[][] map;
	static boolean[] selected;
	static int min = Integer.MAX_VALUE;
	static Point[] startPoint;
	static List<Point> virusList = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		startPoint = new Point[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virusList.add(new Point(i, j));
				}
			}
		}
		selected = new boolean[virusList.size()];

		backTracking(0, 0);

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}
	}// main

	public static void backTracking(int start, int depth) {
		if (depth == M) {
			bfs();
			return;
		}

		for (int i = start; i < virusList.size(); i++) {
			if (!selected[i]) {
				selected[i] = true;
				startPoint[depth] = virusList.get(i);
				backTracking(i, depth + 1);
				selected[i] = false;
			}
		}
	}

	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int[][] temp = new int[N][N];
		for (Point p : startPoint) {
			queue.add(p);
			visited[p.x][p.y] = true;
			temp[p.x][p.y] = 0;
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Point now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nr = now.x + dr[d];
					int nc = now.y + dc[d];
					if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc] && map[nr][nc] != 1) {
						temp[nr][nc] = temp[now.x][now.y] + 1;
						if (temp[nr][nc] >= min && map[nr][nc] == 0)
							return;
						visited[nr][nc] = true;
						queue.add(new Point(nr, nc));
					}
				}
			}

		}

		checkVirus(temp);

	}

	public static void checkVirus(int [][] temp) {
		int time = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (temp[i][j]==0 && map[i][j] == 0) {
					return;
				}
				if (temp[i][j]!=0&& map[i][j]==0) {
					time = Math.max(time, temp[i][j]);
				}
			}
		}
		min = Math.min(min, time);
	}

}
