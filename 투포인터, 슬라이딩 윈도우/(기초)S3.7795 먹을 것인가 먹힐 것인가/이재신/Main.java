import java.io.*;
import java.util.*;

//메모리 : 36696 KB, 시간 : 372 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// N: A의 수, M: B의 수
	static int N, M;
	// A: 포식, B: 피식
	static int[] A, B;

	public static void solution() {
		// 먹을 수 있는 쌍의 수
		int result = 0;

		for (int j = 0; j < N; j++) {
			int left = 0;
			int right = M - 1;
			int isEat = 0;

			// left가 right 지나갈 때 까지
			while (left <= right) {
				int mid = (left + right) >> 1;
				// 먹을 수 있는거면 더 큰 것도 가능한지
				if (B[mid] < A[j]) {
					left = mid + 1;
					isEat = mid + 1;
				}
				// 못 먹는거면 더 작은 거 확인하러
				else {
					right = mid - 1;
				}
			}
			// 이번 A 물고기가 먹을 수 있는 먹이 수
			result += isEat;
		}

		sb.append(result).append("\n");
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		A = new int[N];
		B = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = init(st);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			B[i] = init(st);
		}

		Arrays.sort(B);
	}

	public static void main(String[] args) throws IOException {
		int T = init();
		for (int i = 0; i < T; i++) {
			make();
			solution();
		}
		System.out.println(sb);
	}

	public static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

}
