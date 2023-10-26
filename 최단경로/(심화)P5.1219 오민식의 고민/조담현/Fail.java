/*
1. 벨만포드 실행하는데, cost는 음수로 저장한다.
2. 각 도시에 도착하면 획득하는 금액들을 도시로 향하는 간선의 가중치(금액)에 더해준다.
3. 1000000 * 50 * 50 = 25억까지 가능하므로 출력초과를 방지하기 위해 Long으로 값을 설정한다
4. 벨만포드로 목적지까지 갈 수 있는 최대 비용을 찾는다.
5. 시작점은 돈을 들고 시작하므로 시작점의 배열 값은 0이아니라 해당 도시에 도착했을때의 금액이다.
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
	static class Edge {
		int v;
		int w;
		Long cost;
		
		public Edge(int v, int w, Long cost) {
			this.v = v;
			this.w = w;
			this.cost = cost;
		}
		
	}
	static int N, M;
	static int start, end;
	static List<Edge> edges = new ArrayList<>();
	static Long[] money;
	static Long startMoney;
	static int answer;
	final static Long INF = Long.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		money = new Long[N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			Long cost = Long.parseLong(st.nextToken());
			edges.add(new Edge(v, w, cost * -1));

		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			Long plus = Long.parseLong(st.nextToken());
			for (int j = 0; j < edges.size(); j++) {
				Edge edge = edges.get(j);
				if(i==start) startMoney = plus;
				if (edge.w == i) {
					edge.cost += plus;
				}
			}
		}

		System.out.println(bellmanFord(start, end));

	}

	public static String bellmanFord(int start, int end) {
		Arrays.fill(money, INF);
		money[start] = startMoney;
		Long temp = 0L;
		for (int i = 0; i < N; i++) {
			if (i==N-1&&money[end] == INF)
				return "gg";
			for (int j = 0; j < M; j++) {
				Edge e = edges.get(j);
				if (money[e.v] != INF && money[e.v] + e.cost > money[e.w]) {
					money[e.w] = money[e.v] + e.cost;
					// N번째 모든 간선을 확인할 때 마지막으로 저장한 목적지 값이 바뀌면 사이클의 영향을 받으므로 Gee
					if (i == N - 1 && temp != money[end]) {
						return "Gee";
					}
				}
			}
			// N-1번째, 마지막으로 저장한 값
			if(i==N-2) temp = money[end];
		}
		

		return String.valueOf(money[end]);
	}

}
