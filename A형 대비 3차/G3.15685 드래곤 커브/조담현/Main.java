import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int[][] map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			map[y][x] = 1;
			ArrayList<Integer> dirs = new ArrayList<>();
			switch (d) {
			case 0:
				map[y][x + 1] = 1;
				x = x + 1;
				break;
			case 1:
				map[y - 1][x] = 1;
				y = y - 1;
				break;
			case 2:
				map[y][x - 1] = 1;
				x = x - 1;
				break;
			case 3:
				map[y + 1][x] = 1;
				y = y + 1;	
				break;
			}
			dirs.add(d);
			drawCurve(y, x, dirs, g, 1, 1);
		}

		System.out.println(checkSquare());

	}

	public static int checkSquare() {
		int total = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100	; j++) {
				if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
					total++;
				}
			}
		}
		return total;
	}

	public static void drawCurve(int y, int x, ArrayList<Integer> dirs, int g, int line, int depth) {
		if (depth > g)
			return;
		int level = 1;
		int idx = 1;
		int size =  dirs.size();
		map[y][x] = 1;
		while (level++ <= line) {
			int d = (dirs.get(size - idx++) + 1) % 4;
			dirs.add(d);
			switch (d) {
			case 0:
				map[y][x + 1] = 1;
				x = x + 1;
				break;
			case 1:
				map[y - 1][x] = 1;
				y = y - 1;
				break;
			case 2:
				map[y][x - 1] = 1;
				x = x - 1;
				break;
			case 3:
				map[y + 1][x] = 1;
				y = y + 1;
				break;
			}
		}

		drawCurve(y, x, dirs, g, line * 2, depth + 1);
	}

}
