import java.util.*;
import java.io.*;
// 메모리 27476 KB, 시간 252 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int total = 0;
	static int MIN = Integer.MAX_VALUE;
	static int[][] people;
	static boolean[][] border;

	static void setBorder(int x, int y, int d1, int d2) {
		border = new boolean[N][N];

		// 경계선 세팅
		// (x, y), (x+1, y-1), ..., (x+d1, y-d1)
		// (x, y), (x+1, y+1), ..., (x+d2, y+d2)
		for (int i = 0; i <= d1; i++) {
			border[x + i][y - i] = true;
			border[x + d2 + i][y + d2 - i] = true;
		}
		// (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
		// (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		for (int i = 0; i <= d2; i++) {
			border[x + i][y + i] = true;
			border[x + d1 + i][y - d1 + i] = true;
		}
	}

	static void cntPeople(int x, int y, int d1, int d2) {
		int[] ward = new int[5];

		// 1번 선거구 (좌상단)
		// 1 ≤ r < x + d1, 1 ≤ c ≤ y
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (border[i][j])
					break;
				ward[0] += people[i][j];
			}
		}

		// 2번 선거구 (우상단)
		// 1 ≤ r ≤ x + d2, y < c ≤ N
		for (int i = 0; i <= x + d2; i++) {
			for (int j = N - 1; j > y; j--) {
				if (border[i][j])
					break;
				ward[1] += people[i][j];
			}
		}

		// 3번 선거구 (좌하단)
		// x + d1 ≤ r ≤ N, 1 ≤ c < y - d1 + d2
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (border[i][j])
					break;
				ward[2] += people[i][j];
			}
		}

		// 4번 선거구 (우하단)
		// x + d2 < r ≤ N, y - d1 + d2 ≤ c ≤ N
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = N - 1; j >= y - d1 + d2; j--) {
				if (border[i][j])
					break;
				ward[3] += people[i][j];
			}
		}

		// 5번 선거구 (중앙)
		// 전제 인원에서 1 ~ 4번 선거구의 인원 제외
		ward[4] = total;

		for (int i = 0; i < 4; i++) {
			ward[4] -= ward[i];
		}

		// 최대 최소 인원 선거구
		Arrays.sort(ward);

		// 인구수 최대와 최소의 차이의 최솟값
		MIN = Math.min(MIN, ward[4] - ward[0]);
	}

	static void solution() {
		// 기준점 (x, y)
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				// 경계의 길이 d1, d2 (d1, d2 >= 1)
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						// 1 ≤ x < x + d1 + d2 ≤ N
						// 1 ≤ y - d1 < y < y + d2 ≤ N
						if (x + d1 + d2 >= N || (y - d1 < 0 || y + d2 >= N))
							continue;
						setBorder(x, y, d1, d2);
						cntPeople(x, y, d1, d2);
					}
				}
			}
		}
		System.out.println(MIN);
	}

	static void make() throws IOException {
		N = init();

		people = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				people[i][j] = init(st);
				total += people[i][j];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		make();
		solution();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
