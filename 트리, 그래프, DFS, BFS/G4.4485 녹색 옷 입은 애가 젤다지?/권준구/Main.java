import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r, c, v;

        public Point(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }
    }

    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우 방향 이동을 위한 배열
    static int[] dc = { 0, 0, -1, 1 };

    static int N; // 지도의 크기
    static int[][] map, dist; // 지도 정보와 최소 비용 배열
    static final int INF = 2000000000; // 무한대를 나타내는 상수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int tc = 0;

        while (true) {
            tc++;
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            map = new int[N][N]; // 지도 정보 배열
            dist = new int[N][N]; // 최소 비용 배열

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], INF); // 최소 비용 배열을 무한대로 초기화
            }

            bfs(); // BFS로 최소 비용 계산
            sb.append("Problem " + tc + ": " + dist[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        boolean[][] visit = new boolean[N][N]; // 방문 여부를 저장하는 배열
        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.v - o2.v;
            }
        });

        queue.add(new Point(0, 0, map[0][0])); // 시작점을 큐에 추가
        visit[0][0] = true;
        dist[0][0] = map[0][0]; // 시작점의 최소 비용을 초기화

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.r == N - 1 && p.c == N - 1) {
                return; // 도착 지점에 도달하면 종료
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) {
                    continue; // 범위를 벗어나거나 이미 방문한 경우 무시
                }

                if (dist[nr][nc] > dist[p.r][p.c] + map[nr][nc]) {
                    dist[nr][nc] = dist[p.r][p.c] + map[nr][nc]; // 최소 비용 갱신
                    queue.add(new Point(nr, nc, dist[nr][nc])); // 큐에 추가
                    visit[nr][nc] = true; // 방문 표시
                }
            }
        }
    }
}
