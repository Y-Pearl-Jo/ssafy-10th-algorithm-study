// 14152kb 204ms
// 너무 어렵다
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int[] arr;
	static int[][] dp;
	static int N,y,x;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 행렬의 개수
		
		// 행렬 길이 입력받기 -> 행렬:N개 / 길이:N+1개
		arr = new int[N+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i+1] = Integer.parseInt(st.nextToken());
		}
		
		// DP:bottom-up
		dp = new int[N][N];
		for(int l=1; l<N; l++) { // 길이-1 : 추가로 얼마나 더 확인할지
			for(int s=0; s<N-l; s++) { // 시작점
				dp[s][s+l] = Integer.MAX_VALUE; // 시작 지점 ~ 시작지점+길이 -> 최솟값 구할거임
				for(int m=s; m<s+l; m++) { // 반으로 나누는 지점(첫번째 dp의 마지막 idx)
					int n = dp[s][m] + dp[m+1][s+l] + (arr[s]*arr[m+1]*arr[s+l+1]); // 여기가 너무 어려워요			
          
					dp[s][s+l] = Math.min(dp[s][s+l], n);
          
				}
			}
		}
		
		// 출력
		System.out.println(dp[0][N-1]);
		
	}

}
