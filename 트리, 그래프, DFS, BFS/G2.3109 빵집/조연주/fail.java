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
	static int[] dc = {1,1,1};

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
		
		// 확인
//		for(int i=0; i<R; i++) {
//			for(int k=0; k<C; k++) {
//				char c = map[i][k];
//				if(c=='x') System.out.print(1+" ");
//				else System.out.print(0+" ");
//			}
//			System.out.println();
//		}
		
		// dfs
		for(int i=0; i<R; i++) {
			dfs(i,0);
		}
		
		System.out.println(ans);
		
	}
	
	// dfs
	static void dfs(int r, int c) {
		if(c==C-1) {
			System.out.println(r+" "+c);
			ans++;
			return;
		}
		
		for(int d=0; d<3; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!index(nr,nc) || visited[nr][nc] || map[nr][nc]=='x') continue;
			
			visited[nr][nc] = true;
			dfs(nr,nc);
			break;
		}
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
