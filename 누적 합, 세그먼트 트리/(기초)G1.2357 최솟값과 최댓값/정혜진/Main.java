import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static long INF = 1_000_000_001;
	static long min, max;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 원본 배열 입력 (인덱스 1부터)
		arr = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		// 트리 배열의 크기
		int size = N * 4;
		long[] minTree = new long[size];
		long[] maxTree = new long[size];
		// 트리 배열 초기화
		init(0, minTree, 1, N, 1);
		init(1, maxTree, 1, N, 1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			min = INF;
			max = -1;
			query(0, minTree, 1, N, 1, a, b);
			query(1, maxTree, 1, N, 1, a, b);
			sb.append(min + " " + max + "\n");
		}
		System.out.println(sb.toString());
	}

	// type 0: 최소, type 1: 최대
	static void init(int type, long[] tree, int start, int end, int node) {
		// 리프 노드일 때, 리프노드에 원본데이터를 입력하기
		if (start == end) {
			tree[node] = arr[start];
			return;
		}

		// 리프노드를 제외한 나머지 노드의 값을 채우기
		int mid = (start + end) / 2;
		init(type, tree, start, mid, node * 2);
		init(type, tree, mid + 1, end, node * 2 + 1);

		if (type == 0) {
			// 최댓값 저장하는 트리 배열
			// 왼쪽 자식과 오른쪽 자식 중에서 작은 값을 부모 노드에 저장
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		} else {
			// 최댓값 저장하는 트리 배열
			// 왼쪽 자식과 오른쪽 자식 중에서 큰 값을 부모 노드에 저장
			tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
		}

	}

	// 질의값 구하기
	// type 0: 최소, type 1: 최대
	static void query(int type, long[] minTree, int start, int end, int node, int left, int right) {
		
		// [left, right]와 [start,end]가 겹치지 않는 경우
		if (left > end || right < start)
			return;

		// [left,right]가 [start,end]를 완전히 포함하는 경우
		if (left <= start && end <= right) {
			if (type == 0) {
				// 최솟값 구하기(이미 구해져있는 minTree[node]를 그대로~)
				min = Math.min(min, minTree[node]);
			} else {
				// 최댓값 구하기
				max = Math.max(max, minTree[node]);
			}
			return;
		}

		// [start,end]가 [left,right]를 완전히 포함하는 경우
		// [left,right]와 [start,end]가 겹쳐져 있는 경우
		
		int mid = (start + end) / 2;
		query(type, minTree, start, mid, node * 2, left, right);
		query(type, minTree, mid + 1, end, node * 2 + 1, left, right);
	}
}
