import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static boolean[][] visited;
	static int[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int result = Integer.MIN_VALUE;

	static void DFS(int a, int b, int depth, int sum) {
		if (depth == 4) {
			result = Math.max(result, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int r = a + dr[i];
			int c = b + dc[i];

			if (r >= 0 && r < N && c >= 0 && c < M && !visited[r][c]) {
				
				if(depth == 2) {
					visited[r][c] = true;
					DFS(a, b, depth + 1, sum + board[r][c]);
					visited[r][c] = false;
				}
				
				visited[r][c] = true;
				DFS(r, c, depth + 1, sum + board[r][c]);
				visited[r][c] = false;
			}
		}
	}

	static void makeBoard() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st.nextToken());
		M = init(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = init(st.nextToken());
			}
		}
	}

	static void findMax() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				DFS(i, j, 1, board[i][j]);
				visited[i][j] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		makeBoard();
		findMax();
		System.out.println(result);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static int init(char c) {
		return c - '0';
	}

	static void check(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
