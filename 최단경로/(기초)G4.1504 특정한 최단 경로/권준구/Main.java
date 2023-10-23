import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
	int v, w;

	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}

}

public class Main {
	static int N, E;
	static final int INF = 200000000;
	static int[] dist;

	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		dist = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		long route1 = bfs(1, v1) + bfs(v1, v2) + bfs(v2, N);
		long route2 = bfs(1, v2) + bfs(v2, v1) + bfs(v1, N);
		long min = Math.min(route1, route2);

		System.out.println(route1 >= INF && route2 >= INF ? -1 : min);
	}

	public static int bfs(int start, int end) {
		// 초기화
		for (int i = 0; i < N + 1; i++) {
			dist[i] = INF;
		}

		// 우선순위 큐
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			}
		});

		// 시작점과 가중치 넣기
		queue.add(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			for (int i = 0; i < graph.get(now.v).size(); i++) {
				Node next = graph.get(now.v).get(i);
				if (dist[next.v] > dist[now.v] + next.w) {
					dist[next.v] = dist[now.v] + next.w;

					queue.add(new Node(next.v, dist[next.v]));
				}
			}
		}

		return dist[end];
	}
}
