//43808KB 368ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); // 도시의 개수
		int M = Integer.parseInt(br.readLine()); // 버스의 개수

		// 시작도시에서 출발도시로 가는데 필요한 최소 비용을 저장할 배열
		int[][] arr = new int[N][N];

		// 비용 배열 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 시작 도시와 도착 도시가 같다면 비용은 0
				if (i == j) {
					arr[i][j] = 0;
					continue;
				}
				// 그렇지 않다면, 일단 비용을 100,000보다 큰 수로 초기화 해놓는다
				// (비용은 100,000보다 작거나 같은 자연수)
				arr[i][j] = 987654321;
			}
		}

		// 이동 도시에 따른 버스 비용 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 시작 도시
			int b = Integer.parseInt(st.nextToken()) - 1; // 도착 도시
			int c = Integer.parseInt(st.nextToken()); // 비용

			// 시작도시랑 도착도시가 같다면 비용이 작은 값으로 저장(중복 노선이 있을 수 있음)
			arr[a][b] = Math.min(arr[a][b], c);
		}

		// 플로이드 워셜 알고리즘
		// 다른 노드를 거쳐가는 모든 경우를 고려
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// arr[i][j] : i에서 j까지 가는데 드는 기존비용
					// arr[i][k] + arr[k][j] : k를 거쳐가는 비용
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 아직도 987654321 로 남아있는 값이 있다면 i에서 j로 갈 수 없는 곳임. 0 으로 바꿔줘야 함
				if (arr[i][j] == 987654321) {
					arr[i][j] = 0;
				}
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}
