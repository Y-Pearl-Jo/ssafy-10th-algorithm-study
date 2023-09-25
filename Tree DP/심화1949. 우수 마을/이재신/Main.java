import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 아이디어 :
 * 기본적으로 N개 노드에 N - 1개 선이므로 신장 트리임을 알 수 있다.
 * 방향이 없는 트리이기 때문에 어떤 노드에서 시작하더라도 답 도출 가능하기 때문에 편의상 1부터 시작하면 됨
 * 이번 노드를 선택하려면 자식 노드를 선택하지 않는 경우에 + people[node]
 * 이번 노드를 선택하지 않는다면 자식 노드의 선택 한 것과 안 한 것을 Max 값 비교하여 +
 * 위에 알고리즘을 위해 시작 노드부터 재귀로 들어가면서 리프노드부터 결과를 얻어서 올라오기
 */
// 메모리: 21472 KB, 시간: 188 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N;
	static int[] people;
	static int[][] dp;
	static ArrayList<Integer>[] tree;

	static void recur(int node, int parent) {
		// 자식 노드 탐색
		for (int n : tree[node]) {
			// 자식이 다시 부모로 넘어와 무한 루프 발생 방지
			if (n != parent) {
				// dp에 대입 전 자식 노드의 값을 알아와야 함
				recur(n, node);
				// node 를 선택하지 않는 경우의 값은 자식노드들의 선택 여부와 상관없으니 최댓값을 계속 더해주기
				dp[node][0] += Math.max(dp[n][0], dp[n][1]);
				// node 를 선택하려면 자식노드는 선택되면 안됨
				dp[node][1] += dp[n][0];
			}
		}
		// 중복으로 people이 더해지는 것을 방지하기 위해 반복문 밖에서 더해주기
		dp[node][1] += people[node];
	}

	static void solution() throws IOException {
		// 아이디어에서 말한 대로 출발 노드는 상관 없음
		recur(1, 0);
		// 출발 노드를 최상위 노드라고 생각 했을 때 해당 노드에서의 dp값만 알면 됨
		int result = Math.max(dp[1][0], dp[1][1]);
		System.out.println(result);
	}

	static void make() throws IOException {
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		dp = new int[N + 1][2];

		tree = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			people[i] = init(st.nextToken());
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
