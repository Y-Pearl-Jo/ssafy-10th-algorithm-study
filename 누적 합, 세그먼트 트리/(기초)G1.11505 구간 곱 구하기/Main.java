import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		M += K;

		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		tree = new long[N * 4];
		init(1, 0, N - 1);

		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int what = Integer.parseInt(st.nextToken());
			if (what == 1) {
				int index = Integer.parseInt(st.nextToken());
				long val = Long.parseLong(st.nextToken());
				update(1, 0, N - 1, index - 1, val);

			} else {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				sb.append(query(1, 0, N - 1, left - 1, right - 1) % 1000000007).append("\n");
			}
		}
		System.out.println(sb);
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = arr[start];
		} else {
			init(node * 2, start, (start + end) / 2);
			init(node * 2 + 1, (start + end) / 2 + 1, end);
			tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
		}
	}

	static long query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 1;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}
		long lsum = query(node * 2, start, (start + end) / 2, left, right);
		long rsum = query(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return (lsum * rsum) % 1000000007;
	}

	static void update(int node, int start, int end, int index, long val) {
		if (index < start || index > end) {
			return;
		}
		if (start == end) {
			arr[index] = val;
			tree[node] = val;
			return;
		}
		update(node * 2, start, (start + end) / 2, index, val);
		update(node * 2 + 1, (start + end) / 2 + 1, end, index, val);
		tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % 1000000007;
	}
}
