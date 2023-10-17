import java.io.*;
import java.util.*;
// 메모리 372744 KB, 시간 1672 ms 
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K, map[][];
	static boolean visited[][][];
	static List<Wall> wall = new ArrayList<>();
	// 4방향 탐색 하, 좌, 우, 상
	static int[][] delta = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	static int escape() {
		Queue<Wall> q = new LinkedList<>();
		// 벽을 부순거는 0번과 1번을 구분해야해서 K + 1만큼 필요
		visited = new boolean[N][M][K + 1];
		// 시작 거리 = 1 시작하는 칸과 끝나는 칸 포함
		q.add(new Wall(0, 0, 1, 0));
		visited[0][0][0] = true;
		// 벽을 부순 횟수에 따라 최솟값 비교
		int min = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Wall cur = q.poll();
			// 현재 방문 좌표
			int r = cur.r;
			int c = cur.c;
			// 현재까지 소요 거리
			int dis = cur.dis;
			// 현재까지 벽을 부순 횟수
			int crack = cur.crack;
			// 끝점 도착 시 최솟값 확인
			if (r == N - 1 && c == M - 1) {
				min = Math.min(min, dis);
				continue;
			}
			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int dr = r + delta[i][0];
				int dc = c + delta[i][1];
				// 인덱스 범위 내
				if (index(dr, dc)) {
					// 다음 좌표가 벽인 경우
					if (map[dr][dc] == 1) {
						// 아직 벽을 부술 횟수가 남았고 아직 방문한 적 없는 경우
						if (crack < K && !visited[dr][dc][crack]) {
							visited[dr][dc][crack] = true;
							// 거리 1 증가, 벽 부순 횟수 1 증가
							q.add(new Wall(dr, dc, dis + 1, crack + 1));
						}
					}
					// 다음 좌표가 벽이 아닌 경우
					else {
						// 아직 방문하지 않은 곳
						if (!visited[dr][dc][crack]) {
							visited[dr][dc][crack] = true;
							// 거리만 1 증가
							q.add(new Wall(dr, dc, dis + 1, crack));
						}
					}
				}
			}
		}
		return min;
	}

	static class Wall {
		int r, c;
		int dis, crack;

		public Wall(int r, int c, int dis, int crack) {
			this.r = r;
			this.c = c;
			this.dis = dis;
			this.crack = crack;
		}
	}

	static void solution() {
		int min = escape();
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		K = init(st);

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M)
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
