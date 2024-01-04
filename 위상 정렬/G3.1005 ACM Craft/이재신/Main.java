import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, W;
	static ArrayList<Integer>[] graph;
	static int[] time, dp;
	static int[] in_degree;

	static void solution() throws IOException {
		Queue<Integer> q = new LinkedList<>();
		dp = new int[N + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);

		for (int i = 1; i < N + 1; i++) {
			if (in_degree[i] == 0) {
				q.add(i);
				dp[i] = time[i];
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int i = 0; i < graph[now].size(); i++) {
				int next = graph[now].get(i);
				dp[next] = Math.max(dp[next], dp[now] + time[next])
				if (--in_degree[next] == 0)
					q.offer(next);
			}
		}
		sb.append(dp[W] == Integer.MIN_VALUE ? 0 : dp[W]).append("\n");
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		K = init(st);

		time = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			time[i] = init(st);
		}

		graph = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		in_degree = new int[N + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = init(st);
			int v = init(st);
			graph[u].add(v);
			in_degree[v]++;
		}

		W = init();
	}

	public static void main(String[] args) throws IOException {
		int T = init();
		for (int tc = 0; tc < T; tc++) {
			make();
			solution();
		}
		System.out.print(sb);
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
