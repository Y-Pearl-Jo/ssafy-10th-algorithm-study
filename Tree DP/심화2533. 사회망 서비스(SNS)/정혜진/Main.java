import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 우수마을 {
	static int N;
	static ArrayList<Integer>[] friend; // 인접 리스트를 사용하여 트리 구조를 표현하는 배열
	static boolean[] visited; // DFS 중 방문한 노드를 추적하는 배열
	static int[][] d; // 동적 프로그래밍 결과를 저장하는 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		friend = new ArrayList[N + 1]; // 인접 리스트 초기화
		visited = new boolean[N + 1]; // 방문한 노드를 표시하는 배열 초기화
		d = new int[N + 1][2]; // 결과를 저장할 배열 초기화

		for (int i = 1; i <= N; i++) {
			friend[i] = new ArrayList<>(); // 각 노드의 인접 리스트 초기화
		}


		// 입력된 간선 정보를 읽고 트리 구조를 저장
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			// 양쪽 노드에 간선을 추가하여 트리를 생성
			friend[A].add(B);
			friend[B].add(A);
		}

		treedp(1, 0); // 동적 프로그래밍을 시작하고, 루트 노드는 1번 노드

		// 최종 결과로 루트 노드(1번 노드)에서 얻을 수 있는 최대값을 출력
		System.out.println(Math.min(d[1][0], d[1][1]));
	}

	public static void treedp(int node, int parent) {
		// 현재 노드에서 시작하여 하향식으로 DP 값을 계산

		for (int adjnode : friend[node]) {
			if (adjnode != parent) {
				treedp(adjnode, node); // 재귀적으로 하위 노드를 방문하며 DP 값을 계산
				// 현재 노드의 DP 값을 갱신
				d[node][0] += Math.min(d[adjnode][0], d[adjnode][1]);
				d[node][1] += d[adjnode][0];
			}
		}
		//?

	}
}
