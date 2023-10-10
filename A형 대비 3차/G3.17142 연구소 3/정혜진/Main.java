import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map; // 맵 배열
	static List<Point> viruses = new ArrayList<>(); // 바이러스 위치를 저장하는 바이러스 리스트
	static Point[] active; // 활성화된 바이러스 배열

	static int emptySpace = 0;
	static int minTime = Integer.MAX_VALUE; // 최소 시간 결과

	static int[] dr = { 0, 1, 0, -1 }; // 좌하우상
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // N x N 크기의 연구소
		M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스의 개수

		map = new int[N][N]; // 맵 배열 초기화
		active = new Point[M]; // 활성화 바이러스 배열 초기화

		// 연구소의 상태 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] == 0) {
					emptySpace++; // 빈공간이 있으면 빈공간 수 증가
				} else if (map[i][j] == 2) {
					viruses.add(new Point(i, j, 0)); // 바이러스 위치를 리스트에 추가
				}
			}
		}

		// 출력
		if (emptySpace == 0) {
			System.out.println(0); // 초기 빈공간이 없다면 0 출력
		} else {
			dfs(0, 0); // 바이러스 선택 메서드 호출
			if (minTime == Integer.MAX_VALUE) {
				System.out.println(-1); // 바이러스 퍼트린 후에도 빈칸이 남아있다면 -1 출력
			} else {
				System.out.println(minTime); // 그렇지 않다면 최소시간 출력
			}
		}

	}

	// 비활성 바이러스 중에서 M개를 골라 활성 바이러스로 만드는 조합 메서드
	private static void dfs(int depth, int start) {
		if (depth == M) {
			bfs(emptySpace);
			return;
		}

		for (int i = start; i < viruses.size(); i++) {
			active[depth] = viruses.get(i); // 활성 바이러스 배열에 바이러스 저장
			dfs(depth + 1, i + 1);
		}

	}

	// 활성 바이러스가 확산되는 메서드
	private static void bfs(int empty) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		// 활성 바이러스들을 시작 노드로 해서 큐에 저장
		for (int i = 0; i < M; i++) { // 활성바이러스는 M개
			Point p = active[i];
			visited[p.r][p.c] = true; // 방문처리
			q.add(p); // 큐에 추가
		}

		// 큐가 빌 때까지 반복
		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}

				// 빈공간이라면 빈공간 개수 하나 감소
				if (map[nr][nc] == 0) {
					empty--;
				}

				// 빈공간이 0개가 되었다면 그때까지의 최소 시간을 갱신하고 종료
				if (empty == 0) {
					minTime = Math.min(minTime, p.time + 1);
				}

				visited[nr][nc] = true; // 방문처리
				q.add(new Point(nr, nc, p.time + 1)); // 시간 하나 증가 시켜서 큐에 추가

			}

		}

	}

}

class Point {
	int r, c, time;

	Point(int r, int c, int time) {
		this.r = r;
		this.c = c;
		this.time = time;
	}
}
