// 42156kb 332ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int r,c;
	public Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, ans;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1,0,1};

	// main
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = input(st);
		C = input(st);
		map = new char[R][C];
		visited = new boolean[R][C];
		
		// 맵 입력받기
		for(int i=0; i<R; i++) {
			String s = br.readLine();
			for(int k=0; k<C; k++) {
				map[i][k] = s.charAt(k);
			}
		}
		
		// dfs
		for(int i=0; i<R; i++) {
			if(dfs(i,0)) ans++;
		}
		
		System.out.println(ans);
		
	}
	
	// dfs
	static boolean dfs(int r, int c) {
		if(c==C-1) {
			return true;
		}
		
		else if(map[r][c]=='x') {
			return false;
		}
		
		for(int d=0; d<3; d++) {
			int nr = r+dr[d];
			int nc = c+1;
			
			if(!index(nr,nc) || visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			
			if(dfs(nr,nc)) {
				return true;
			}
			
		}
		
		return false;
	}

	// ---------------------------------
	static boolean index(int r, int c) {
		if (r < 0 || r >= R || c < 0 || c >= C)
			return false;
		return true;
	}

	static int input() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int input(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
