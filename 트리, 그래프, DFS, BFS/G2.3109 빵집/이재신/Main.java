import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 36828 KB, 시간 320 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, cnt = 0;
	static char[][] map;
	static boolean[][] check;
	// 우측으로만 이동하기 때문에 column은 항상 1씩 증가할 거임
	// 도착을 위에서부터 하도록 상, 중, 하
	static int[] dr = { -1, 0, 1 };

	static boolean dfs(int row, int col) {
		// 이동 할 수 있는 경우의 수 3가지
		for (int i = 0; i < 3; i++) {
			int nr = row + dr[i];
			int nc = col + 1;
			// 인덱스 범위 내 파이프가 설치되지 않은 빈칸
			if (index(nr, nc) && map[nr][nc] == '.') {
				// 마지막 열에 도착
				if (nc == M - 1) {
					// 연결된 파이프 개수 증가
					cnt++;
					// 더 이상 이번 타임의 dfs가 실행되지 않도록 return 해줌
					return true;
				}
				
				// 파이프 설치 표현
				// 한 번 실패한 위치는 나중에 다시 방문해도 실패이므로
				// 다시 원래대로 안바꾼느게 시간 절약
				map[nr][nc] = '0';
				if (dfs(nr, nc))
					return true;
			}

		}
		return false;
	}

	static void solution() {
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}
		System.out.println(cnt);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		map = new char[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= M) {
			return false;
		}
		return true;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
