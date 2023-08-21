import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 세로 R
	// 가로 C
	// 1행1열에 말이 있다
	// 한번지나간 칸은 못지나간다.
	// 조합과 순열 (A,B) (B,A)
	static int[] dr = { -1, 1, 0, 0 }; // 북,남,동,서
	static int[] dc = { 0, 0, 1, -1 };
	static boolean[] visit = new boolean[26];
	static int[][] map;
	static int ans = 0;

	public static void dfs(int r, int c, int cnt) { // 얼마나 이동했는지 알아보기 위해 depth와 같은 개념으로 cnt를 입력
		if (visit[map[r][c]]) { // ()안에 값이 true이면(더 이상 이동할 곳이 없다면)
			ans = Math.max(cnt, ans); // 정답 갱신
			return;
		} else {
			visit[map[r][c]] = true; // 방문했다고 체크하기
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr >= 0 && nc >= 0 && nr < map.length && nc < map[0].length) {
					dfs(nr, nc, cnt + 1);
				}
			}
			visit[map[r][c]] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		// 배열에 넣기
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - 'A';
			}
		}
		// (0,0)부터 시작하기 cnt는 1(원래는 depth)
		dfs(0, 0, 0);

		System.out.println(ans);
	}
}
