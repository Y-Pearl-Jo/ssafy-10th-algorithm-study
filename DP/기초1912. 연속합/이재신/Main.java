import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static int[] num;
	static int[] dp;
	static int max;

	static void solution() {
		dp[0] = num[0];
    //점점 작아지는 배열의 경우 0번째 원소가 가장 큰 값이 됨
		max = dp[0];
		for(int i = 1; i < N; i++) {
      //이전까지 연속된 수의 최댓값 + i번째 값 과 i번째 값의 크기 비교
			dp[i] = Math.max(dp[i - 1] + num[i], num[i]);
      //N-1은 N-1까지 연속된 수들 중 최댓값이지 전체 배열 중에서 최댓값이 아님
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
	
	static void make() throws IOException {
		N = init(br.readLine());
		num = new int[N];
		dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
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
