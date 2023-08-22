import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static char[][] board;
	static boolean[] alphabets = new boolean[26];
	static int max = Integer.MIN_VALUE;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		dfs(0, 0, 0);
		System.out.println(max);

	}

	public static void dfs(int r, int c, int count) {
		alphabets[board[r][c] - 'A'] = true;
		count++;

		if (count > max) {
			max = count;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < R && nc >= 0 && nc < C && !alphabets[board[nr][nc] - 'A']) {
				dfs(nr, nc, count);
				alphabets[board[nr][nc] - 'A'] = false;
			}
		}
		

	}

}
