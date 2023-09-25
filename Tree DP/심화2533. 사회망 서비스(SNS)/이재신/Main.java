import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] dp;
	static ArrayList<Integer>[] tree;

	static void recur(int node, int parent) {
		for (int n : tree[node]) {
			if (n != parent) {
				recur(n, node);
				// 얼답인 경우
				dp[node][0] += Math.min(dp[n][0], dp[n][1]);
				// 얼답이 아닌 경우
				dp[node][1] += dp[n][0];
			}
		}
		// 반복문을 나온 후 얼답인 경우 얼답 수 +1;
		dp[node][0]++;
	}

	static void solution() throws IOException {
		recur(1, 0);
		System.out.print(Math.min(dp[1][0], dp[1][1]));
	}

	static void make() throws IOException {
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][2];

		tree = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = init(st.nextToken());
			int v = init(st.nextToken());
			tree[n].add(v);
			tree[v].add(n);
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
