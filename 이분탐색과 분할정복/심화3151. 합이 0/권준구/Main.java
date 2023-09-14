import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		long cnt = 0;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for (int i = 0; i < N - 2; i++) {
			// 처음수는 음수만 가능하기 때문에
			if (arr[i] > 0)
				break;
			int start = i + 1;
			int end = N - 1;

			while (start < end) {
				int s = 1; // 나중에 앞에 똑같은 수가 있을 때 계산을 하기 위해
				int e = 1; // 뒤에 똑같은 수가 있을 때 계산을 하기 위해
				int sum = arr[i] + arr[start] + arr[end]; //

				if (sum == 0) { // 합이 0이라면
					if (arr[start] == arr[end]) {
						cnt += Combination(end - start + 1); // -2, 1, 1, 1, 1, 1, 1 이럴때 6C2
						break;
					}

					while (start + 1 < end && arr[start] == arr[start + 1]) {
						s++;
						start++;
					}

					while (start < end - 1 && arr[end - 1] == arr[end]) {
						e++;
						end--;
					}
					cnt += s * e;
				}

				if (sum > 0) {
					end--;
				} else {
					start++;
				}

			}
		}
		System.out.println(cnt);

	}

	public static int Combination(int x) {
		return x * (x - 1) / 2;
	}
}
