//130924KB	332ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = { -1, 0, 1, 0 }; // 상 부터 시계방향
	static int[] dy = { 0, 1, 0, -1 };

	static int R, C, T;
	static int[][] room;
	static Point[] airCleaner;

	static Queue<Point> que = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 방의 크기 입력
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		room = new int[R + 1][C + 1];
		airCleaner = new Point[2]; // airCleaner[0] : 위, airCleaner[1] : 아래
		int idx = 0;

		// 미세먼지 위치 저장
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());

				// 공기 청정기 위치 저장
				if (room[i][j] == -1) {
					airCleaner[idx] = new Point(i, j, 0);
					idx++;
				}
			}
		}

		// T초만큼 반복
		for (int i = 0; i < T; i++) {
			dust(); // 미세먼지 확산
//			print();

			wind(airCleaner[0], airCleaner[1]); // 공기청정기 바람 순환
//			print();
		}

		// 미세먼지의 합 계산
		int ans = 0; // T초 후 미세먼지의 합
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (room[i][j] == -1) {
					continue;
				}

				ans += room[i][j];
			}
		}
		System.out.println(ans);

	}

	// 출력 확인 메소드
	public static void print() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				System.out.print(room[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// 미세먼지 확산 메소드. bfs랑 비슷
	public static void dust() {
		// 미세먼지 위치(BFS 시작노드들)를 모두 큐에 추가
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (room[i][j] > 0) {
					que.add(new Point(i, j, room[i][j]));
				}
			}
		}

		// 큐 빌 때까지 반복
		while (!que.isEmpty()) {
			// 큐에서 먼지 위치 꺼냄
			Point p = que.poll();
			int diffusion = 0; // 확산된 먼지의 양(총 감소하는 양)

			// 4방향 탐색(미세먼지는 4방으로 확산됨)
			for (int i = 0; i < 4; i++) {
				int nx = p.getX() + dx[i];
				int ny = p.getY() + dy[i];

				// 범위 밖이거나 공기청정기면 다음 위치 탐색
				if (nx <= 0 || nx > R || ny <= 0 || ny > C || room[nx][ny] == -1) {
					continue;
				}

				room[nx][ny] += p.dusts / 5; // 미세먼지 주변 좌표로 미세먼지 확산
				diffusion += p.dusts / 5; // 총 확산된 먼지의 양 계산

			}

			// 원래 미세먼지가 있던 위치에서 총 확산된 먼지의 양만큼 빼준다
			room[p.getX()][p.getY()] -= diffusion;

		}
		// 큐를 비워줌
		que.clear();

	}

	// 공기청정기 바람 순환 메소드
	public static void wind(Point up, Point down) {
		// 위쪽 순환 : 순환하는 방향의 역순(시계방향)부터 시작해서 값을 이동시킨다
		int d = 0;
		int x = up.getX();
		int y = up.getY();

		// 다시 공기 청정기가 나타날 때까지 (순환 한바퀴 돌 때까지) 반복
		while (true) {

			int nx = x + dx[d];
			int ny = y + dy[d];

			// 범위 밖이면 방향 바꿔줌
			if (nx <= 0 || nx > up.getX() || ny <= 0 || ny > C) {
				d = (d + 1) % 4;
				continue;
			}
			if (room[x][y] == -1) {
				// 공기청정기에서 순환 시작

			} else if (room[nx][ny] == -1) {
				// 한바퀴 순환 끝내고 다시 공기청정기가 나타남 -> 공기청정기 위치에는 미세먼지 0
				room[x][y] = 0;
				break;

			} else {
				// 다음 위치의 미세먼지 값을 현재 위치로 땡김
				room[x][y] = room[nx][ny];

			}

			x = nx;
			y = ny; // 값 갱신해서 다다음칸을 다음칸으로 땡김~

		}

		// 아래쪽 순환 : 순환하는 방향의 역순(반시계방향)부터 시작해서 값을 이동시킨다
		d = 3;
		x = down.getX();
		y = down.getY();

		// 다시 공기 청정기가 나타날 때까지 (순환 한바퀴 돌 때까지) 반복
		while (true) {

			int nx = x + dx[d];
			int ny = y + dy[d];

			// 범위 밖이면 방향 바꿔줌
			if (nx < down.getX() || nx > R || ny <= 0 || ny > C) {
				d = d - 1;
				if (d < 0) {
					d = 3;
				}
				continue;
			}
			if (room[x][y] == -1) {
				// 공기청정기에서 순환 시작
				
			} else if (room[nx][ny] == -1) {
				// 한바퀴 순환 끝내고 다시 공기청정기가 나타남 -> 공기청정기 위치에는 미세먼지 0
				room[x][y] = 0;
				break;

			} else {
				// 다음 위치의 미세먼지 값을 현재 위치로 땡김
				room[x][y] = room[nx][ny];

			}

			x = nx;
			y = ny; // 값 갱신해서 다다음칸을 다음칸으로 땡김~

		}
		// 큐를 비워줌
		que.clear();

	}

}

class Point {
	int x, y;
	int dusts;

	public Point(int x, int y, int dusts) {
		super();
		this.x = x;
		this.y = y;
		this.dusts = dusts;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
