import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Bottom-Up 11492 KB 76 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, count;
	static int[] num;
	static long[] dp;

	static void solution() {
		// d[n] = n자리 이친수.
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= count; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		System.out.println(dp[count]);
	}

	static void make() throws IOException {
		count = init(br.readLine());
		dp = new long[count + 1];
	}

	public static void main(String args[]) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
