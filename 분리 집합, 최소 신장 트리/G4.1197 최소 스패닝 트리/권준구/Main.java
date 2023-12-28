import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 프림 : 모든 정점들을 연결했을 때, 연결되는 가중치의 최소 합
// 데이크스트라 : 이것은 모든 정점이 연결안될 수도 있음 그냥 최단 거리를 구할 때 사용함
// 두개 잘 구분해서 사용하기

public class Main {

	static class Edge{
		int from;
		int to;
		int w;
		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}	
	}

	static List<Edge> edgeList;
	static int sum;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 수
		int M = Integer.parseInt(st.nextToken()); // 간선의 수

		edgeList = new ArrayList<>();
		sum = 0;
		
		parent = new int[N+1];
		
		for(int i =1; i<=N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edgeList.add(new Edge(u,v,w));
		}
		
		Collections.sort(edgeList, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1,Edge o2) {
				return o1.w - o2.w;
			}
			
		});
		//// 연결까지 끝
		int cnt = 0;
		
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				sum += edge.w;
				if(++cnt == M-1) {
					break;
				}
			}
		}

		System.out.println(sum);

	}
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return false;
		}
		
		if(x <= y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
		return true;			
	}
}
