//95904KB	1052ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 양꼬치 {

	static int N;
	static long[] animal;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 섬의 수
		animal = new long[N + 1];

		list = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char c = (st.nextToken()).charAt(0);
			int A = Integer.parseInt(st.nextToken()); // 양/늑대 마리수
			int route = Integer.parseInt(st.nextToken());

			if (c == 'S') {
				animal[i] = A; // 양
			} else {
				animal[i] = -A; // 늑대
			}
			list[route].add(i);

		}

		for (int x : list[1]) {
			dfs(x, 1);
		}
		System.out.println(animal[1]);
	}

	private static void dfs(int node, int parent) {
		// 재귀파트
		for (int x : list[node]) {
			dfs(x, node);
		}

		if (animal[node] > 0) {
			animal[parent] += animal[node];
			return;
		}
	}

}
