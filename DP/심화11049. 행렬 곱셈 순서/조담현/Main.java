import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * dp테이블에는 start부터 end까지 행렬을 곱했을 때 최솟값을 저장한다.
 * dp테이블의 값을 inf로 초기화한다.
 * start부터 end까지 i번째에서 잘랐을 때, dp[start][end] = dp[start][i] + dp[i+1][end] + matrix[start][0]*matrix[i][1]*matrix[end][1]
 * i번째를 start와 end사이에서 조정해가며 최솟값을 찾는다.
 * if(start==end) return 0;
 */

public class Main {
	static int[][] dp;
	static int N;
	static int[][] matrix;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][2];
		dp = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			matrix[i][0] = r;
			matrix[i][1] = c;
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		System.out.println(solution(0,N-1));
		
	}
	
	public static int solution(int start, int end) {
		if(start==end) return 0;
		if(dp[start][end]!=Integer.MAX_VALUE) return dp[start][end];
		
		for(int i=start; i<end; i++) {
			int calCnt = solution(start, i) + solution(i+1, end) + matrix[start][0] * matrix[i][1] * matrix[end][1];
			dp[start][end] = Math.min(dp[start][end], calCnt);
		}
		
		return dp[start][end];
	}


}
