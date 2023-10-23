import java.io.*;
import java.util.*;

// 메모리: 69208 KB, 시간: 732 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Edge>[] graph;
	static int[] dis;
	static boolean[] visited;
	static int V, E;
	static int A, B;

	static int stra(int start, int end) {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		dis = new int[V + 1];
		visited = new boolean[V + 1];
		for (int i = 0; i <= V; i++) {
			dis[i] = Integer.MAX_VALUE;
		}
		dis[start] = 0;
		q.add(new Edge(start, 0));

		while (!q.isEmpty()) {
			Edge now = q.poll();
			// 사실상 최종 출력을 위한 방문 처리
			if (visited[now.vertex])
				continue;
			visited[now.vertex] = true;
			// 현재 정점과 연결된 정점들을 큐에 추가
			for (int i = 0; i < graph[now.vertex].size(); i++) {
				// 다음 정점까지에 대한 정보
				Edge temp = graph[now.vertex].get(i);
				int next = temp.vertex;
				int weight = temp.weight;
				// 다음 정점까지 가는 거리가 이번에 가는 방법이 더 최단거리인 경우
				if (dis[next] > dis[now.vertex] + weight) {
					dis[next] = dis[now.vertex] + weight;
					q.add(new Edge(next, dis[next]));
				}
			}
		}

		return dis[end];
	}

	static void solution() {
		int min = Integer.MAX_VALUE;

		int startA = stra(1, A);
		int startB = stra(1, B);
		int sub = stra(A, B);
		int endA = stra(B, V);
		int endB = stra(A, V);

		if (sub == Integer.MAX_VALUE) {
			System.out.print(-1);
			return;
		}

		if (startA != Integer.MAX_VALUE && endA != Integer.MAX_VALUE)
			min = Math.min((stra(1, A) + sub + stra(B, V)), min);

		if (startB != Integer.MAX_VALUE && endB != Integer.MAX_VALUE)
			min = Math.min((stra(1, B) + sub + stra(A, V)), min);

		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		// 정점 개수
		V = init(st);
		// 간선 개수
		E = init(st);
		// 각 배열의 크기를 노드의 개수에 맞추어 설정
		graph = new ArrayList[V + 1];
		// 그래프 초기화
		for (int i = 1; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		// 가중치가 있는 인접 리스트의 표현
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int now = init(st);
			int next = init(st);
			int weight = init(st);
			graph[now].add(new Edge(next, weight));
			graph[next].add(new Edge(now, weight));
		}

		st = new StringTokenizer(br.readLine());
		A = init(st);
		B = init(st);

	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}

class Edge implements Comparable<Edge> {
	int vertex;
	int weight;

	public Edge(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return this.weight - o.weight;
	}
}
