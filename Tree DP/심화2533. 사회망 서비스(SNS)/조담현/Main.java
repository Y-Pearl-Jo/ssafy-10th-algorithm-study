import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 1. 현재 노드를 선택하면 자식 노드를 선택하지 않아도 된다.
 * 1-1. DFS로 부모부터 내려가며 현재노드를 선택한 경우와 현재노드를 선택하지 않은 경우로 나누어 재귀 시작
 * 1-2. 현재노드를 선택한 경우 자식의 자식노드를 선택, 현재노드를 선택하지 않은 경우 자식노드를 선택
 * 1-3. 이 가정은 반례가 있음. 1 2 1 3 1 4 / 3 5 3 6 3 7 구조일 때 1과 3인 부모 자식을 선택하는 것이 최적해
 * 
 * 2-0. 자기 자신을 선택하면 연결된노드를 선택해도 되고, 선택하지 않아도 됨(2가지 경우의 수)
 * 2-1. 자기 자신을 선택하지 않으면 연결된 노드를 반드시 선택해야 함(1가지 경우의 수)
 * 2-2. dp로 top-down dfs로 내려가며 dp테이블에 n-1번째 노드 반환시 n번째 노드를 선택한 경우의 수와 선택하지 않은 경우의 수를 더해준다.
 * 2-3. dp[N][0] 은 N번째를 선택하지 않았을 때 얼리어답터의 수(반드시 연결된 노드 선택), dp[N][1] 은 N번째를 선택했을 때 얼리어답터의 수(연결 노드 선택해도 되고 안해도 되고)
 */

public class Main {
	static int dp[][];
	static ArrayList<ArrayList<Integer>> tree;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		tree = new ArrayList<>();
		for(int i=0; i<N+1; i++) {
			tree.add(new ArrayList<Integer>());
		}
		
		// 트리를 단방향 그래프라 가정하고 트리를 생성한다. 자식에서 부모로 다시 가는 순환없이 dfs로 항상 자식 노드에 대해서만 탐색할 것이므로 - X 
		// 이렇게하면 루트노드(시작노드)를 반드시 알아야함
		// 트리를 무방향 그래프라 가정하고 연결된 노드간의 그래프로 바라보아야 함
		for(int i=0; i<N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			tree.get(from).add(to);
			tree.get(to).add(from);
		}
		
		dfs(1, 1);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	// 2-2. dp로 top-down dfs로 내려가며 dp테이블에 n-1번째 노드 반환시 n번째 노드를 선택한 경우의 수와 선택하지 않은 경우의 수를 더해준다.
	public static void dfs(int now, int start) {
		// 2-3. dp[N][0] 은 N번째를 선택하지 않았을 때 얼리어답터의 수(반드시 연결노드 선택), dp[N][1] 은 N번째를 선택했을 때 얼리어답터의 수(연결노드 선택해도 되고 안해도 되고)
		dp[now][0] = 0;
		dp[now][1] = 1;
		
		for(int i=0; i<tree.get(now).size(); i++) {
			int next = tree.get(now).get(i);
			if(next!=start) { // 출발한 노드로 돌아가지 않기 위한 조건
				dfs(next, now);
				// 현재 노드를 선택하지 않았을 때는 연결노드를 (반드시)선택했을때의 합을 더해주고 
				dp[now][0] += dp[next][1];
				// 현재 노드를 선택했을 때는 연결 노드를 선택했을 때와 선택하지 않았을때의 합중 최솟값을 더해준다.
				dp[now][1] += Math.min(dp[next][0], dp[next][1]); 
			}
		}
	}

}
