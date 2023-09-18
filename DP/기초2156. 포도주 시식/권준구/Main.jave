import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];

		for(int i=1;i<N+1;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		int max = Integer.MIN_VALUE;

		dp[1] = arr[1];

		if (N == 2) {
			dp[N] = dp[1] + arr[2];
		}

		for (int i = 3; i < N + 1; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
		}

		for (int i = 1; i < N + 1; i++) {
			max = Math.max(max, dp[i]);
		}

		System.out.println(max);
	}
}
