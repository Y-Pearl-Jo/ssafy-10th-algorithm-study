import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 *
 */

public class Main {
	static int dp[];
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int N, R ,Q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		dp = new int[N+1];
		for(int i=0; i<=N; i++) {
			tree.add(new ArrayList<Integer>());
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			tree.get(from).add(to);
			tree.get(to).add(from);
		}
		
		makeTree(R, R);
		
		for(int i=0; i<Q; i++) {
			int query = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp[query]));
			bw.write('\n');
		}
		bw.close();
	}
	
	public static void makeTree(int curNode, int parent) {
		dp[curNode] = 1;
		
		for(Integer next : tree.get(curNode)) {
			if(next!=parent) {
				makeTree(next, curNode);
				dp[curNode] += dp[next];
			}
		}
	}

}
