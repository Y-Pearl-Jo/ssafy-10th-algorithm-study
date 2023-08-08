import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int exchange = Integer.parseInt(st.nextToken());
		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		} // 배열 채우기

		BubbleSort(N, exchange);

	}

	public static void BubbleSort(int N, int exchange) {
		int cnt = 0;

		loop: for (int i = N; i > 0; i--) {
			for (int j = 0; j < i - 1; j++) {
				if (A[j] > A[j + 1]) {
					int tmp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = tmp;
					cnt++;
					if (exchange == cnt) {
						System.out.println(A[j] + " " + A[j + 1]);
						break loop;
					}
				}
			}
		}
		if (exchange > cnt) {
			System.out.println(-1);
		}
	}
}
