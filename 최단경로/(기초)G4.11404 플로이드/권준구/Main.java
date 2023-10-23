import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static final int INF = 200000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 도시의 개수
		N = Integer.parseInt(br.readLine());
		// 버스의 개수
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];

		// 초기화
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j) {
					continue;
				} else {
					map[i][j] = INF;
				}
			}
		}

		// 입력값 받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[a][b] = Math.min(map[a][b], c);
		}

		// 폴로이드
		// 도중에 거치는 노드
		for (int k = 1; k < N + 1; k++) {
			// 출발 노드
			for (int i = 1; i < N + 1; i++) {
				// 도착 노드
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
