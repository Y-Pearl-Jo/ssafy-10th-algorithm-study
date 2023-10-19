import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, ans;
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int tcNum = 1;
		
		while(true){
			N = input(br.readLine());
			if(N==0) break;
			
			// 도둑 루피 크기
			arr = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					arr[i][k] = input(st.nextToken());
				}
			}
			
			// 다익스트라?
			visited = new boolean[N][N];
			ans = 0;
			dijstra();
			
			bw.write("Problem "+(tcNum++)+": "+ans+"\n");
			
		}
		
		bw.flush();
		bw.close();
		
	}
	
	static void dijstra() {		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		pq.offer(new Node(0,0,arr[0][0]));
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			
			for(int d=0; d<4; d++) {
				int nr = node.r + dr[d];
				int nc = node.c + dc[d];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc]) continue;
				
				pq.offer(new Node(nr,nc,arr[nr][nc]+node.cost));
				visited[nr][nc] = true;
				
				if(nr==N-1 &&  nc==N-1) {
					ans = node.cost + arr[nr][nc];
					return;
				}
				
			}
			
		}
		
	}
	
	static int input(String s) {
		return Integer.parseInt(s);
	}
}

class Node{
	int r;
	int c;
	int cost;
	public Node(int r, int c, int cost) {
		this.r = r;
		this.c = c;
		this.cost = cost;
	}
}
