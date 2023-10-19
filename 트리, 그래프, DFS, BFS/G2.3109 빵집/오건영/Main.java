import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int result = 0;
    static int N, M;
    static boolean flag;
    static int[] dr = { -1, 0, 1 };
    static int[] dc = { 1, 1, 1 };
    static char[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        for (int i = 0; i < N; i++) {
            dfs(new Point(i, 0));
            flag = false;
        }
        System.out.println(result);
    }

    static void dfs(Point p) {
        if (p.y == M - 1) {
            result++;
            flag = true;
            return;
        }

        visit[p.x][p.y] = true;

        for (int i = 0; i < 3; i++) {
            int nr = p.x + dr[i];
            int nc = p.y + dc[i];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || visit[nr][nc] || map[nr][nc] == 'x') {
                continue;
            }
            map[nr][nc] = '0';
            dfs(new Point(nr, nc));
            if (flag == true) {
                break;
            }
        }
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
