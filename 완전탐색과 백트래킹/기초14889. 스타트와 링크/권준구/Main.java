import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int MIN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1]; // 1번부터 시작하니까 맞춰주기
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		MIN = Integer.MAX_VALUE;
		Combination(N / 2, 1, new boolean[N + 1]);
		System.out.println(MIN);
	}

	// 조합코드
	public static void Combination(int toChoise, int startIdx, boolean[] visited) {
		// 기저조건
		if (toChoise == 0) {
//			System.out.println(Arrays.toString(visited));
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (visited[i] == visited[j]) {
						if (visited[i]) {
							sum += map[i][j];
						} else {
							sum -= map[i][j];
						}
					}
				}
			}
			MIN = Math.min(MIN, Math.abs(sum));
			return;
		}
		// 재귀상태
		for (int i = startIdx; i <= N; i++) {
			visited[i] = true; // 선택
			Combination(toChoise - 1, i + 1, visited);// 선택했으니까 하나 줄어들고
			visited[i] = false;
		}

	}
}
