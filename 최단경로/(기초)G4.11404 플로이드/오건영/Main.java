import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int map[][];
	static int[][] dp;
	static int INF = 200000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];

		// 최솟값 해야되니 높은값
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = INF;
			}
		}

		// 자기 자신한테 가는거 0으로
		for (int i = 1; i <= N; i++) {
			map[i][i] = 0;
		}

		// map 배열에다가 버스 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			map[start][end] = Math.min(map[start][end], cost);
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][k] != INF && map[k][j] != INF) {
						if (map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == INF) {
					map[i][j] = 0;
				}
			}
		}
		print();
	}

	static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
