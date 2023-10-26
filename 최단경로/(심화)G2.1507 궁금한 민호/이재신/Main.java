import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 : 11628 KB, 시간 : 84 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int INF = (int) 1e9;
	static int N, dist[][], before[][];

	public static boolean reverse_floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					// 중간에 거쳐가는 과정이 없는 친구들은 지나가자
					if (i == j || i == k || k == j) {
						continue;
					}

					// dist는 이미 알고리즘을 수행한 결과물인데 이럴 수 없음
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						return false;
					}

					// 조건문의 값이 true라면 알고리즘을 통해 초기화 된 값이라는 뜻
					if (dist[i][j] == dist[i][k] + dist[k][j]) {
						before[i][j] = INF;
					}
				}
			}
		}
		return true;
	}

	public static void solution() {
		if (!reverse_floyd())
			return;

		int result = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = i; j < N + 1; j++) {
				if (before[i][j] != INF && i != j) {
					result += before[i][j];
				}
			}
		}

		System.out.println(result);
	}

	public static void make() throws IOException {
		N = init();
		dist = new int[N + 1][N + 1];
		before = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				before[i][j] = dist[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) throws IOException {
		return Integer.parseInt(st.nextToken());
	}
}
