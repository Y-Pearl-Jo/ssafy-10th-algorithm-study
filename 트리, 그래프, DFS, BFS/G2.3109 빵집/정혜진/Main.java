//51932KB	316ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static int count;
	static char[][] map;
	static boolean[][] visited;

	static int[] dr = { -1, 0, 1 }; // 오른쪽 위, 오른쪽, 오른쪽 아래
	static int[] dc = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visited = new boolean[R][C];

		// 맵 정보 입력
		for (int i = 0; i < R; i++) {
			String tmp = br.readLine();
			map[i] = tmp.toCharArray();
		}

		count = 0;

		// 각 행의 첫 번째 열에서 시작해서 dfs 호출
		for (int i = 0; i < R; i++) {
			if (dfs(i, 0)) // true를 반환하면 파이프라인 설치가 가능하다는 뜻
				count++; // 최대 파이프라인 개수 1 증가
		}

		System.out.println(count);
	}

	private static boolean dfs(int r, int c) {
		// 가스관(맨 끝열)까지 파이프를 설치하면 true를 반환
		// dfs 하면서 끝열을 여러번 방문해도 단 한번만 체킹하면 되니까 그냥 true 반환하는 거임
		if (c == C - 1) {
			return true;
		}

		// 재귀파트
		for (int d = 0; d < 3; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 범위 밖이거나 방문했거나(파이프설치했거나) 건물이면 패스
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == 'x') {
				continue;
			}

			// 방문처리하고 dfs 호출. 
			// 근데 만약에 dfs(nr,nc)가 true를 리턴하면 가스관에서부터 계속 true를 유지한 채, 거꾸로 타고타고 온 거임~ 
			// 이게 dfs호출 시작한 곳(메인)까지 유지가 되면 count값을 증가시킨다.
			visited[nr][nc] = true;
			if (dfs(nr, nc))
				return true;
//			visited[nr][nc] = false; // 이거 하면 똑같이 테케는 맞는데 4% 맞고 시간초과 뜬다. 단 한 번 가스관 도달하는 거 확인만하면 되니까 굳이 할 필요x

		}

		return false; // 중간에 건물로 막혔거나 이미 파이프가 설치되어 가스관까지 도달하지 못하면 false 리턴

	}

}
