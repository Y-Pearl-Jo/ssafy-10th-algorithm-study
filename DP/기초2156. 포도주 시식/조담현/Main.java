import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// dp[i] = max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i], dp[i-1])
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
		int n = Integer.parseInt(br.readLine());
		// n이 2 이하일수도 있으므로 최대범위인 10000개의 배열 생성
    int[] arr = new int[10000];
		int[] dp = new int[10000];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = arr[0];
		dp[1] = arr[0] + arr[1];
		dp[2] = Math.max(arr[1]+arr[2], dp[0]+ arr[2]);
		dp[2] = Math.max(dp[2], dp[1]);
		if(n>=3) {
		for(int i=3; i<n; i++) {
			dp[i] = Math.max(dp[i-3]+arr[i-1]+arr[i], dp[i-2]+arr[i]);
			dp[i] = Math.max(dp[i], dp[i-1]);
		}
		}
		
		System.out.println(dp[n-1]);
	}

}
