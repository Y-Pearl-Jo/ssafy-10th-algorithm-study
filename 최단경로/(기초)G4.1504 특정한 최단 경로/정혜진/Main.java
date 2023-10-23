//59200KB 1120ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		int[][] arr = new int[N + 1][N + 1];

		// 최단경로 배열 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j) {
					arr[i][j] = 0;
					continue;
				}
				arr[i][j] = 987654321;
			}
		}

		// 정점 사이 거리 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			arr[a][b] = c;
			arr[b][a] = c;
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점 1
		int v2 = Integer.parseInt(st.nextToken()); // 반드시 거쳐야 하는 정점 2

		// i에서 j까지 가는 최단 경로를 저장
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
				}
			}
		}

		// 1번 정점에서 N번 정점까지의 최단 경로 중에 반드시 지나야 하는 정점 v1과 v2를 포함하는 경로
		// arr[1][v1] + arr[v1][v2] + arr[v2][N] : v1를 먼저 지나고 v2를 지나는 경우
		// arr[1][v2] + arr[v2][v1] + arr[v1][N] : v2를 먼저 지나고 v1를 지나는 경우
		int dist1 = 987654321;
		int dist2 = 987654321;
		if (arr[1][v1] != 987654321 && arr[v1][v2] != 987654321 && arr[v2][N] != 987654321) {
			dist1 = arr[1][v1] + arr[v1][v2] + arr[v2][N];
		}
		if (arr[1][v2] != 987654321 && arr[v2][v1] != 987654321 && arr[v1][N] != 987654321) {
			dist2 = arr[1][v2] + arr[v2][v1] + arr[v1][N];
		}

		// 출력
		int answer = Math.min(dist1, dist2);
		if(answer == 987654321) {
			System.out.println("-1");
		}else {
			System.out.println(answer);
		}
	}
}
