import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 2. 마을을 노드로, 마을간의 연결을 무방향 그래프라고 가정하자.
 * 2-1. 어떤 마을을 선택하면, 인접한 마을은 선택할 수 없다.
 * 2-2. 어떤 마을을 선택하지 않으면, 적어도 하나의 인접한 마을은 선택해야한다.
 * 2-2. dp로 top-down dfs로 내려가며 dp테이블에 n-1번째 노드 반환시 n번째 노드를 선택한 경우의 수와 선택하지 않은 경우의 수를 더해준다.
 * 2-3. dp[N][0] 은 N번째 마을을 선택하지 않았을 경우 주민 수(반드시 연결된 노드 하나 이상 선택), dp[N][1] 은 N번째 마을을 선택했을 때 주민 수(연결 노드를 반드시 선택하지 않아야)
 */

public class Main {
	static int dp[][];
	static ArrayList<ArrayList<Integer>> tree;
	static int[] people;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		// 2. 마을을 노드로, 마을간의 연결을 무방향 그래프라고 가정하자.
		tree = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		// 각 마을 번호에 해당하는 주민 수 저장
		people = new int [N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
				
		for(int i=1; i<=N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			tree.get(from).add(to);
			tree.get(to).add(from);
		}
		dfs(1, 1);
		
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	// 2-2. dp로 top-down dfs로 내려가며 dp테이블에 n-1번째 노드 반환시 n번째 노드를 선택한 경우의 수와 선택하지 않은 경우의 수를 더해준다.
	public static void dfs(int now, int start) {
		// 2-3. dp[N][0] 은 N번째를 선택하지 않았을 때 , dp[N][1] 은 N번째를 선택했을 때
		dp[now][0] = 0;
		dp[now][1] = people[now];
		
		boolean isAdj = false;
		int maxAdj = Integer.MIN_VALUE;
		int adjNode = 0;
		for(int i=0; i<tree.get(now).size(); i++) {
			int next = tree.get(now).get(i);
			if(next!=start) { // 출발한 노드로 돌아가지 않기 위한 조건
				dfs(next, now);
				// 현재 노드를 선택하지 않았을 때는 반드시 한가지 이웃은 선택
				if (dp[next][1]>=dp[next][0]) {
					dp[now][0] += dp[next][1];
					isAdj = true;
				} else {
					dp[now][0] += dp[next][0];
				}
				if(dp[next][1]>maxAdj) {
					maxAdj = dp[next][1];
					adjNode = next;
				}
				
				// 현재 노드를 선택했을 때는 인접노드를 선택하지 않는다.
				dp[now][1] += dp[next][0]; 
			}
		}
		if(!isAdj) {
			dp[now][0] = dp[now][0] - dp[adjNode][0] + maxAdj;
		}
 	}

}
