import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Top-Down 11620 KB 76 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, count;
	static int[] num;
	static Long[] dp;

	static long recur(int level) {
		if (dp[level] == null) {
			dp[level] = recur(level - 1) + recur(level - 2);
		}
		return dp[level];
	}

	static void solution() {
		// d[n] = n자리 이친수.
		dp[0] = (long) 0;
		dp[1] = (long) 1;
		recur(count);
		System.out.println(dp[count]);
	}

	static void make() throws IOException {
		count = init(br.readLine());
		dp = new Long[count + 1];
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
