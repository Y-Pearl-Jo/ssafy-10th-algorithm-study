import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 받기
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열의 길이
		int X = Integer.parseInt(st.nextToken()); // 연속된 수열의 길이

		int[] arr = new int[N]; // 정수 배열을 저장할 배열

		// 배열 입력 받기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int cnt = 0; // 최댓값을 갖는 수열의 개수를 저장하는 변수
		int max = Integer.MIN_VALUE; // 최댓값을 저장하는 변수
		int sum = 0; // 현재 수열의 합을 저장하는 변수

		// N-X+1 번 반복하여 모든 가능한 수열을 확인
		for (int i = 0; i < N - X + 1; i++) {
			int j = i + (X - 1); // 현재 수열의 끝 인덱스

			// 초기 수열을 만들고 최댓값 갱신
			if (i == 0) {
				for (int k = 0; k <= j; k++) {
					sum += arr[k];
				}
				max = sum;
				cnt = 1;
			} else {
				// 현재 수열의 합을 이전 수열의 합에서 뺀 후 새로운 수를 더함
				sum = sum - arr[i - 1] + arr[j];

				// 합이 최댓값과 같으면 가능한 수열의 개수 증가
				if (sum == max) {
					cnt++;
				}

				// 합이 최댓값보다 크면 최댓값과 가능한 수열의 개수 갱신
				if (sum > max) {
					max = sum;
					cnt = 1;
				}
			}
		}

		// 결과 출력
		if (max == 0) {
			System.out.println("SAD");
		} else {
			System.out.println(max); // 최댓값 출력
			System.out.println(cnt); // 가능한 수열의 개수 출력
		}
	}
}
