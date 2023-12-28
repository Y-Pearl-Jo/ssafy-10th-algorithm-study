// 54780 KB, 772 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] graph;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점 수
		int E = Integer.parseInt(st.nextToken()); // 간선 수
		
		// 부모 노드 배열 생성
		parent = new int[V+1];
		for(int i=0; i<V+1; i++) {
			parent[i] = i;
		}
		
		// 간선 정보 입력 받기
		graph = new int[E][3];
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 간선 -> 가중치에 따라 오름차순 정렬
		Arrays.sort(graph,(o1,o2)->o1[2]-o2[2]);
		
		
		// N-1개의 간선 생성 후 종료
		int cost = 0;
		for(int i=0; i<graph.length; i++) {
			// 사이클 형성하는 간선 제외 -> union-find 이용
			// 사이클 형성 조건 -> 두 정점(시작점, 끝점)의 조상 노드가 다름
			if(find(graph[i][0]) != find(graph[i][1])) {
				cost += graph[i][2];
				union(graph[i][0],graph[i][1]);
			}
		}
		
		
		// 정답 출력
		System.out.println(cost);
		
	}
	
	// union
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a<b) {
			parent[b] = a;
		}
		else {
			parent[a] = b;
		}
		
	}
	
	// find
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		else {
			return find(parent[x]);
		}
		
	}

}
