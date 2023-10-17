import java.io.*;
import java.util.*;
// 메모리 11680 KB, 시간 80 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Hole hole;
	static Marble blue, red;
	static int N, M;
	static char[][] map;
	static boolean[][][][] visited;
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static int bfs() {
		// 빨, 파 각각의 정보를 가지고 있어야 하므로 ball 클래스를 만들어 배열로 저장
		Queue<Marble[]> queue = new LinkedList<>();
		// 빨간 구슬이 ball[0], 파란 구슬이 ball[1]
		queue.add(new Marble[] { new Marble(red.r, red.c, 1), new Marble(blue.r, blue.c, 1) });
		// 빨강 파랑 각각의 인덱스를 위해 4차원 배열
		visited[red.r][red.c][blue.r][blue.c] = true;
		while (!queue.isEmpty()) {
			Marble[] marble = queue.poll();
			// 빨강 위치
			int rR = marble[0].r;
			int rC = marble[0].c;
			// 파랑 위치
			int bR = marble[1].r;
			int bC = marble[1].c;
			// 이동 횟수
			int cnt = marble[0].cnt;
			// 10번 내로 불가능하면 -1 출력
			if (cnt > 10) {
				return -1;
			}
			// 4방향으로 기울여 보기
			for (int i = 0; i < 4; i++) {
				int drR = rR;
				int drC = rC;
				int dbR = bR;
				int dbC = bC;

				boolean isRed = false, isBlue = false;
				// 빨간 구슬이 이동방향 중에 벽을 만날 때까지 위치 조정
				while (map[drR + delta[i][0]][drC + delta[i][1]] != '#') {
					drR += delta[i][0];
					drC += delta[i][1];
					// 이동 과정 중에 구멍이 있으면 탈출 여부 true
					if (drR == hole.r && drC == hole.c) {
						isRed = true;
						break;
					}
				}
				// 파란 구슬이 이동방향 중에 벽을 만날 때까지 위치 조정
				while (map[dbR + delta[i][0]][dbC + delta[i][1]] != '#') {
					dbR += delta[i][0];
					dbC += delta[i][1];
					// 이동 과정 중에 구멍이 있으면 탈출 여부 true
					if (dbR == hole.r && dbC == hole.c) {
						isBlue = true;
						break;
					}
				}
				// 파랑이 탈출했으면 더 볼 필요도 없음
				if (isBlue) {
					continue;
				}
				// 빨강만 탈출한 경우 현재까지 기울인 획수 return
				if (isRed && !isBlue) {
					return cnt;
				}
				// 기울였을 때 둘의 위치가 같다면 이전의 위치에 따라 옆으로 옮겨줘야 함
				if (drR == dbR && drC == dbC) {
					// 위쪽으로 기울이기
					if (i == 0) {
						if (rR > bR)
							drR++;
						else
							dbR++;
					}
					// 아래쪽으로 기울이기
					else if (i == 2) {
						if (rR < bR)
							drR--;
						else
							dbR--;
					}
					// 오른쪽으로 기울이기
					else if (i == 1) {
						if (rC < bC)
							drC--;
						else
							dbC--;
					}
					// 왼쪽으로 기울이기
					else {
						if (rC > bC)
							drC++;
						else
							dbC++;
					}
				}
				// 다음 큐에 추가 합시다.
				if (!visited[drR][drC][dbR][dbC]) {
					visited[drR][drC][dbR][dbC] = true;
					// 카운트는 빨강에서만 꺼내니까 굳이 파랑은 추가하지 말자
					queue.add(new Marble[] { new Marble(drR, drC, cnt + 1), new Marble(dbR, dbC, cnt) });
				}
			}
		}
		// 다해봤는데 탈출 못했으면 -1임
		return -1;
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		map = new char[N][M];
		visited = new boolean[N][M][N][M];

		// 구슬 map 구성
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				// 구멍 발견!
				if (map[i][j] == 'O') {
					hole = new Hole(i, j);
				}
				// 파랑 발견!
				else if (map[i][j] == 'B') {
					blue = new Marble(i, j, 0);
				}
				// 빨강 발견!
				else if (map[i][j] == 'R') {
					red = new Marble(i, j, 0);
				}
			}
		}
	}

	static class Marble {
		int r;
		int c;
		int cnt;

		Marble(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static class Hole {
		int r;
		int c;

		public Hole(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		make();
		System.out.println(bfs());
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
