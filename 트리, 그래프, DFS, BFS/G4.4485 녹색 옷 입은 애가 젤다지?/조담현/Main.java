import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable {
	int row;
	int col;
	int cost;
	
	Node(int row, int col, int cost) {
		this.row = row;
		this.col = col;
		this.cost = cost;
	}

	@Override
	public int compareTo(Object o) {
		Node next = (Node)o;
		return this.cost - next.cost;
	}
}

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int N;
	static int[][] map;
	static int[][] dp;
	static boolean[][] visited;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 0;
		while (true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			map = new int[N][N];
			dp = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
			dijkstra();
			System.out.printf("Problem %d: %d\n", tc, dp[N-1][N-1]);
		}

	}

	public static void dijkstra() {
		visited[0][0] = true;
		dp[0][0] = map[0][0];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			Node p = pq.poll();
			for(int d=0; d<4; d++) {
				int nr = p.row+dr[d];
				int nc = p.col+dc[d];
				
				if(nr>=0&&nc>=0&&nr<N&&nc<N&&!visited[nr][nc]) {
					int cost = map[nr][nc];
					if(p.cost+cost<dp[nr][nc]) {
						visited[nr][nc] = true;
						dp[nr][nc] = p.cost + cost;
						pq.add(new Node(nr, nc, dp[nr][nc]));
					}
				}
			}
		}
		
	}

}
