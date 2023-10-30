// 42860KB 1000mx (ㅋㅋ)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		int T = Integer.parseInt(br.readLine()); // 테케 수
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // A의 수
			int M = Integer.parseInt(st.nextToken()); // B의 수

			int[] A = new int[N];
			int[] B = new int[M];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(A);
			Arrays.sort(B);

			int startA = 0;
			int startB = 0;
			int count = 0;

			while (startA < N) {

				if (A[startA] > B[startB]) {
					count++;
					startB++;
					if (startB == M) {
						startA++;
						startB = 0;
					}

				} else {
					startA++;
					startB = 0;
				}
			}

			System.out.println(count);

		}

	}
}
