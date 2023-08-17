import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 북,동,남,서
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int d;
	static int cnt = 0;

	public static void search(int r, int c, int[][] arr, int d) {
		for (int loop = 0; loop < 4; loop++) {
			
			arr[r][c] = 1; //자기 자리 청소
			
			d = (d + 3) % 4; // 반시계방향 돌리기
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 0 && nr < arr[0].length && nc >= 0 && nr < arr[0].length && arr[nr][nc] == 0) {
				cnt++;
				search(nr, nc, arr, d);
				return;
			}
		}

		int back = (d + 2) % 4;
		int br = r + dr[back];
		int bc = c + dc[back];

		if (br >= 0 && br < arr.length && bc >= 0 && bc < arr[0].length && arr[br][bc] != 1) {
			search(br, bc, arr, d);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 첫째 줄 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];

		// 둘째 줄 받기
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		// 행과 열 채우기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시작하는 곳, 자기자리 빈칸 채우고 시작
		if (arr[r][c] == 0) {
			cnt++;
			search(r, c, arr, d);
		}
		System.out.println(cnt);
	}
}
