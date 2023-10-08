import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// 출력 결과 : 최소 시간
	static int min = Integer.MAX_VALUE;
	// 배열 크기 및 활성화 개수
	static int N, M;
	static int empty = 0;
	static int[][] map, sec;
	static Virus[] active;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static ArrayList<Virus> virus = new ArrayList<>();

	// 조합을 활용한 바이러스 선택
	static void selectVirus(int depth, int cnt) {
		// 활성화할 바이러스 M개 선택
		if (depth == M) {
			// 임시 시간
			int temp = spreadVirus();
			// 빈 칸에 모두 퍼지는게 가능한 경우 비교
			if (temp != -1)
				min = Math.min(min, spreadVirus());
			return;
		}

		// 조합
		for (int i = cnt; i < virus.size(); i++) {
			active[depth] = virus.get(i);
			selectVirus(depth + 1, i + 1);
		}
	}
	
	// 바이러스 확산
	static int spreadVirus() {
		Queue<Virus> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		// 이번 경우의 수에서 걸리는 시간
		int tempMax = 0;
		// 모든 칸 확산되는지 확인
		int emptyCnt = 0;

		// 활성화 된 바이러스 큐에 저장
		for (Virus v : active) {
			visited[v.x][v.y] = true;
			q.offer(v);
		}

		// BFS 탐색 시작
		while (!q.isEmpty()) {
			Virus cur = q.poll();
			int r = cur.x;
			int c = cur.y;

			for (int j = 0; j < 4; j++) {
				int nR = r + dr[j];
				int nC = c + dc[j];

				// 배열 범위 안이고 방문 전인 곳
				if (index(nR, nC) && !visited[nR][nC]) {
					// 빈 칸일 때
					if (map[nR][nC] == 0) {
						emptyCnt++;
						visited[nR][nC] = true;
						q.add(new Virus(nR, nC, cur.s + 1));
						tempMax = Math.max(tempMax, cur.s + 1);
					}
					// 비활성 바이러스일 때
					else if (map[nR][nC] == 2) {
						visited[nR][nC] = true;
						q.add(new Virus(nR, nC, cur.s + 1));
					}
				}
			}
		}
		// 모두 확산시키지 못한 경우
		if (emptyCnt != empty)
			return -1;
		return tempMax;
	}

	static void solution() {
		selectVirus(0, 0);
		System.out.println(min != Integer.MAX_VALUE ? min : -1);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		map = new int[N][N];
		active = new Virus[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = init(st);

				// 빈 칸의 개수 확인
				// 모든 빈 칸에 바이러스 퍼지는지 확인 필요
				if (map[i][j] == 0)
					empty++;
				// 바이러스
				else if (map[i][j] == 2)
					virus.add(new Virus(i, j, 0));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static class Virus {
		// r 좌표, c 좌표, 걸린 시간
		int x, y, s;

		public Virus(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}

	static boolean index(int r, int c) {
		if (0 <= r && r < N && 0 <= c && c < N) {
			return true;
		}
		return false;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
