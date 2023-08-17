import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 로봇의 row 좌표
		int c = Integer.parseInt(st.nextToken()); // 로봇의 col 좌표
		int dir = Integer.parseInt(st.nextToken()); // 로봇이 바라보는 방향 0:북/1:동/2:남/3:서
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		// 0은 청소되지 않음 1은 벽 2는 청소됨
		int[][] room = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 청소하는 칸 수
		int count = 0;
		while (true) {
			
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (room[r][c] == 0) {
				room[r][c] = 2;
				count++;
			}

			// 현재 칸의 주변 4칸중 청소되지 않은 빈 칸이 있는지 확인
			boolean isEmpty = false;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && room[nr][nc] == 0) {
					isEmpty = true;
				}
			}
			
			// 2. 현재 칸의 주변 4칸중 청소되지 않은 빈 칸이 없는 경우
			if (!isEmpty) {
				int back = (dir + 2) % 4;
				int nr = r + dr[back];
				int nc = c + dc[back];
				// 2-1. 바라보는 방향을 유지한 채로 한칸 후진할 수 있다면 한칸 후진하고 1번으로 돌아간다.
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && room[nr][nc] != 1) {
					r = nr;
					c = nc;
					continue;
				}
				
				// 2-2. 바라보는 방향의 뒤쪽칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && room[nr][nc] == 1) {
					break;
				}
			}
			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 있는 경우
			if(isEmpty) {
				// 3-1. 반시계 방향으로 회진한다.
				dir = (dir+3)%4;
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈칸인 경우 한칸 전진한다.
				if (nr >= 0 && nc >= 0 && nr < N && nc < M && room[nr][nc] == 0) {
					r = nr;
					c = nc;
				}
				continue;
			}
		}
		
		System.out.println(count);
	}

}
