import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static boolean movement;

	static int[] dr = { 0, 1, 0, -1 }; // 좌하우상
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N x N 크기의 땅
		L = Integer.parseInt(st.nextToken()); // 두나라의 인구차이가 L명 이상
		R = Integer.parseInt(st.nextToken()); //// 두 나라의 인구차이가 R명 이하인 경우 국경선 열기
		map = new int[N][N];

		// 초기 인구 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0; // 인구이동이 발생하는 기간

		while (true) {
			movement = false;
			visited = new boolean[N][N];

			// 방문한 적 없으면 인구이동이 가능한지 검사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j);

					}
				}
			}

			// 인구이동이 더 이상 발생하지 않으면 while문 종료
			if (!movement)
				break;
			else
				count++;
		}

		System.out.println(count);
	}

	private static void bfs(int r, int c) {
		Queue<Point> que = new LinkedList<>();
		ArrayList<Point> union = new ArrayList<>(); // 연합을 저장할 배열 초기화

		visited[r][c] = true;
		que.add(new Point(r, c));
		union.add(new Point(r, c)); // 연합 리스트에 좌표 추가

		while (!que.isEmpty()) {
			Point p = que.poll();
			r = p.r;
			c = p.c;

			//4방탐색하면서 인접 국가와 국경선을 열지 말지 확인
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
					continue;
				}

				// 두 나라의 인구 차이가 L명 이상, R명 이하라면 국경선을 연다.
				int diff = Math.abs(map[nr][nc] - map[r][c]);
				if (diff >= L && diff <= R) {
					visited[nr][nc] = true;
					que.add(new Point(nr, nc));
					union.add(new Point(nr, nc)); // 연합 리스트에 좌표 추가

					movement = true; // 인구이동이 일어났음을 표시
				}

			}

		}

		// 국경선이 열린 뒤 연합을 이루는 각 칸의 인구수를 갱신한다.
		int total = 0; // 연합의 인구수
		for (int i = 0; i < union.size(); i++) {
			Point p = union.get(i);
			total += map[p.r][p.c];
		}

		// 연합을 이루는 각 칸의 인구수는 (연합의 인구수)/(연합을 이루고 있는 칸의 개수)
		for (int i = 0; i < union.size(); i++) {
			Point p = union.get(i);
			map[p.r][p.c] = total / union.size();
		}

	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}

class Point {
	int r, c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}
