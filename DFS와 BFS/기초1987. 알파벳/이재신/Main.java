import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int row, col;
	static int[][] board;
	static boolean[] visit = new boolean[26];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int max = 0;

	static void DFS(int a, int b, int cnt) {
		if (visit[board[a][b]]) {
			max = Math.max(max, cnt);
			return;
		} else {
			visit[board[a][b]] = true;
			for (int i = 0; i < 4; i++) {
				int x = a + dr[i];
				int y = b + dc[i];

				if (x >= 0 && x < row && y >= 0 && y < col) {
					DFS(x, y, cnt + 1);
				}
			}
			
			visit[board[a][b]] = false;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		row = init(st.nextToken());
		col = init(st.nextToken());

		board = new int[row][col];

		for (int i = 0; i < row; i++) {
			String str = br.readLine();
			for (int j = 0; j < col; j++) {
				board[i][j] = init(str.charAt(j));
			}
		}

		DFS(0, 0, 0);
		
		System.out.println(max);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static int init(char c) {
		return c - 'A';
	}
}
