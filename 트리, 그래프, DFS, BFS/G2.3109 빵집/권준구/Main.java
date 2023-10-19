import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1 };

	static int N, M, cnt;
	static char[][] map;
	static boolean[][] visit;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'x') {
					visit[i][j] = true;
				}
			}
		}

		cnt = 0;
		for (int i = 0; i < N; i++) {
			flag = false;
			dfs(i, 0);
		}
		System.out.println(cnt);
	}

	public static void dfs(int r, int c) {
		if (c == M - 1) {
			cnt++;
			flag = true;// 파이프라인 완성했을 경우
			visit[r][c] = true;
			return;
		}
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + 1;
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc]) {
				continue;
			}
			dfs(nr, nc);

			if (flag) {
				// 하나 완성했을때, 방문했던 곳 표시, 다른곳 탐색할 필요x
				visit[r][c] = true;
				return;
			} else {
				// 완성하지 못했을 경우, 안되는 경로 표시
				visit[r][c] = true;
			}

		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(visit[i][j] + " ");
			}
			System.out.println();
		}
	}
}
