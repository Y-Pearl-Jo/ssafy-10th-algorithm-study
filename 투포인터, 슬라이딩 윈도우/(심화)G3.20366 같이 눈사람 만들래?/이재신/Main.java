import java.io.*;
import java.util.*;

// 메모리: 13252 KB, 시간: 204 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// N: 눈덩이 개수, snow[]: 눈덩이들의 지름
	static int N, snow[];

	public static int compAB(int i, int j, int elsa) {
		int minDiff = Integer.MAX_VALUE;

		int left = 0;
		int right = N - 1;

		int anna, diff;
		while (left < right) {
			if (left == i || left == j) {
				left++;
				continue;
			}

			if (right == i || right == j) {
				right--;
				continue;
			}
			// 안나가 만든 눈사람의 키
			anna = snow[left] + snow[right];

			diff = elsa - anna;
			minDiff = Math.min(minDiff, Math.abs(diff));
			// 이번 차이가 0보다 큰 경우
			// 안나의 눈사람 크기 증가
			if (diff > 0) {
				left++;
			}
			// 이번 차이가 0보다 작은 경우
			// 안나의 눈사람 크기 감소
			else if (diff < 0) {
				right--;
			}
			// 차이가 0이면 그냥 종료
			else {
				System.out.print(0);
				System.exit(0);
			}
		}
		return minDiff;
	}

	public static void solution() {
		int elsa;
		int minDiff = Integer.MAX_VALUE;

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				// 엘사가 만든 눈사람의 키
				elsa = snow[i] + snow[j];
				// 모든 엘사와 비교 했을 때 최소 차이
				minDiff = Math.min(compAB(i, j, elsa), minDiff);
			}
		}
		System.out.println(minDiff);
	}

	public static void make() throws IOException {
		N = init();

		snow = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			snow[i] = init(st);
		}
		// 부분 수열 아님
		Arrays.sort(snow);
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	public static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

}
