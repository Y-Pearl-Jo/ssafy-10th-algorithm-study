// 51584 KB, 560 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int vertex,edge,ans;
	static List<Edge>[] graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		vertex = Integer.parseInt(br.readLine()); // 정점 수
		edge = Integer.parseInt(br.readLine()); // 간선 수
		
		// 그래프 선언, 초기화
		graph = new ArrayList[vertex+1];
		for(int i=0; i<graph.length; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 간선 정보 입력받기
		for(int i=0; i<edge; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[v1].add(new Edge(v2,cost));
			graph[v2].add(new Edge(v1,cost));

		}
		
		// 프림 알고리즘 수행
		prim(1,vertex);
		
		// 정답 출력
		System.out.println(ans);
		
	}
	
	// 프림 알고리즘
	static void prim(int start, int n) {
		boolean[] visited = new boolean[vertex+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// 1. 시작 정점 추가
		pq.offer(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			// 2. 가중치가 낮은 간선 선택
			Edge e = pq.poll();
			int v = e.w;
			int cost = e.cost;
			
			if(visited[v]) {
				continue;
			}
			
			visited[v] = true;
			ans += cost;
			
			for(Edge edge : graph[v]) {
				if(!visited[edge.w]) {
					pq.add(edge);
				}
			}		
		}	
	}
}

// 간선 클래스
class Edge implements Comparable<Edge>{
	int w; // 간선 들어오는 정점
	int cost; // 간선 가중치
	
	Edge(int w, int cost){
		this.w = w;
		this.cost = cost;
	}

	// 간선 오름차순 정렬
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
	
}
