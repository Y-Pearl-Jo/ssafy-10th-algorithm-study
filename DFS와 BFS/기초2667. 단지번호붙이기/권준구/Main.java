import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int[][] map; // 지도
	static int N;
	static int cnt = 0; // 단지 안에 있는 집들 수 체크
	static boolean[][] visit; // 방문여부 체크를 위해
	static List<Integer> dange = new ArrayList<>(); // 단지들 각 각의 개수를 저장하기 위해 사용

	public static int dfs(int r, int c) {
		visit[r][c] = true; // 들어오자마자 방문했다고 바꿔주고

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				// 바뀐 위치 값이 1이고 false(방문하지않은곳)면 다시 들어가서 시작
				if (map[nr][nc] == 1 && visit[nr][nc] == false) {
					dfs(nr, nc);
					cnt++;
				}
			}

		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];

		// 배열채우기
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		// 지도 전체 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 현재 위치 값이 1이고, false라면
				if (map[i][j] == 1 && visit[i][j] == false) {
					cnt = 1; // 맨 처음이므로 +1,그리고 시작하자마자 1로 초기화 기능도 한다
					dfs(i, j);
					dange.add(cnt);
				}
			}
		}
		System.out.println(dange.size());
		Collections.sort(dange);
		for (int x : dange) {
			System.out.println(x);
		}

	}
}
