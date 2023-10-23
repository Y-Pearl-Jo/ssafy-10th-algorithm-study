//13264KB 152ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] dist = new int[N][N];

		// 인접 행렬 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				// 플로이드 거리 행렬 초기화
				if (tmp == 1) {
					dist[i][j] = 1;
					continue;
				}
				// 그렇지 않다면 매우 큰 값으로 초기화
				dist[i][j] = 987654;
			}
		}

		// 플로이드 워셜 알고리즘
		// 다른 노드를 거쳐가는 모든 경우를 고려
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					// dist[i][j] : i에서 j까지 가는데 드는 기존비용
					// dist[i][k] + dist[k][j] : k를 거쳐가는 비용
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

				}
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				// 간선이 존재하지 않아 dist배열도 갱신되지 않은 경우
				if (dist[i][j] == 987654) {
					sb.append(0).append(" ");
				} else {
					sb.append(1).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
