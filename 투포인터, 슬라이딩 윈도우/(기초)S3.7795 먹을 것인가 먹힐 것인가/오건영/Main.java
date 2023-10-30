import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

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

			Arrays.sort(B);

			int result = 0;
			for (int i = 0; i < N; i++) {
				// 시작점
				int start = 0;
				// 끝점
				int end = M - 1;
				int index = 0;

				while (start <= end) {
					// 중간지점
					int mid = (start + end) / 2;
					// 중간이 더 작으면
					if (B[mid] < A[i]) {
						// 시작점을 중간 앞으로
						start = mid + 1;
						index = mid + 1;
					} else {
						end = mid - 1;
					}
				}
				result += index;
			}
			System.out.println(result);
		}
	}
}
