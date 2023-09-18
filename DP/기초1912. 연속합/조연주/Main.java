// 22324kb 204ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int max = Integer.MIN_VALUE;
	static int[] arr;
	static int[] dp;
	
	// main
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 배열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// DP
		dp = new int[N]; // idx까지의 최댓값 저장		
		for(int i=0; i<N; i++) {
			max = Math.max(max, DP(i));
		}	
		System.out.println(max);
	}
	
	// DP
	static int DP(int idx) {
		if(idx==0) {
			dp[idx] = arr[0];
			return dp[idx];
		}		
		
		dp[idx] = Math.max(dp[idx-1]+arr[idx], arr[idx]); // (이전 인덱스까지 최대합 + 현재 인덱스) vs 현재 인덱스 -> 큰 값을 저장
		return dp[idx];				
	}		
}
