import java.util.*;
import java.io.*;

class Node {
	int v;
	int w;

	public Node(int v, int w) {
		super();
		this.v = v;
		this.w = w;
	}

	@Override
	public String toString() {
		return "Node [v=" + v + ", w=" + w + "]";
	}

}

public class test {
	static int N, M;
	static int result;
	// 값 비교용 result1, 2
	static int result1 = 0;
	static int result2 = 0;
	// 큰 수
	static final int INF = 200000000;
	static int[] dist;
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		// dist 배열에 큰 수 저장
		dist = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
			dist[i] = INF;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}

		// 거쳐가야할 길 2개
		st = new StringTokenizer(br.readLine());
		int path1 = Integer.parseInt(st.nextToken());
		int path2 = Integer.parseInt(st.nextToken());

		// 경로는 1 -> path1 -> path2 -> N || 1 -> path2 -> path1 -> N
		result1 += dijkstra(new Node(1, 0), path1);
		result1 += dijkstra(new Node(path1, 0), path2);
		result1 += dijkstra(new Node(path2, 0), N);

		result2 += dijkstra(new Node(1, 0), path2);
		result2 += dijkstra(new Node(path2, 0), path1);
		result2 += dijkstra(new Node(path1, 0), N);

		result = Math.min(result1, result2);
		if (result >= INF) {
			result = -1;
		}
		System.out.println(result);

	}

	static int dijkstra(Node n, int end) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.w - o2.w;
			}
		});
		int num = 0;
		queue.add(n);
		// 복사 해놓고 dist 만지작 거리다가
		int[] copy = new int[N + 1];
		copy(copy, dist);
		dist[n.v] = n.w;
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
		num = dist[end];
		// 원상복구
		copy(dist, copy);
		return num;
	}

	static void copy(int[] copy, int[] map) {
		for (int i = 1; i < N + 1; i++) {
			copy[i] = map[i];
		}
	}
}
