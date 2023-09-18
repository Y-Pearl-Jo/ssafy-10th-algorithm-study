import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// Top-Down 39160 KB 232 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, max;
	static int[] num;
	static Integer[] dp;
	
	static int recur(int level) {
    
		if (dp[level] == null) {
			dp[level] = Math.max(recur(level - 1) + num[level], num[level]);
			max = Math.max(max, dp[level]);
		}
    
		return dp[level];
	}
	
	static void solution() {
		dp[0] = num[0];
		// 점점 작아지는 배열의 경우 0번째 원소가 가장 큰 값이 됨
		max = dp[0];
		recur(N - 1);
		System.out.println(max);
	}

	static void make() throws IOException {
		N = init(br.readLine());
		num = new int[N];
		dp = new Integer[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = init(st.nextToken());
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
