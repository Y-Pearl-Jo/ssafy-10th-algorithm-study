import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int u;
	int v;
	int w;

	public Node(int u, int v, int w) {
		super();
		this.u = u;
		this.v = v;
		this.w = w;
	}
}

public class Main {
	static int N, start, end, M;
	static int INF = -200000000;
	static Queue<Node> queue = new LinkedList<Node>();
	static boolean[] visit;
	static long[] dist;
	static int[] add;
	static ArrayList<Node> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 값 저장
		dist = new long[N];
		// 갔을때 얻는 이득
		add = new int[N];
		// bfs용 visit 배열
		visit = new boolean[M];
		// INF 로 채우기
		Arrays.fill(dist, INF);

		graph = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// start
			int s = Integer.parseInt(st.nextToken());
			// end
			int e = Integer.parseInt(st.nextToken());
			// cost
			int c = Integer.parseInt(st.nextToken());

			graph.add(new Node(s, e, c));
		}

		// 버는 돈
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			add[i] = Integer.parseInt(st.nextToken());
		}

		bellmanFord(start);

	}

	static void bellmanFord(int start) {
		// 시작값 0
		dist[start] = add[start];
		// 정점의 개수만큼
		for (int i = 0; i < N; i++) {
			// 간선의 개수만큼
			for (int j = 0; j < M; j++) {
				Node n = graph.get(j);
				// 만약 dist[도착] < dist[시작] - 교통비 + 가서 버는돈 이라면
				if (dist[n.u] != INF && dist[n.v] < dist[n.u] - n.w + add[n.v]) {
					// dist[도착] = dist[시작] - 교통비 + 가서 버는돈
					dist[n.v] = dist[n.u] - n.w + add[n.v];
				}
			}
		}

		// 만약 도착 못하면 ㅈㅈ
		if (dist[end] == INF) {
			System.out.println("gg");
			return;
		}
		// 사이클 한번 더 돌림
		for (int i = 0; i < M; i++) {
			Node n = graph.get(i);
			// 값이 달라졌을때 bfs 돌림
			if (dist[n.u] != INF && dist[n.v] < dist[n.u] - n.w + add[n.v]) {
				dist[n.v] = dist[n.u] - n.w + add[n.v];
				queue.add(n);
			}
		}
		// true면 영향을 주니까 Gee
		if (bfs()) {
			System.out.println("Gee");
			return;
			// 아니면 영향 안주니까 dist[end] 출력
		} else {
			System.out.println(dist[end]);
			return;
		}

	}

	// 노드가 값이 변하는 노드가 도착점에 영향을 주는지 확인하는 bfs
	static boolean bfs() {
		
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			// 도착지점이 end면 영향을 주니까 true
			if (now.v == end) {
				return true;
			}
			// 간선의 갯수만큼 돌면서
			for (int i = 0; i < graph.size(); i++) {
				// 도착지점이 시작지점인 간선이 있으면 큐에 넣고 돌림
				if (now.v == graph.get(i).u && !visit[i]) {
					queue.add(graph.get(i));
					visit[i] = true;
				}
			}
		}
		return false;
	}
}
