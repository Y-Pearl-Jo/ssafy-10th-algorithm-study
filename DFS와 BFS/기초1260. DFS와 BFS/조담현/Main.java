import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static int N;
	static int M;
	static int V;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		for(int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			graph.get(num1).add(num2);
			graph.get(num2).add(num1);
		}
		for (int i = 0; i < graph.size(); i++) {
			Collections.sort(graph.get(i));
		}
		visited = new boolean[N + 1];
		dfs(V);
		System.out.println();
		visited = new boolean[N + 1];
		bfs(V);
	}

	public static void dfs(int x) {
		visited[x] = true;
		System.out.print(x + " ");
		for (int i = 0; i < graph.get(x).size(); i++) {
			int y = graph.get(x).get(i);
			if (!visited[y]) {
				dfs(y);
			}
		}
	}

	public static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(x);
		visited[x] = true;
		
		while (!queue.isEmpty()) {
			int n = queue.poll();
			System.out.print(n + " ");
			for (int i = 0; i < graph.get(n).size(); i++) {
				int y = graph.get(n).get(i);
				if (!visited[y]) {
					queue.offer(y);
					visited[y] = true;
				}

			}
		}
	}

}
