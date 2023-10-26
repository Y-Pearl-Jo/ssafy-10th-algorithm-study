import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
	int v;
	int w;
	int distCount;

	public Node(int v, int w) {
		super();
		this.v = v;
		this.w = w;
	}

	public Node(int v, int w, int distCount) {
		super();
		this.v = v;
		this.w = w;
		this.distCount = distCount;
	}

}

public class Main {
	static int INF = Integer.MAX_VALUE;
	static int N, M;
	// 여우 최솟값
	static int[] distFox;
	// 늑대 최솟값
	static int[][] distWolf;
	static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		distFox = new int[N + 1];
		distWolf = new int[2][N + 1];

		Arrays.fill(distFox, INF);
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N + 1; j++) {
				distWolf[i][j] = INF;
			}
		}

		graph = new ArrayList<ArrayList<Node>>();

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = 2 * Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
			graph.get(v).add(new Node(u, w));
		}

		// 다익스트라 여우버젼
		dijkstraFox(new Node(1, 0));
		// 다익스트라 늑대버젼
		dijkstraWolf(new Node(1, 0, 0));

		int result = 0;
		for (int i = 2; i < N + 1; i++) {
			if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i]))
				result++;
		}

		System.out.println(result);

	}

	// 그냥 다익스트라
	static void dijkstraFox(Node n) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.w - o2.w;
			}
		});
		queue.add(n);
		distFox[n.v] = 0;
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			if (distFox[now.v] < now.w) {
				continue;
			}
			for (int i = 0; i < graph.get(now.v).size(); i++) {
				Node next = graph.get(now.v).get(i);
				if (distFox[next.v] > distFox[now.v] + next.w) {
					distFox[next.v] = distFox[now.v] + next.w;
					queue.add(new Node(next.v, distFox[next.v]));
				}
			}
		}
	}

	static void dijkstraWolf(Node n) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.w - o2.w;
			}
		});
		queue.add(n);
		distWolf[0][1] = 0;
		while (!queue.isEmpty()) {
			Node now = queue.poll();
      // 속도 나타내는거임
			int status = now.distCount;
      // 다음속도 여기서 설정해줌 밑에서 +1 하면 인덱스 터짐
			int nextStatus = (status + 1) % 2;
			if (distWolf[status][now.v] < now.w) {
				continue;
			}
			for (int i = 0; i < graph.get(now.v).size(); i++) {
				Node next = graph.get(now.v).get(i);
				int worth = next.w;
				if (status == 0) {
					worth = worth / 2;
				} else {
					worth = worth * 2;
				}
				if (distWolf[nextStatus][next.v] > distWolf[status][now.v] + worth) {
					distWolf[nextStatus][next.v] = distWolf[status][now.v] + worth;
					queue.add(new Node(next.v, distWolf[nextStatus][next.v], nextStatus));
				}
			}
		}
	}

}
