import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 메모리 : 64828 KB, 시간 : 848
class Node implements Comparable<Node> {
	int to;
	int cost;
	int speed;

	// 여우 생성자
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	// 늑대 생성자
	public Node(int to, int cost, int speed) {
		this.to = to;
		this.cost = cost;
		this.speed = speed;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}

}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Node>[] graph;
	static final int INF = Integer.MAX_VALUE;
	// 그루터기(정점)의 개수, 오솔길(간선)의 개수
	static int N, M;
	// 1번에서 x번 그루터기로 가는 최단 경로 배열
	static int dWolf[][], dFox[];

	// 여우는 기본 다익스트라 구조를 그대로 적용해도 됨
	// 가중치는 * 2가 기본 값임
	public static void foxDist() {
		PriorityQueue<Node> fox = new PriorityQueue<>();

		fox.offer(new Node(1, 0));

		while (!fox.isEmpty()) {
			Node cur = fox.poll();

			int next = cur.to;
			int cost = cur.cost;

			if (dFox[next] < cost)
				continue;

			for (Node n : graph[next]) {
				if (dFox[n.to] > cost + n.cost * 2) {
					dFox[n.to] = cost + n.cost * 2;
					fox.offer(new Node(n.to, dFox[n.to]));
				}
			}
		}
	}

	public static void wolfDist() {
		PriorityQueue<Node> wolf = new PriorityQueue<>();
		// 현재 위치 / 걸린 시간 / 속도 상태 (0 : 1/2배, 1 : 2배)
		wolf.offer(new Node(1, 0, 0));

		while (!wolf.isEmpty()) {
			Node cur = wolf.poll();

			int next = cur.to;
			int cost = cur.cost;

			// 지금 들어있는 값보다 크면 더 확인 할 필요 없음
			if (dWolf[cur.speed][next] < cost)
				continue;

			for (Node n : graph[next]) {
				// 0 과 1을 번갈아 갈거임
				int speed = 1 - cur.speed;
				// 현재 상태에 따른 걸린 시간 조절
				int nextCost = dWolf[cur.speed][next] + n.cost * (cur.speed == 0 ? 1 : 4);
				if (dWolf[speed][n.to] > nextCost) {
					dWolf[speed][n.to] = nextCost;
					wolf.add(new Node(n.to, dWolf[speed][n.to], speed));
				}
			}

		}
	}

	public static void solution() {
		foxDist();
		wolfDist();

		int cnt = 0;
		for (int i = 2; i < N + 1; i++) {
			if (Math.min(dWolf[0][i], dWolf[1][i]) > dFox[i])
				cnt++;
		}
		System.out.println(cnt);
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		// 입력값에 맞는 그래프 생성
		graph = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = init(st);
			int to = init(st);
			int dis = init(st);

			graph[from].add(new Node(to, dis));
			graph[to].add(new Node(from, dis));
		}

		// 출발 정점으로 모든 정점으로 까지 거리 배열 초기화
		dWolf = new int[2][N + 1];
		dFox = new int[N + 1];

		Arrays.fill(dWolf[0], INF);
		Arrays.fill(dWolf[1], INF);
		Arrays.fill(dFox, INF);

		dWolf[0][1] = dFox[1] = 0;
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
