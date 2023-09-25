// 다시풀게요
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] list, tree;
	static int[] parent, size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점 수
		int R = Integer.parseInt(st.nextToken()); // 루트 번호
		int Q = Integer.parseInt(st.nextToken()); // 쿼리 수
		
		parent = new int[N+1];
		size = new int[N+1];
		
		list = new ArrayList[N+1];
		tree = new ArrayList[N+1];
		for(int i=0; i<list.length; i++) {
			list[i] = new ArrayList<>();
			tree[i] = new ArrayList<>();
		}
		
		// 간선 정보 입력받기
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
			
		}
		
		// 모르겠어요
		makeTree(R,-1); // 루트 노드
		count(R);
		
		// 정답 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<Q; i++) {
			int query = Integer.parseInt(br.readLine());
			sb.append(size[query]).append("\n");
		}
		System.out.println(sb);
		
	}
	
	
	static void makeTree(int curNode, int p) {
		for(int node : list[curNode]) {
			if(node!=p) {
				tree[curNode].add(node);
				parent[node] = curNode;
				makeTree(node,curNode);
			}
		}
	}
	
	static void count(int curNode) {
		size[curNode] = 1;
		for(int node : tree[curNode]) {
			count(node);
			size[curNode] += size[node];
		}
		
	}

}
