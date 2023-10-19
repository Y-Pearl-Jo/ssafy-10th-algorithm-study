/*
위 쪽 대각선, 가운데, 아래 대각선 순서대로 가장 위쪽 길부터 DFS 탐색하는 그리디를 사용한다.
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int[] dr = {-1, 0, 1};
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		for(int i=0; i<R; i++) {
			dfsUp(i,0);
		}

		System.out.println(answer);

	}
	
	public static boolean dfsUp(int row, int col) {
		if(col==C-1) {
			answer++;
			// 연결되었음을 return
			return true;
		}
		
		for(int d=0; d<3; d++) {
			int nr = row+dr[d];
			int nc = col+1;
			if(nr<0||nc<0||nr>=R||nc>=C) continue;
			if(!visited[nr][nc]&&map[nr][nc]!='x') {
        // 방문했음을 처리한다.
				visited[nr][nc]=true;
				boolean isConnected = dfsUp(nr, nc);
				if(!isConnected) {
					// 연결되지 않았다면 다른 방향 탐색
					continue;
				}
				// 연결 했다면 해당 시작점에서의 경로는 더 확인 할 필요 없으므로 그대로true  return
				return isConnected;
			}
		}
    // 3방향 탐색 후 연결되지 않았다면 false를 return
		return false;
	}
	


}
