import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class City {
	int start, end, weigt;

	public City(int start, int end, int weigt) {
		this.start = start;
		this.end = end;
		this.weigt = weigt;
	}

}

public class Main {
	static int N, from, to, M;
	static List<City> graph;
	static long[] dist;
	static int[] money; // 도시에 들렸을 때 벌 수 있는 값들 초기화
	static final int INF = Integer.MIN_VALUE; // 최대값을 구해야 하므로 최소값으로 초기화
	static boolean[][] linkGraph; // 그래프 어떻게 연결이 되어있는지 알아보기 위해 만든 2차배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		money = new int[N];
		dist = new long[N];
		linkGraph = new boolean[N][N];
		Arrays.fill(dist, INF);

		// 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			linkGraph[a][b] = true;
			graph.add(new City(a, b, c));
		}

		// 초기화
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}

		if (bell(from)) {
			System.out.println("Gee");
		} else {
			System.out.println(dist[to]);
		}

	}

	// 벨만
	private static boolean bell(int start) {
		dist[start] = money[start];

		// 거리가 1,2,3,...N-1까지 일때 최대값을 보기 위해
		for (int i = 0; i < N - 1; i++) {
			// INF가 아닌 도시에 연결된 다른 도시들을 찾기 위한 for문
			for (int j = 0; j < M; j++) {
				City now = graph.get(j);

				//
				if (dist[now.start] != INF) {
					// 지금 도시에서의 최대값 < 지금 도시 오기전까지의 최대값 - 건너오는데 드는 교통비 + 도시에 도착했을 때 받을 수 있는 돈
					if (dist[now.end] < dist[now.start] - now.weigt + money[now.end]) {
						dist[now.end] = dist[now.start] - now.weigt + money[now.end];

					}
				}
			}
		}

		// 도착을 못했을 때
		if (dist[to] == INF) {
			System.out.println("gg");
			System.exit(0);
		}

		// 사이클이 존재한다면
		for (int i = 0; i < M; i++) {
			City now = graph.get(i);

			if (dist[now.start] != INF) {
				if (dist[now.end] < dist[now.start] - now.weigt + money[now.end]) {
					// 사이클이 존재해도 그 사이클이 도착지점까지 이동할 수 있다면 무한으로 돈을 벌 수 있다.
					// 연결되어 있는지 판단하는 bfs!! true면 연결되어 있다.
					if (bfs(now.end)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean bfs(int start) {

		boolean[] visit = new boolean[N];
		Queue<Integer> queue = new LinkedList<>();

		queue.add(start);
		visit[start] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();

			if (now == to) {
				return true;
			}

			for (int i = 0; i < N; i++) {
				if (linkGraph[now][i] && !visit[i]) {
					queue.add(i);
					visit[i] = true;
				}
			}
		}
		return false;
	}
}
