import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 힌트의 수도코드를 따라 차근차근 해나감
// 메모리: 100404 KB, 시간: 776 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, R, Q;
	static int[] parent;
	static int[] size;
	static ArrayList<Integer>[] list, tree;

  // 트리를 만드는 중입니다.
	static void makeTree(int curNode, int p) {
		for (int node : list[curNode]) {
			if (node != p) {
				tree[curNode].add(node);
				parent[node] = curNode;
				makeTree(node, curNode);
			}
		}
	}
  // 자식노드로 내려가며 size를 계속 바꿀 준비하다가
  // 마지막 노드부터 증가되면서 올라옴
	static void countSubtreeNodes(int curNode) {
		size[curNode] = 1;
		for (int node : tree[curNode]) {
			countSubtreeNodes(node);
			size[curNode] += size[node];
		}
	}

	static void solution() throws IOException {
		makeTree(R, -1);
		countSubtreeNodes(R);
		while (Q-- != 0) {
			int query = init(br.readLine());
			sb.append(size[query]).append("\n");
		}
		System.out.println(sb);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		size = new int[N + 1];
		list = new ArrayList[N + 1];
		tree = new ArrayList[N + 1];

		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int now = init(st.nextToken());
			int next = init(st.nextToken());

			list[now].add(next);
			list[next].add(now);
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
