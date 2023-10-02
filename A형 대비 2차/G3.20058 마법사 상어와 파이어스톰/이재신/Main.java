import java.awt.Point;
import java.io.*;
import java.util.*;
// 메모리 37140 KB, 284 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, Q;
	// 행과 열의 크기
	static int size;
	// 가장 큰 덩어리의 크기
	static int maxSize = Integer.MIN_VALUE;
	static int[][] ice;
	static boolean[][] visited;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	static int[][] rotate(int len) {
		int[][] temp = new int[size + 1][size + 1];
		// 2^L * 2^L 크기의 부분 격자로 나눈다.
		for (int i = 1; i < size + 1; i += len)
			for (int j = 1; j < size + 1; j += len)
				// 모든 부분 격자를 시계방향으로 90도 회전
				for (int r = 0; r < len; r++)
					for (int c = 0; c < len; c++)
						temp[i + c][j + len - r - 1] = ice[i + r][j + c];

		return temp;
	}

	// 인접 칸에 얼음이 3곳 이상 있지 않은 칸은 얼음의 양이 1 감소
	static void decIce() {
		boolean[][] checkIce = new boolean[size + 1][size + 1];
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				// 얼음이 없는 곳
				if (ice[i][j] == 0)
					continue;
				int cnt = 0;
				// 인접 4칸 확인
				for (int d = 0; d < 4; d++) {
					int nR = i + dr[d];
					int nC = j + dc[d];
					// 인접 칸의 얼음 수 확인
					if (index(nR, nC) && ice[nR][nC] > 0) {
						cnt++;
					}

				}
				// 인접 칸에 얼음이 3칸 이상 있으면 감소 X
				if (cnt >= 3)
					checkIce[i][j] = true;
			}
		}
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				if (!checkIce[i][j] && ice[i][j] != 0)
					ice[i][j]--;
			}
		}
	}

	// 가장 큰 덩어리가 차지하는 칸의 개수
	// 얼음이 있는 칸이 얼음이 있는 칸 과 인접해 있으면, 두 칸은 연결
	static void findBiggest(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		visited[r][c] = true;
		int cnt = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nR = p.x + dr[i];
				int nC = p.y + dc[i];

				if (index(nR, nC) && !visited[nR][nC] && ice[nR][nC] > 0) {
					cnt++;
					visited[nR][nC] = true;
					q.add(new Point(nR, nC));
				}
			}
		}

		maxSize = Math.max(maxSize, cnt);
	}

	static void solution() throws IOException {
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = init(st);
			int partSize = (int) Math.pow(2, L);
			ice = rotate(partSize);
			decIce();
		}

		int total = 0;
		visited = new boolean[size + 1][size + 1];
		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				total += ice[i][j];
				if (!visited[i][j] && ice[i][j] != 0)
					findBiggest(i, j);
			}
		}
		System.out.println(total);
		System.out.println(maxSize == Integer.MIN_VALUE ? 0 : maxSize);
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		Q = init(st);

		size = (int) Math.pow(2, N);

		ice = new int[size + 1][size + 1];

		for (int i = 1; i < size + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < size + 1; j++) {
				ice[i][j] = init(st);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		make();
		solution();
	}

	static boolean index(int r, int c) {
		if (r > 0 && r <= size && c > 0 && c <= size) {
			return true;
		}
		return false;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
