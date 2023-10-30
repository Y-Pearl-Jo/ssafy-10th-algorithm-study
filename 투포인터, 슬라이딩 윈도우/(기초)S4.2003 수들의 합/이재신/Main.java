import java.io.*;
import java.util.*;

// 메모리 14284 KB, 시간 104 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	// N: 원소 개수, M: 목표 숫자
	static int N, M, arr[];

	public static void solution() {
		int left = 0, right = 0;
		int sum = 0, cnt = 0;

		while (true) {
			// 목표보다 크면 빼야함
			// 왼쪽 부터 빼는거임
			if (sum >= M) {
				sum -= arr[left++];
			}
			// 더 이상 불가능한 조건
			else if (right == N)
				break;
			// 오른쪽 하나 더함
			else {
				sum += arr[right++];
			}
			// 목표 숫자이면 카운트 증가
			if (sum == M) {
				cnt++;
			}
		}

		System.out.println(cnt);
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);

		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = init(st);
		}
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
