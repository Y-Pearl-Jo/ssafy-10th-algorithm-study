import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int Blank = 0;
	static StringTokenizer st;
	static int[][] board;
	static boolean[][] change;
	static int[] visited;
	static int N;
	static int result = Integer.MIN_VALUE;

	static void recur(int depth) {
		if (depth == 5) {
			int[][] temp = new int[N][N];
			copyBoard(temp);
			for (int i = 0; i < 5; i++) {
				if (visited[i] == 1)
					up(temp);
				else if (visited[i] == 2)
					down(temp);
				else if (visited[i] == 3)
					left(temp);
				else if (visited[i] == 4)
					right(temp);
			}
			checkBoard(temp);
			return;
		}

		for (int i = 1; i <= 4; i++) {
			visited[depth] = i;
			recur(depth + 1);
		}
	}

	static void down(int[][] board) {
		change = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (board[j][i] == Blank)
					continue;
				int idx = j + 1;
				while (idx <= N - 2 && board[idx][i] == Blank && !change[idx + 1][i]) {
					idx++;
				}
				if (board[idx][i] == board[j][i]) {
					board[idx][i] *= 2;
					change[idx][i] = true;
					board[j][i] = Blank;
				} else if (board[idx][i] == Blank) {
					board[idx][i] = board[j][i];
					board[j][i] = Blank;
				} else {
					board[idx - 1][i] = board[j][i];
					if (idx - 1 != j) {
						board[j][i] = Blank;
					}
				}

			}
		}
	}

	static void up(int[][] board) {
		change = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (board[j][i] == Blank)
					continue;
				int idx = j - 1;
				while (idx >= 1 && board[idx][i] == Blank && !change[idx - 1][i]) {
					idx--;
				}
				if (board[idx][i] == board[j][i]) {
					board[idx][i] *= 2;
					change[idx][i] = true;
					board[j][i] = Blank;
				} else if (board[idx][i] == Blank) {
					board[idx][i] = board[j][i];
					board[j][i] = Blank;
				} else {
					board[idx + 1][i] = board[j][i];
					if (idx + 1 != j) {
						board[j][i] = Blank;
					}
				}

			}
		}
	}

	static void right(int[][] board) {
		change = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			for (int i = N - 2; i >= 0; i--) {
				if (board[j][i] == Blank)
					continue;
				int idx = i + 1;
				while (idx <= N - 2 && board[j][idx] == Blank && !change[j][idx + 1]) {
					idx++;
				}
				if (board[j][i] == board[j][idx]) {
					board[j][idx] *= 2;
					change[j][idx] = true;
					board[j][i] = Blank;
				} else if (board[j][idx] == Blank) {
					board[j][idx] = board[j][i];
					board[j][i] = Blank;
				} else {
					board[j][idx - 1] = board[j][i];
					if (idx - 1 != i)
						board[j][i] = Blank;
				}
			}
		}
	}

	static void left(int[][] board) {
		change = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			for (int i = 1; i < N; i++) {
				if (board[j][i] == Blank)
					continue;
				int idx = i - 1;
				while (idx >= 1 && board[j][idx] == Blank && !change[j][idx - 1]) {
					idx--;
				}
				if (board[j][i] == board[j][idx]) {
					board[j][idx] *= 2;
					change[j][idx] = true;
					board[j][i] = Blank;
				} else if (board[j][idx] == Blank) {
					board[j][idx] = board[j][i];
					board[j][i] = Blank;
				} else {
					board[j][idx + 1] = board[j][i];
					if (idx + 1 != i)
						board[j][i] = Blank;
				}
			}
		}
	}

	static void makeBoard() throws IOException {
		N = init(br.readLine());
		board = new int[N][N];
		visited = new int[5];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = init(st.nextToken());
			}
		}
	}

	static void copyBoard(int[][] temp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = board[i][j];
			}
		}
	}

	static void checkBoard(int[][] board) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result = Math.max(result, board[i][j]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		makeBoard();
		recur(0);
		System.out.println(result);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static int init(char c) {
		return c - '0';
	}

	static void check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
