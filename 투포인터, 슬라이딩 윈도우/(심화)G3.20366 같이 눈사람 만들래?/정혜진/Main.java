//12416KB 216ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		// 눈덩이 배열 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		Arrays.sort(arr);

		int snowman1 = 0;
		int snowman2 = 0;
		int min = Integer.MAX_VALUE;

		// 이중 포문으로 첫 번째 눈사람 키 계산
		out:
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				snowman1 = arr[i] + arr[j];

				int start = 0;
				int end = N - 1;

				// 투포인터로 두 번째 눈사람 키 계산
				while (start < end) {

					if (start == i || start == j) {
						start++;
						continue;
					}

					if (end == j || end == i) {
						end--;
						continue;
					}
					
					snowman2 = arr[start] + arr[end];

					min = Math.min(min, Math.abs(snowman1 - snowman2));

					if(snowman1 < snowman2) {
						end--;
					}else if(snowman1 > snowman2) {
						start++;
					}else {
						break out;
					}
					
				}
			}
		}

		System.out.println(min);

	}
}
