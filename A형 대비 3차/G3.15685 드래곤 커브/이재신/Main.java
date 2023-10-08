import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static boolean[][] map = new boolean[101][101];
	static int cnt = 0;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };

	static void countBox() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
					cnt++;
			}
		}
	}

	static void drawCurv(int r, int c, ArrayList<Integer> list) {
		map[r][c] = true;
		for (int direc : list) {
			r += dr[direc];
			c += dc[direc];
			map[r][c] = true;
		}
	}

	static void makeCurv(int r, int c, int d, int g) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(d);

		for (int i = 0; i < g; i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				list.add((list.get(j) + 1) % 4);
			}
		}
		drawCurv(r, c, list);
	}

	static void make() throws IOException {
		int N = init();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = init(st);
			int r = init(st);
			int d = init(st);
			int g = init(st);

			makeCurv(r, c, d, g);
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		countBox();
		System.out.println(cnt);
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
