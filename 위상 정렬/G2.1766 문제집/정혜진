//44564KB 432ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		List<Integer>[] adjList = new ArrayList[V + 1]; // 인접리스트/ 정점의 번호는 1부터 V까지의 정수값
		int[] degree = new int[V + 1]; // 진입 차수를 저장할 배열.

		// 각 정점의 인접 리스트 초기화
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adjList[x].add(y); // 유향 그래프
			degree[y]++; // 진입 차수 증가
		} // ------------------------------------------

		// 위상정렬
		// 진입차수가 0인 정점을 넣을 큐
		Queue<Integer> que = new LinkedList<>();

		// 1. 진입차수가 0인 정점들의 인덱스를 큐에 넣는다.
		for (int i = 1; i <= V; i++) {
			if (degree[i] == 0) {
				que.add(i);
			}
		}

		// 2. Queue가 공백상태가 될 때까지 반복 수행
		while (!que.isEmpty()) {
			int node = que.poll(); // 하나 꺼내
			sb.append(node).append(" "); // 출력
			
			for (int i = 0; i < adjList[node].size(); i++) {
				degree[adjList[node].get(i)]--; // 진입 차수 감소

				// 3.만약 노드의 진입차수가 감소하여 0이 되었다면(선행작업이 모두 완료되었다면)
				if (degree[adjList[node].get(i)] == 0)
					que.add(adjList[node].get(i)); // 큐에 넣는다
			}
		}

		sb.append("\n");

		System.out.println(sb);
	}// main
}
