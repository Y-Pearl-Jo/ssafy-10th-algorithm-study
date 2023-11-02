import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, C;
	static int[] arr;
	static Deque<Integer> deque;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 값을 저장할 덱
		deque = new LinkedList<Integer>();

		// 덱에 M개만큼 넣어놓음
		for (int i = 0; i < M; i++) {
			deque.add(arr[i]);
		}

		StringBuilder sb = new StringBuilder();
		// 만약 조건을 만족하면 추가
		if (check()) {
			sb.append(M - 1);
		}

		// M부터 N까지 가면서 앞에꺼 빼고 뒤에꺼 추가 만약 조건 만족시sb에 넣
		for (int i = M; i < N; i++) {
			deque.pollFirst();
			deque.add(arr[i]);
			if (check()) {
				sb.append(i).append("\n");
			}

		}
		System.out.println(sb);

	}

	static boolean check() {
		int[] arr = new int[M];
		int[] copy = new int[M];

		// 배열에 덱 값 넣어놓고
		for (int i = 0; i < M; i++) {
			arr[i] = deque.pollFirst();
		}

		// 복사해놓고
		copy(arr, copy);
		// 정렬
		Arrays.sort(arr);

		// 덱 원상복구
		for (int i = 0; i < M; i++) {
			deque.add(copy[i]);
		}
		// 만약 최댓값 - 최솟값이 C보다 작으면 true
		if (arr[M - 1] - arr[0] <= C) {
			return true;
		}
		// 아님 false
		return false;
	}

	static void copy(int[] arr, int[] copy) {
		for (int i = 0; i < M; i++) {
			copy[i] = arr[i];
		}
	}
}
