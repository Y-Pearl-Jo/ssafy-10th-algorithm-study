import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node {
	int x;
	int y;
	int w;

	public Node(int x, int y, int w) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
	}
}

public class Main {
	static int N;
	static int INF = 200000000;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int tc = 0;
		while (true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = INF;
				}
			}
			dijkstra(new Node(0, 0, map[0][0]));
			System.out.println("Problem " + tc + ": " + dist[N - 1][N - 1]);

		}
	}

	static void dijkstra(Node n) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.w - o2.w;
			}
		});

		queue.add(n);
		dist[n.x][n.y] = n.w;
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (dist[nr][nc] > dist[now.x][now.y] + map[nr][nc]) {
					dist[nr][nc] = dist[now.x][now.y] + map[nr][nc];
					queue.add(new Node(nr, nc, dist[nr][nc]));
				}
			}
		}
	}
}
