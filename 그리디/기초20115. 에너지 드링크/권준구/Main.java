import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		double max = Integer.MIN_VALUE;

		int N = Integer.parseInt(br.readLine());

		double[] arr = new double[N];

		// 배열값입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			max = Math.max(max, arr[i]);
		}
		
		double sum = max;

		for (int i = 0; i < N; i++) {
			if (arr[i] == max)
				continue;
			sum += arr[i] / 2;
		}

		System.out.println(sum);

		// 1. 최대값 찾고
		// 2. 다 나누기 2를 하고 더해버린다.

	}
}
