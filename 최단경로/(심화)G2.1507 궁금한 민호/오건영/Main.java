import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static final int INF = 200000000;
	static int[][] dist, map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		// 최소거리가 저장된 dist 배열
		dist = new int[N + 1][N + 1];
		// 맵 정보가 저장될 map 배열
		map = new int[N + 1][N + 1];

		// dist와 map에 같은 값 저장
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = dist[i][j];
			}
		}

		// 플로이드 와셜처럼 진행
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					// 자기 자신한테 가는건 건들지 말자
					if (i == j || i == k || j == k) {
						continue;
					}
					// 만약에 다시 돌려서 최솟값이 갱신된다면 문제가 있는거임
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						return;
					}
					// 플로이드 와셜할때랑 반대로 생각하면 됨 i -> j 길이 없어도 i->k->j가 있으니까 INF로 바꿔서 길이 없다고 해놔도 최소로
					// 가는거에 문제 없음

					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						map[i][j] = INF;
					}
				}

			}
		}

		// 맵에 이쁘게 값이 들어가 있으면 INF 뻬고 다 더한 후 양방향 정보가 다 들어가 있으니 /2
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (map[i][j] == INF) {
					continue;
				}
				sum += map[i][j];
			}
		}
		System.out.println(sum / 2);

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
