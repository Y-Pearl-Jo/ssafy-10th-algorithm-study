import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] selected;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selected = new boolean[N][M];
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTracking(0,0);
		System.out.println(max);
	}

	public static void bfs() {
		boolean[][] visited = new boolean[N][M];
		Queue<Point> queue = new LinkedList<>();
		int[][] temp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				temp[i][j] = arr[i][j];
				if(temp[i][j]==2) {
					queue.add(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			for(int d=0; d<4; d++) {
				int nr = p.x+dr[d];
				int nc = p.y+dc[d];
				if(nr>=0&&nc>=0&&nr<N&&nc<M&&!visited[nr][nc]&&temp[nr][nc]==0) {
					visited[nr][nc] = true;
					temp[nr][nc] = 2;
					queue.add(new Point(nr, nc));
				}
			}
			
		}
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j]==0) count++;
			}
		}
		max = Math.max(max, count);
	}

	public static void backTracking(int start, int depth) {
		if (depth == 3) {
			bfs();
			return;
		}
		
		for(int i=start; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j]==0) {
					arr[i][j] = 1;
					backTracking(i, depth+1);
					arr[i][j] = 0;
				}
			}
		}
		
	}

}
