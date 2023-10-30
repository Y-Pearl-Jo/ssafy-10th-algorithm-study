//49124KB 376ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		int[] visiter = new int[N];
		Deque<Integer> dq = new LinkedList<>();

		// 방문자 수를 배열에 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			visiter[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0; // 부분합(X일 동안 들어온 방문자의 수)
		int maxSum = 0; // 최대 부분합
		int count = 0; // 최대 부분합이 나타나는 횟수

		for (int i = 0; i < N; i++) {

			// 제일 앞에 들어가 있는 원소가 범위를 벗어났다면
			while (!dq.isEmpty() && dq.peekFirst() <= i - X) {
				// 데큐에서 빼줌. 부분합에서도 빼줌
				sum -= visiter[dq.pollFirst()];
			}

			// 제일 뒤에 원소 추가
			dq.add(i);
			sum += visiter[i];

			// 앞에서 뺀 원소보다 뒤에 추가한 원소가 크면 maxSum값 갱신, 카운트 초기화
			if (maxSum < sum) {
				maxSum = sum;
				count = 1;
			}

			// 현재 최대 부분합이랑 같은 부분합이 발생하면 카운트 증가
			else if (maxSum == sum) {
				count++;
			}
		}
		if (maxSum == 0) {
			System.out.println("SAD");
			return;
		}
		sb.append(maxSum).append("\n").append(count);
		System.out.println(sb);
	}
}
