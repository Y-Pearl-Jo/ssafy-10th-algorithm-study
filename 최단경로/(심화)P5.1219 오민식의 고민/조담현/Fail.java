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
					if (i == N - 1 && temp != money[end]) {
						return "Gee";
					}
				}
			}
			if(i==N-2) temp = money[end];
		}
		

		return String.valueOf(money[end]);
	}

}
