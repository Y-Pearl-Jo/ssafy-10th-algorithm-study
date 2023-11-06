import java.io.*;
import java.util.*;
// 메모리: 59384 KB, 시간: 580 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] minSegment, maxSegment, num;
	static int N, M;
	static int min, max;

	static void makeSegment(boolean isMax, int[] tree, int start, int end, int node) {
		int leftNode = (node << 1);
		int rightNode = (node << 1) + 1;

		if (start == end)
			tree[node] = num[start];
		else {
			int mid = start + (end - start) / 2;

			makeSegment(isMax, tree, start, mid, leftNode);
			makeSegment(isMax, tree, mid + 1, end, rightNode);

			if (isMax)
				tree[node] = Math.max(tree[leftNode], tree[rightNode]);
			else
				tree[node] = Math.min(tree[leftNode], tree[rightNode]);
		}
	}

	static void findValue(boolean isMax, int start, int end, int node, int left, int right) {
		if (left > end || right < start)
			return;
		if (left <= start && end <= right) {
			if (isMax)
				max = Math.max(max, maxSegment[node]);
			else
				min = Math.min(min, minSegment[node]);

			return;
		}
		int mid = start + (end - start) / 2;
		findValue(isMax, start, mid, (node << 1), left, right);
		findValue(isMax, mid + 1, end, (node << 1) + 1, left, right);
	}

	public static void solution() throws IOException {
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = init(st);
			b = init(st);

			max = -1;
			findValue(true, 1, N, 1, a, b);
			
			min = (int) 1e9;
			findValue(false, 1, N, 1, a, b);

			sb.append(min).append(" ").append(max).append("\n");
		}

		System.out.println(sb);
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		num = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			num[i] = init();
		}

		maxSegment = new int[N << 2];
		makeSegment(true, maxSegment, 1, N, 1);

		minSegment = new int[N << 2];
		makeSegment(false, minSegment, 1, N, 1);
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	public static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
