import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map; // 맵 배열
	static boolean[][] visited; // 방문 배열
	static ArrayList<Point> list; // 블록 그룹을 담는 리스트

	static int sum = 0;

	static int[] dr = { 0, 1, 0, -1 }; // 좌하우상
	static int[] dc = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 한 변의 크기
		M = Integer.parseInt(st.nextToken()); // 색상의 개수

		map = new int[N][N]; // 맵 배열 초기화

		// 블록 입력. 검은색 블록은 -1, 무지개 블록은 0, 일반 블록은 1~M 사이의 자연수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {

			visited = new boolean[N][N]; // 방문 배열 초기화
			list = new ArrayList<>(); // 블록 그룹 리스트 초기화

			// 크기가 가장 큰 블록 그룹을 찾기
			for (int i = 0; i < N; i++) { // 기준 블록은 무지개 블록이 아닌 블록 중에서 행의 번호가 가장 작은 블록,
				for (int j = 0; j < N; j++) { // 그러한 블록이 여러개면 열의 번호가 가장 작은 블록

					// 검은색 블록(-1)이거나 무지개 블록(0)이거나 이미 방문한 블록은 기준 블록이 될 수 없음
					if (map[i][j] <= 0 || visited[i][j])
						continue;

					// 기준 블록에서부터 bfs
					bfs(i, j, map[i][j]);

				}
			}

			// 우선순위대로 정렬
			Collections.sort(list);

			// 블록 그룹이 없다면 종료
			if (list.size() == 0)
				break;

			// 블록 그룹 제거
			delete();
//			System.out.println("제거");
//			print();

			// 중력
			gravity();
//			System.out.println("중력");
//			print();

			// 반시계 회전
			rotate();
//			System.out.println("회전");
//			print();

			// 중력
			gravity();

		}

		// 출력
		System.out.println(sum);

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.printf("%3d ", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	// 크기가 가장 큰 블록 그룹을 찾는 메서드
	private static void bfs(int r, int c, int color) {
		Queue<Point> q = new LinkedList<>();
		int size = 1;
		int rainbow = 0;
		visited[r][c] = true;
		q.add(new Point(r, c, 0, 0));

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				// 범위 밖이거나 방문 했었거나 검은 블록이면 패스
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == -1) {
					continue;
				}

				// 무지개 블록이면 방문 처리하고 큐에 추가
				if (map[nr][nc] == 0) {
					size++; // 전체 블록 수와
					rainbow++; // 무지개 블록 수도 하나 증가
					visited[nr][nc] = true;
					q.add(new Point(nr, nc, 0, 0));
				}
				// 일반 블록이면 기준 블록 색이랑 같을 때만 방문처리하고 큐에 추가
				else if (map[nr][nc] == color) {
					size++; // 전체 블록 수만 증가
					visited[nr][nc] = true;
					q.add(new Point(nr, nc, 0, 0));
				}

			}

		}
		// 블록 그룹이 1보다 크면 리스트에 추가
		if (size > 1) {
			list.add(new Point(r, c, size, rainbow));
		}
		
		// 근데 무지개 블럭은 또 방문해도 되니까 방문 처리 원상 복구 해줌
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					visited[i][j] = false;
			}
		}
	}

	// 블록 그룹 제거 (값을 -99로 바꾸기)
	private static void delete() {
		// 기준블럭이랑 일치하는 애들 싹 다 bfs해서 -99로 값 바꿔놔
		Queue<Point> q = new LinkedList<>();
		Point p = list.get(0); // 리스트에서 제일 앞에 정렬된 것(최대 크기 블롞)을 빼
		q.add(new Point(p.r, p.c, 0, 0)); // 큐에 추가

		// 기준 블럭 색
		int color = map[p.r][p.c];
		map[p.r][p.c] = -99; // 값을 제거

		// 점수 계산
		sum += (p.size * p.size);

		while (!q.isEmpty()) {
			Point p2 = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p2.r + dr[d];
				int nc = p2.c + dc[d];

				// 범위 밖이거나 검은 블록이면 패스
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == -1) {
					continue;
				}

				if (map[nr][nc] == 0 || map[nr][nc] == color) {
					map[nr][nc] = -99; // 값을 제거
					q.add(new Point(nr, nc, 0, 0));
				}
			}
		}

	}

	// 반시계로 90도 회전
	private static void rotate() {
		int[][] tmp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmp[i][j] = map[j][N - i - 1];
			}
		}
		map = tmp;

	}

	// 중력
	private static void gravity() {
		// 열 순회
		for (int j = 0; j < N; j++) {
			// 해당 열의 재일 아랫행에서 위로 순회
			for (int i = N - 1; i >= 0; i--) {
				// 현재 위치에 블록이 없는 경우 아래로 블록을 이동시킴
				if (map[i][j] == -99) {

					int nr = i;

					while (true) {
						nr--; // nr을 한 행 위로 이동시킴

						// 범위 밖이거나 검은색 블록을 만나면 종료
						if (nr < 0 || map[nr][j] == -1)
							break;

						// 빈칸(-99)이 아닌 블록을 만나면
						if (map[nr][j] != -99) {
							map[i][j] = map[nr][j]; // 현재 위치에 그 블록의 값을 집어넣고
							map[nr][j] = -99; // 블록을 빈칸으로 바꿈 (swap)
							break;
						}

					}
				}

			}
		}

	}

}

class Point implements Comparable<Point> {
	int r, c, size, rainbow;

	public Point(int r, int c, int size, int rainbow) {
		this.r = r;
		this.c = c;
		this.size = size;
		this.rainbow = rainbow;
	}

	@Override
	public int compareTo(Point o) {
		if (this.size == o.size) {
			if (this.rainbow == o.rainbow) {
				if (this.r == o.r) {
					return -(this.c - o.c); // 열 크기 내림차순
				}
				return -(this.r - o.r); // 행 크기 내림차순
			}
			return -(this.rainbow - o.rainbow); // 무지개 블록 개수 내림차순
		}
		return -(this.size - o.size); // 블록 크기 내림차순
	}
}
