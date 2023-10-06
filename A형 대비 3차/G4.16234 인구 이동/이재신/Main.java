import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, R, sum;
	static int day = 0;
	static int[][] people;
	static boolean flag = true;
	static boolean[][] visited;
	static ArrayList<Point> open;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	// 국가들의 연합을 구하기 위한 메서드
	// BFS를 이용하여 구현
	static void eachUnion(int row, int col) {
		Queue<Point> q = new LinkedList<>();
		// 국경선 닫음
		open = new ArrayList<>();
		q.add(new Point(row, col));
		open.add(new Point(row, col));
		visited[row][col] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int r = cur.x + dr[i];
				int c = cur.y + dc[i];

				if (index(r, c) && !visited[r][c]) {
					int diff = Math.abs(people[cur.x][cur.y] - people[r][c]);
					// 인접한 두 나라의 인구 차이가 L <= 차이 <= R 이면
					if (L <= diff && diff <= R) {
						// 연합의 인구수
						sum += people[r][c];
						// 이어서 확인해야 하니까
						q.add(new Point(r, c));
						// 국경선을 오픈
						open.add(new Point(r, c));
						visited[r][c] = true;
					}
				}
			}
		}
	}

	// 모든 국경선을 확인했다면 인구이동 시작
	static void peopleMove() {
		int move = sum / open.size();
		// 국경선이 열려있어 연결 된 칸 = 연합
		for (Point p : open)
			// 연합의 인구수 조정 (연합의 인구수) / (연합 국가의 개수)
			people[p.x][p.y] = move;
	}

	static void union() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					sum = people[i][j];
					eachUnion(i, j);

					if (open.size() > 1) {
						peopleMove();
						flag = true;
					}
				}
			}
		}
	}

	static void solution() {
		while (flag) {
			flag = false;
			visited = new boolean[N][N];
			union();
			if (flag)
				day++;
		}
		System.out.println(day);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		L = init(st);
		R = init(st);

		people = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				people[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		return true;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
