//14308KB 106ms
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
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 수열의 i번째 수부터 j번째 수까지의 합이 M이 되는 경우의 수
		int count = 0;

		// 모두 첫번째 원소에서 시작하는 경우 (두 포인터의 진행방향이 일치)
		int sum = 0; // 부분합
		int start = 0; // 두 포인터의 관계는 항상 'start <= end' 여야 함
		int end = 0;

		// 배열의 길이만큼 반복
		while (start < N) {

			// 부분합이 M보다 크거나 end가 N이면 start 포인터를 한 칸 오른쪽으로 이동
			if (sum > M || end == N) {
				sum -= arr[start]; // 더이상 포함되지 않는 칸 빼주고
				start++; // start 포인터 한 칸 이동

			} else {
				sum += arr[end]; // 새로 포함되는 칸 더해주고
				end++; // end 포인터 한칸 이동
			}

			// 부분합이 M과 같아지면 count 1 증가
			if (sum == M) {
				count++;
//				System.out.println("start" + start + " end " + end);
			}
		}

		// 출력
		System.out.println(count);
	}
}
