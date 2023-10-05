import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map, dustmap;
    static int[] dc = {-1, 1, 0, 0};
    static int[] dr = {0, 0, -1, 1};
    static int robot1, robot2, N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        robot1 = 0;
        robot2 = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    if (robot1 != 0) {
                        robot2 = i;
                    } else {
                        robot1 = i;
                    }
                }
            }
        }

        while (T-- > 0) {
            dustmap = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j] != 0 && map[i][j] != -1) {
                        spread(new Point(i, j));
                    }
                }
            }
            copy();

            robot();
            robott();
        }

        int sum = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                sum += map[i][j];
            }
        }

        System.out.println(sum+2);
    }

    public static void spread(Point p) {
        int cnt = 0;
        int dust = map[p.x][p.y] / 5;
        for (int i = 0; i < 4; i++) {
            int nc = p.x + dc[i];
            int nr = p.y + dr[i];
            if (nc >= map.length || nr >= map[0].length || nc <= 0 || nr <= 0 || map[nc][nr] == -1) {
                continue;
            }

            dustmap[nc][nr] += dust;
            cnt++;
        }
        dustmap[p.x][p.y] += map[p.x][p.y] - dust * cnt;
    }

    public static void robot() {
        for (int i = robot1 - 1; i >= 2; i--) {
            map[i][1] = map[i - 1][1];
        }

        for (int i = 1; i <= M - 1; i++) {
            map[1][i] = map[1][i + 1];
        }

        for (int i = 1; i <= robot1 - 1; i++) {
            map[i][M] = map[i + 1][M];
        }

        for (int i = M; i >= 3; i--) {
            map[robot1][i] = map[robot1][i - 1];
        }

        map[robot1][1] = -1;
        map[robot1][2] = 0;
    }

    public static void robott() {
        for (int i = robot2 + 1; i < N; i++) {
            map[i][1] = map[i + 1][1];
        }

        for (int i = 1; i < M; i++) {
            map[N][i] = map[N][i + 1];
        }

        for (int i = N; i >= robot2 + 1; i--) {
            map[i][M] = map[i - 1][M];
        }

        for (int i = M; i >= 2; i--) {
            map[robot2][i] = map[robot2][i - 1];
        }

        map[robot2][1] = -1;
        map[robot2][2] = 0;

    }

    public static void copy() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = dustmap[i][j];
            }
        }
    }
}
