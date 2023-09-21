import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int N;
    static int[][] map;
    static int[][] dp;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
 
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, DFS(i, j));
            }
        }
 
        System.out.println(ans);
    }
 
    public static int DFS(int r, int c) {
        // dp에 저장된 값이 있을 경우 그 값을 반환.
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        
        // 판다가 대나무 숲에서 최소 1칸을 이동하므로 1로 초기화
        dp[r][c] = 1;
 
        int nr, nc;

        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
          
            
            // 현재 대나무 숲보다 더 많은 양의 대나무가 있는 경우.
            if (nr>=0&&nc>=0&&nr<N&&nc<N&&map[r][c] < map[nr][nc]) {
                // 상하좌우 중에서 가장 오랫동안 이동할 수 있는 칸을 계산한다.
                dp[r][c] = Math.max(dp[r][c], DFS(nr, nc) + 1);
                DFS(nr, nc);
            }
        }
        return dp[r][c];
    }
 
}
