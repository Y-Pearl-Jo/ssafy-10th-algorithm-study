import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;

	static int[][] dist, copyMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dist = new int[N + 1][N + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		copy(); // 복사

		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (i == j || j == k || i == k) {
						continue;
					}

					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						copyMap[i][j] = 0;
					}

					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						System.exit(0);
					}
				}
			}
		}

		int ans = 0;

		// 출력
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				ans += copyMap[i][j];
			}
		}

		System.out.println(ans / 2);

	}

	public static void copy() {
		copyMap = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				copyMap[i][j] = dist[i][j];
			}
		}
	}
}
