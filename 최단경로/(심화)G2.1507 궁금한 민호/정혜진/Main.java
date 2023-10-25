//11588KB 80ms

// 아이디어 : 결국 구하고자 하는 것은 도로 시간의 합이므로 i에서 j로 '바로' 가지 않고, 다른 노드들을 거쳐간 경우는 '도로 시간의 합(res)'에 더해주지 않을 거임

// i --(6)--> j : 도로 1개를 지남! res에 도로의 시간 6울 더해줌
// i --(6)--> k --(9)--> j : 도로 2개를 지남! res에 도로의 시간 15 를 더하지 않고 넘어감

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		// 플로이드 배열 입력 : 모든 쌍의 도시 사이의 최소 이동 시간
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int res = 0; // 도로 개수가 최소일 때, 모든 도로의 시간의 합

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					// i에서 j로 바로 가는 경우는 체킹하면 안되니까 걸러줌
					if (i == k || k == j) {
						continue;
					}

					// i에서 j로 '바로'가는 경로보다 다른 노드를 거쳐온 경로의 시간이 적은 경우
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						// -1 출력
						System.out.println("-1");
						return;
					}

					// i에서 j로 '바로'가는 경로랑 다른 노드를 거쳐온 경로의 시간이 같은 경우
					if (arr[i][j] == arr[i][k] + arr[k][j]) {
						// 체킹해줌
						visited[i][j] = true;
					}
				}
			}
		}

		// 끝까지 갱신 안된 경우 res 에 더해줌
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (!visited[i][j])
					res += arr[i][j];
			}
		}
		System.out.println(res);
	}
}
