// 40680kb 436ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][] dp;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static int N,ans;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine()); // 대나무 숲 크기
		
		// 숲 정보 입력받기
		arr = new int[N][N];
		for(int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) {
				arr[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// DP:top-down
		dp = new int[N][N];
		for(int i=0; i<N; i++) {
			Arrays.fill(dp[i], -1); // 모든 값을 -1로
		}
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				DP(y,x);
			}
		}
		
		// 정답 출력
		System.out.println(ans);
		
	}
	
	// start 지점에서 갈 수 있는 경로 길이의 최댓값 -> 동서남북 나보다 더 큰 값이 있는 자리들 중 최댓값 +1
	static int DP(int y, int x) {
		if(dp[y][x]!=-1) {
			return dp[y][x];
		}
		
		boolean isEnd = true;
		
		// 현재 지점보다 크고 + 배열 밤위 안이라면
		for(int d=0; d<4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(nx>=0 && nx<N && ny>=0 && ny<N) {
				if(arr[ny][nx]>arr[y][x]) {
					dp[y][x] = Math.max(dp[y][x], DP(ny,nx)+1);
					isEnd = false;
				}		
			}
		}
		
		// 더 이동할 수 있는 곳이 없을 때
		if(isEnd) {
			dp[y][x]=1;
		}
		
		// 최댓값 저장
		ans = Math.max(ans, dp[y][x]);
		
		return dp[y][x];
	}
	
}
