import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
1-1. (r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
1-2. 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
1-3. 확산되는 양은 Ar,c/5이고 소수점은 버린다.
1-4. (r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
2. 공기청정기가 작동한다.
2-1. 공기청정기에서는 바람이 나온다.
2-2. 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
2-3. 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
2-4. 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
*/
public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int R;
	static int C;
	static int[][] room;
	static int[] airCleaners;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		room = new int[R][C];
		airCleaners = new int[2];
		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j]==-1) {
					airCleaners[idx] = i;
					idx++;
				}
			}
		}

		for (int i = 0; i < T; i++) {
			diffuse();
			airCleaner();
		}
		
		int total = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] != 0 && room[i][j] != -1) {
					total += room[i][j];
				}
			}
		}
		System.out.println(total);

	}

	public static void diffuse() {
		int[][] temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			temp[i] = room[i].clone();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] != 0 && room[i][j] != -1) {
					int dust = room[i][j];
					for(int d=0; d<4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(nr>=0&&nc>=0&&nr<R&&nc<C&&room[nr][nc]!=-1) {
							temp[nr][nc] += dust/5;
							temp[i][j] -= dust/5;
						}
					}
				}
			}
		}
		room = temp;
	}

	private static void airCleaner() {
		int r1 = airCleaners[0];
		
		room[r1-1][0] = 0;
		for(int i = r1-1; i-1>=0; i--) {
			room[i][0] = room[i-1][0];
		}
		for(int j=0; j<C-1; j++) {
			room[0][j] = room[0][j+1];
		}
		for(int i=0; i<r1; i++) {
			room[i][C-1] = room[i+1][C-1];
		}
		for(int j=C-1; j>=2; j--) {
			room[r1][j] = room[r1][j-1];
		}
		room[r1][1] = 0;
		
		int r2 = airCleaners[1];
		
		room[r2+1][0] = 0;
		for(int i = r2+1; i+1<R; i++) {
			room[i][0] = room[i+1][0];
		}
		for(int j=0; j<C-1; j++) {
			room[R-1][j] = room[R-1][j+1];
		}
		for(int i=R-1; i>r2; i--) {
			room[i][C-1] = room[i-1][C-1];
		}
		for(int j=C-1; j>=2; j--) {
			room[r2][j] = room[r2][j-1];
		}
		room[r2][1] = 0;
		
	}

}
