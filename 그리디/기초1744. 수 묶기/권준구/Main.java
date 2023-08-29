package baek;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// 음수
		// 1. 음수 * 음수 =양수
		// 2. 만약 음수가 홀수개면 나머지 하나는 그냥 더하기
		int result = 0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] num = new int[n];

		int minus = 0;
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(br.readLine());
			// 음수 카운트 하기 위해
			if (num[i] <= 0)
				minus++;
		}

		Arrays.sort(num);

		for (int i = 1; i < minus; i += 2)
			result += num[i - 1] * num[i];

		// 음수 홀수개 일때 처리
		if (minus % 2 == 1) {
			result += num[minus - 1];
		}
		// 양수 홀수개 일때 가장 작은 양수 더하기 처리
		if ((n - minus) % 2 == 1) {
			result += num[minus];
		}
		// 나머지 양수를 그냥 더하는 것과 두 수를 묶어 곱한 값 중 큰 값을 누적
		for (int i = n - 1; i > minus; i -= 2) {
			int sum = num[i] + num[i - 1];
			int mul = num[i] * num[i - 1];

			if (sum > mul)
				result += sum;
			else
				result += mul;
		}

		System.out.println(result);
	}

}
