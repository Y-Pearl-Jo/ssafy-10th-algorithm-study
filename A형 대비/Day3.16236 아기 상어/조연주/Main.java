// 12408kb 88ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main{
  // 전역 변수
	static Shark shark;
	static int N;
	static boolean isPossible = true;
	static int dist;
	static int[][] map;
	static List<Node> eatable = new ArrayList<>();

  // 델타값
	static int[] dr = {1,0,-1,0};
	static int[] dc = {0,1,0,-1};

  // main
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = input(br.readLine()); // 공간 크기
		
		// 공간 정보 입력받기
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int k=0; k<N; k++) {
				int n = input(st.nextToken());
				
				if(n==9) shark = new Shark(i,k);
				else map[i][k] = n;
			}
		}
		
		int cnt = 0;
		while(isPossible) {
			isPossible = false;	
			find();
			if(isPossible) {
				eat();
				cnt += dist;
			}
		}
		
		System.out.println(cnt);
		
	}
	
	// 물고기 먹기
	static void eat() {
		if(eatable.size()>1) {
			eatable.sort(new Comparator<Node>() {
				@Override
				public int compare(Node n1, Node n2) {
					if(n1.r==n2.r) {
						return n1.c - n2.c;
					}
					return n1.r - n2.r;
				}
			});
		}
		
		Node fish = eatable.get(0); // 가장 가까운 물고기
		map[fish.r][fish.c] = 0; // 죽음
		
		// 이동
		shark.r = fish.r;
		shark.c = fish.c;
		
		// 먹기
		shark.ate++;
		if(shark.ate==shark.size) {
			shark.size++;
			shark.ate = 0;
		}		
    
		eatable.clear();
		
	}
	
	// BFS
	static void find() {
		// 거리 변수 설정
		dist = 0;
		
		// 큐, 방문배열
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// 첫번째 값 넣기
		q.offer(new Node(shark.r, shark.c));
		visited[shark.r][shark.c] = true;
		
		// 큐가 빌 때까지
		while(!q.isEmpty()) {
			List<Node> nodeList = new ArrayList<Node>();
			while(!q.isEmpty()) {
				nodeList.add(q.poll());
			}						
			
			// 이번 턴 노드리스트
			for(int i=0; i<nodeList.size(); i++) {
				Node node = nodeList.get(i);
				
				// 사방탐색
				for(int d=0; d<4; d++) {
					int nr = node.r + dr[d];
					int nc = node.c + dc[d];
					
					if(nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc] || map[nr][nc]>shark.size) continue;
					// 지나갈 수 있는 경우
					if(map[nr][nc]==shark.size || map[nr][nc]==0) {					
						q.offer(new Node(nr,nc));
						visited[nr][nc] = true;
					}
					
					// 먹을 수 있는 경우
					else if(map[nr][nc]<shark.size) {
						isPossible = true;
						eatable.add(new Node(nr,nc));
					}					
				}
			}
			
			dist++;
			
			// 종료 조건 : 먹을 수 있는 물고기를 찾은 경우
			if(!eatable.isEmpty()) return;
			
		}		
	}
	
	static int input(String s) {
		return Integer.parseInt(s);
	}	
}

class Node{
	int r;
	int c;
	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Shark{
	int r;
	int c;
	int size;
	int ate;
	public Shark(int r, int c) {
		this.r = r;
		this.c = c;
		size = 2;
		ate = 0;
	}
}


