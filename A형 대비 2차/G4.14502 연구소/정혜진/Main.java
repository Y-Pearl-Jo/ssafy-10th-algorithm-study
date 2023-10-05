//0은 빈 칸, 1은 벽, 2는 바이러스

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] visited; // bfs 방문여부 저장 배열

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int maxSafeZone = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);
		
		System.out.println(maxSafeZone);

	}

	private static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	// 벽 3개 세우는 메서드
	private static void dfs(int depth) {
		// 기저파트
		if (depth == 3) { // 벽을 세 개 세우면 종료하고 bfs호출
			bfs();
			return;
		}

		// 재귀파트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 순회하다가 빈 공간이 나오면
				if (map[i][j] == 0) {
					map[i][j] = 1; // 벽을 하나 세우고
					dfs(depth + 1); // 다음자리에 벽을 하나 세우기 위해 dfs호출
					map[i][j] = 0;	// 재귀가 끝난 뒤에는 원상복구
				}

			}
		}

	}

	// 바이러스 확산하는 메서드
	private static void bfs() {
		Queue<Node> que = new LinkedList<>();

		// 순회하다가 바이러스 발견하면 좌표를 큐에 추가
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					que.add(new Node(i, j));
				}
			}
		}

		// (주의!) 원본 map배열을 바꾸지 않기 위해서 깊은 복사로 다른 배열을 만들어서 사용
		int copyMap[][] = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			copyMap[i] = map[i].clone();
		}

		// 큐가 빌 때까지 bfs
		while (!que.isEmpty()) {
			Node p = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.getX() + dr[d];
				int nc = p.getY() + dc[d];

				// 범위 내이고, 빈 공간이라면
				if (nr >= 0 && nr < N && nc >= 0 && nc < M && copyMap[nr][nc] == 0) {
					copyMap[nr][nc] = 2; // 바이러스 전파
					que.add(new Node(nr, nc));
				}
			}
		}

		// 바이러스를 퍼트린 후 안전영역의 크기를 확인
		safeZone(copyMap);

	}

	// 얻을 수 있는 안전영역의 최대 크기를 구하는 메서드
	private static void safeZone(int[][] copyMap) {
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) {
					count++;
				}
			}
		}

		maxSafeZone = Math.max(maxSafeZone, count);
	}
}

class Node {
	int x, y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
