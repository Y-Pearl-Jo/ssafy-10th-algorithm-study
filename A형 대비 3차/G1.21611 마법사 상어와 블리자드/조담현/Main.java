import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int N, M;
	static int[][] map;
	static Point[] idxMap;
	static int center;
	static int[] countMarble = new int[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		idxMap = new Point[N * N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		center = (N + 1) / 2 - 1;
		initIdxMap();
		idxMap[0] = new Point(center, center);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			breakMarble(d, s);
			moveMarble();
			explosion();
			transform();
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//		for(int i=0; i<N*N; i++) {
//			System.out.println(idxMap[i].x + " "+ idxMap[i].y);
//		}
		
		System.out.println(countMarble[1]+countMarble[2]*2+countMarble[3]*3);
	}

	private static void initIdxMap() {
		boolean[][] visited = new boolean[N][N];
		int idx = N * N - 1;
		int row = 0;
		int col = 0;
		int d = 0;
		visited[0][0] = true;
		while (true) {
			int nr = row + dr[d];
			int nc = col + dc[d];
			if(idx==0) break;
			if (nr >= N || nc >= N || nr < 0 || nc < 0 || visited[nr][nc]) {
				d = (d + 1) % 4;
				continue;
			} 
			idxMap[idx--] = new Point(row, col);
			visited[nr][nc] = true;
			row = nr;
			col = nc;
		}

	}

	private static void breakMarble(int dir, int s) {
		int r = center;
		int c = center;
		int d = 0;
		switch (dir) {
		case 1:
			d = 3;
			break;
		case 2:
			d = 1;
			break;
		case 3:
			d = 2;
			break;
		case 4:
			d = 0;
			break;
		}
		for (int i = 0; i < s; i++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			map[nr][nc] = 0;
			r = nr;
			c = nc;
		}
	}

	private static void moveMarble() {
		for (int i = 1; i < N * N; i++) {
			Point now = idxMap[i];
			if (map[now.x][now.y] == 0) {
				int cpyTo = i;
				for (int j = i + 1; j < N * N; j++) {
					Point next = idxMap[j];
					if (map[next.x][next.y] != 0) {
						for(int k=j; k<N*N; k++) {
							Point cpyFrom = idxMap[k];
							map[idxMap[cpyTo].x][idxMap[cpyTo].y] = map[cpyFrom.x][cpyFrom.y];
							map[cpyFrom.x][cpyFrom.y] = 0;
							cpyTo++;
						}
						break;
					}
				}
			}
		}
	}

	private static void explosion() {
		int sequence = 1;
		while(true) {
			List<Point> marbleToExplode = new ArrayList<>(); 
			for(int i=1; i<idxMap.length; i++) {
				if(map[idxMap[i].x][idxMap[i].y]!= map[idxMap[i-1].x][idxMap[i-1].y]||i==idxMap.length-1||map[idxMap[i].x][idxMap[i].y]==0) {
					if(i==idxMap.length-1&&map[idxMap[i].x][idxMap[i].y]== map[idxMap[i-1].x][idxMap[i-1].y]) sequence++;
					if(sequence>=4) {
						for(int j=i-1; j>=i-sequence; j--) {
							marbleToExplode.add(idxMap[j]);
						}
					}
					sequence = 1;
				}
				else {
					sequence++;
				}
			}
			if(marbleToExplode.size()==0) break;
			for(Point p : marbleToExplode) {
				countMarble[map[p.x][p.y]]++;
				map[p.x][p.y] = 0;
			}
			moveMarble();
		}

	}

	private static void transform() {
		int[][] temp = new int[N][N];
		int sequence = 1;
		ArrayList<ArrayList<Point>> groupList = new ArrayList<>();
		for(int i=1; i<idxMap.length; i++) {
			if(map[idxMap[i].x][idxMap[i].y]!= map[idxMap[i-1].x][idxMap[i-1].y]||i==idxMap.length-1) {
				if(map[idxMap[i-1].x][idxMap[i-1].y]!=0) {
					ArrayList<Point> group = new ArrayList<>();
					for(int j=i-1; j>=i-sequence; j--) {
						group.add(idxMap[j]);
					}
					groupList.add(group);
				}
				sequence = 1;
			} else {
				sequence++;
			}
		}
		temp[center][center] = 0;
		int idx = 1;
		Loop : while(true) {
			for(ArrayList<Point> group : groupList) {
				int A = group.size();
				int B = map[group.get(0).x][group.get(0).y];
				temp[idxMap[idx].x][idxMap[idx].y] = A;
				if(++idx>=N*N) break Loop;
				temp[idxMap[idx].x][idxMap[idx].y] = B;
				if(++idx>=N*N) break Loop;
			}
			break;
		}
		map = temp;
	}

}
