import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] arr;
	static long[] treeMin;
	static long[] treeMax;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		treeMin = new long[N * 4];
		treeMax = new long[N * 4];
		initMin(1, 0, N - 1);
		initMax(1, 0, N - 1);

		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			sb.append(queryMin(1, 0, N - 1, left - 1, right - 1)).append(" ")
					.append(queryMax(1, 0, N - 1, left - 1, right - 1));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void initMin(int node, int start, int end) {
		if (start == end) {
			treeMin[node] = arr[start];
		} else {
			initMin(node * 2, start, (start + end) / 2);
			initMin(node * 2 + 1, (start + end) / 2 + 1, end);
			treeMin[node] = Math.min(treeMin[node * 2], treeMin[node * 2 + 1]);
		}
	}

	static void initMax(int node, int start, int end) {
		if (start == end) {
			treeMax[node] = arr[start];
		} else {
			initMax(node * 2, start, (start + end) / 2);
			initMax(node * 2 + 1, (start + end) / 2 + 1, end);
			treeMax[node] = Math.max(treeMax[node * 2], treeMax[node * 2 + 1]);
		}
	}

	static long queryMin(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 1000000001;
		}
		if (left <= start && end <= right) {
			return treeMin[node];
		}
		long lsum = queryMin(node * 2, start, (start + end) / 2, left, right);
		long rsum = queryMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return Math.min(lsum, rsum);
	}

	static long queryMax(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		if (left <= start && end <= right) {
			return treeMax[node];
		}
		long lsum = queryMax(node * 2, start, (start + end) / 2, left, right);
		long rsum = queryMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
		return Math.max(lsum, rsum);
	}
}
