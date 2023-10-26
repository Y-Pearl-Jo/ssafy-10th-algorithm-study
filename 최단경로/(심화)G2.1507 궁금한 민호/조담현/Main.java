/*
1. 플로이드 와샬을 한번 더돌며 전체 N*N개에서 d[i][j] == d[i][k] + d[j][k]라면 boolean[][] 배열에서 true, 아니라면 false
1-1. 이때, i==k거나 j==k면 0을 더해주므로 직접가는 경로이다. 이 조건은 제외한다.
2. false인 좌표만 직접 가는 경로이므로 시간의 합을 더해준다.
3. d[i][j]가 d[i][k] + d[j][k] 즉 거쳐가는 경로보다 같거나 짧아야 최소경로인데, 크다면 최소경로가 아니므로 최소경로의 합을 찾는것이 불가능하다.
3-1. 만약 1->2 2->3 으로 가는 경로가 있는데
	 1 -> 3으로 가는 경로가 더 짧으면 OK.. 더 최적 경로가 있는 거니까
	  근데 1->3으로 가는 길이 거쳐서 가는 길보다 크다? 뭔가 문제가 있다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static int N;
	static int[][] map;
	static boolean[][] isDirect;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		isDirect = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 플로이드 와샬을 한번 더돌며 전체 N*N개에서 d[i][j] == d[i][k] + d[j][k]라면 boolean[][] 배열에서 true, 아니라면 false
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 이때, i==k거나 j==k면 0을 더해주므로 직접가는 경로이다. 이 조건은 제외한다.
					if(map[i][j]==map[i][k]+map[j][k]&& i!=k && j!=k) {
						isDirect[i][j] = true;
					}
					// d[i][j]가 d[i][k] + d[j][k] 즉 거쳐가는 경로보다 같거나 짧아야 최소경로인데, 크다면 최소경로가 아니므로 최소경로의 합을 찾는것이 불가능하다.
					if(map[i][j]>map[i][k]+map[j][k]&& i!=k && j!=k) {
						System.out.println(-1);
						System.exit(0);
					}
				}
			}
		}
		
		int result = 0;
		// 2. false인 좌표만 직접 가는 경로이므로 시간의 합을 더해준다.
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!isDirect[i][j]) {
					result+=map[i][j];
				}
			}
		}
		
		// i to j, j to i 두번을 더하므로 나누기 2를 해준다.
		answer = result == 0 ? -1 : result/2;
		System.out.println(answer);

	}
	


}
