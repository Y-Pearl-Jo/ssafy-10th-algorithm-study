import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int cntW = 0;
	static int cntB = 0;
	static int[][] board;

	static void find(int r, int c, int n) throws IOException {
		int color = board[r][c];
		boolean flag = true;

		loop: for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (board[i][j] != color) {
					flag = false;
					break loop;
				}
			}
		}

		n = n / 2;

		if (!flag) {
			find(r, c, n);
			find(r + n, c, n);
			find(r, c + n, n);
			find(r + n, c + n, n);
		} else {
			if(color == 1)
				cntB++;
			else
				cntW++;
		}
	}

	static void Solution() throws IOException {
		N = init(br.readLine());
		board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = init(st.nextToken());
			}
		}

		find(0, 0, N);
		System.out.println(cntW);
		System.out.println(cntB);
	}

	public static void main(String[] args) throws IOException {
		Solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
