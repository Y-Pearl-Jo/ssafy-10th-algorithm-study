import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Bus {
	int from; // 나가는 정점
	int to; // 들어오는 정점
	int cost;

	public Bus(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// 노선 리스트
	static ArrayList<Bus> graph;
	// 십억
	static final int INF = -(int) 1e9;
	// 도시, 버스의 개수
	static int N, M;
	// 출발 도착 도시
	static int cityA, cityB;
	// 도시 별 버는 돈
	static int[] get;
	// 양수 사이클을 돈 후 도착 도시를 갈 수 있는지
	static Queue<Bus> bus = new LinkedList<>();

	// 정점의 개수, 간선의 개수, 출발지
	public static void BellmanFord() {
		long[] money = new long[N];
		Arrays.fill(money, INF);

		money[cityA] = get[cityA];

		// 정점의 개수만큼 반복
		for (int i = 0; i < N; i++) {
			// 간선의 개수만큼 반복
			for (int j = 0; j < M; j++) {
				Bus b = graph.get(j); // 현재 간선

				// 현재 간선의 들어오는 정점에 대해 비교
				if (money[b.from] != INF && money[b.to] < money[b.from] + b.cost) {
					money[b.to] = money[b.from] + b.cost;
				}

//				System.out.println(Arrays.toString(money));
			}
		}

		System.out.println(result(money));
	}

	public static String result(long[] money) {
		// 도착 도시로 갈 수 없는 경우 "gg"
		if (money[cityB] == INF)
			return "gg";
		// 사이클이 존재하여 돈을 무한히 버는 경우 "Gee"
		else if (plus_cycle(money))
			return "Gee";
		// 보통의 경우라면 도착 도시에서 가진 돈
		else
			return money[cityB] + "";
	}

	// 양수 가중치 확인
	public static boolean plus_cycle(long[] money) {
		long save = money[cityB];
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < M; i++) {
				Bus b = graph.get(i); // 현재 간선
				// 현재 간선의 들어오는 정점에 대해 비교 -> 더 큰 값 생기면 양수 사이클 존재
				// 이미 도착 도시에 도착이 가능한 상황이라 그냥 리턴 해도 될 것 같은데
				// 혹시 몰라서 확인하고 리턴함
				if (money[b.from] != INF && money[b.to] < money[b.from] + b.cost) {
					money[b.to] = money[b.from] + b.cost;
					bus.add(b);
				}
			}
		}

		if (check_INF())
			return true;
		return false;
	}

	public static boolean check_INF() {
		boolean visited[] = new boolean[M];
		while (!bus.isEmpty()) {
			Bus now = bus.poll();

			int nfrom = now.to;

			if (nfrom == cityB)
				return true;

			for (int i = 0; i < graph.size(); i++) {
				if (!visited[i] && graph.get(i).from == nfrom) {
					bus.add(graph.get(i));
					visited[i] = true;
				}
			}
		}
		return false;
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		cityA = init(st);
		cityB = init(st);
		M = init(st);

		graph = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = init(st);
			int w = init(st);
			int cost = init(st);

			graph.add(new Bus(v, w, -cost));
		}

		get = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			get[i] = init(st);
		}

		// 버스 노선의 가중치 즉 요금을 해당 도시에서 얻을 수 있는 금액과 합산
		// 결국 버스에서 내려 도시에 도착하면 버스 요금을 잃고 해당 도시의 돈을 범
		// 노선의 개수가 50개 밖에 안되기 때문에 시간도 넉넉
		for (int i = 0; i < graph.size(); i++) {
			graph.get(i).cost += get[graph.get(i).to];
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		// 벨만-포드 알고리즘 수행
		BellmanFord();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
