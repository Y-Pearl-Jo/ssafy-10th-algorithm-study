import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N, M;
	static int[][] map;
	static int[][] groupMap;
	static List<Point> BigGroup;
	static int BigRainbow;
	static int groupId;
	static int score = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			BigGroup = new ArrayList<>();
			groupMap = new int[N][N];
			BigRainbow = 0;
			groupId = 0;
			int total = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(groupMap[i][j]==0 && map[i][j]>0) {
						if(findBigGroup(i, j)) total++;
					}
				}
			}

			if(total==0) {
				System.out.println(score);
				break;
			}
			removeBlock();

			gravity();

			rotateMap();

			gravity();
			
		}
	}
	
	public static boolean findBigGroup(int r, int c) {
		boolean isGroup = true;
		int num = map[r][c];
		int rainbow = 0; // 무지개 블록 개수
		Queue<Point> queue = new LinkedList<>();
		List<Point> group = new ArrayList<Point>();
		boolean[][] visited = new boolean[N][N];
		groupId++;
		Point start = new Point(r, c);
		visited[r][c] = true;
		queue.add(start);
		group.add(start);
		groupMap[r][c] = groupId;
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			for(int d=0; d<4; d++) {
				int nr = now.x+dr[d];
				int nc = now.y+dc[d];
				
				if(nr>=0&&nc>=0&&nr<N&&nc<N&&!visited[nr][nc]&&groupMap[nr][nc]==0&&(map[nr][nc]==num||map[nr][nc]==0)) {
					visited[nr][nc] = true;
					groupMap[nr][nc] = groupId;
					Point next = new Point(nr, nc);
					queue.add(next);
					group.add(next);
					if(map[nr][nc]==0) {
						groupMap[nr][nc] = 0;
						rainbow++;
					}
				}
			}
		}
		if(group.size() == 1) {
			isGroup = false;
			groupId--;
			groupMap[r][c] = 0;
		}
		if(group.size()>=2) {
			if(group.size()>BigGroup.size()) {
				BigGroup = group;
				BigRainbow = rainbow; 
			}
			else if(group.size()==BigGroup.size()) {
				if(rainbow>BigRainbow) {
					BigGroup = group;
					BigRainbow = rainbow; 
				} else if (rainbow==BigRainbow) {
					if(group.get(0).x>BigGroup.get(0).x) {
						BigGroup = group;
						BigRainbow = rainbow; 
					} else if(group.get(0).x==BigGroup.get(0).x) {
						if(group.get(0).y>BigGroup.get(0).y) {
							BigGroup = group;
							BigRainbow = rainbow; 
						}
					}
				}
			}
		}
		
		return isGroup;
	}
	
	public static void removeBlock() {
		score+= BigGroup.size()*BigGroup.size();
		for(Point p : BigGroup) {
			map[p.x][p.y] = -9;
		}
	}
	
	public static void gravity() {
		for(int col=0; col<N; col++) {
			for(int row = N-2; row>=0; row--) {
				if(map[row][col]>-1) {
					for(int i = row+1; i<N; i++) {
						if(map[i][col]==-9) {
							map[i][col] = map[i-1][col];
							map[i-1][col] = -9;
						} else {
							break;
						}
					}
				}
			}
		}
	}
	
	public static void rotateMap() {
		int[][] temp = new int[N][N];
		for(int row=0; row<N; row++) {
			for(int col = 0; col<N; col++) {
				temp[row][col] = map[col][N-1-row];
			}
		}
		map = temp;
	}

}
