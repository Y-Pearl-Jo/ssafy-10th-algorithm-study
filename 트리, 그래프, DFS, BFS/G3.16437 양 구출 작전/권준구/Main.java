import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int v;
	char tpye;
	long val;

	public Node(int v, char tpye, long val) {
		this.v = v;
		this.tpye = tpye;
		this.val = val;
	}

}

public class Main {
	static int N;
	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 2; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			char t = st.nextToken().charAt(0); // 늑대인지 양인지
			long a = Integer.parseInt(st.nextToken()); // 수
			int p = Integer.parseInt(st.nextToken()); // 연결되어 있는 섬

			graph.get(p).add(new Node(i, t, a));

		}

		System.out.println(dfs(new Node(1, 'W', 0)));
	}

	private static long dfs(Node now) {
		long sum = 0;
		for (int i = 0; i < graph.get(now.v).size(); i++) {
			sum += dfs(graph.get(now.v).get(i));
		}

		if (now.tpye == 'W') {
			if (sum + now.val * -1 < 0) {
				return 0;
			} else {
				return sum + now.val * -1;
			}
		} else {
			return sum + now.val;
		}

	}
}
