import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//Top-Down 14508 Kb 120 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N;
	static int[] wine;
	static Integer[] dp;

	static int recur(int level) {
		//Integer 형식이기에 사용한 방법
		//int 배열에 0으로 조건을 걸 수 않은 이유 : 모든 값이 0인 경우 시간 초과 날 수 있음
		if (dp[level] == null) {
			//현재 단계의 와인을 먹는 경우 최댓값을 우선 저장
			dp[level] = Math.max(recur(level - 3) + wine[level - 1], recur(level - 2)) + wine[level];
			//현재 단계에서 와인을 먹는 경우와 먹지 않는 경우 중 최댓값을 저장
			dp[level] = Math.max(dp[level], recur(level - 1));
		}
		return dp[level];
	}

	static void make() throws IOException {
		N = init(br.readLine());
		wine = new int[N + 1];
		dp = new Integer[N + 1];

		for (int i = 1; i < N + 1; i++) {
			wine[i] = init(br.readLine());
		}
	}

	static void solution() {
		dp[0] = 0;
		dp[1] = wine[1];
		//N의 개수가 1 이상이라서 1인 경우에 2가 들어가면 인덱스 벗어남
		if (N > 1)
			dp[2] = wine[1] + wine[2];

		System.out.println(recur(N));
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
