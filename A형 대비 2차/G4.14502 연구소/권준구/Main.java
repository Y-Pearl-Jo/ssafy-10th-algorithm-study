import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우 방향을 표현하기 위한 배열
    static int[] dc = { 0, 0, -1, 1 };

    static int N, M, max, cnt; // 지도 크기 N, M, 최대 안전 영역 크기(max), 0의 개수(cnt) 변수
    static int[][] map; // 지도 정보를 저장하는 배열
    static boolean[][] visit; // 방문 여부를 저장하는 배열

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지도의 행 수
        M = Integer.parseInt(st.nextToken()); // 지도의 열 수

        map = new int[N][M]; // 지도 정보를 저장하는 배열 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 지도 정보 입력
            }
        }
        dfs(0); // 벽을 3개 세우는 모든 경우에 대해 탐색
        System.out.println(max); // 최대 안전 영역 크기 출력
    }

    // 벽을 3개 세우는 경우를 DFS로 탐색하는 함수
    public static void dfs(int depth) {
        if (depth == 3) { // 벽을 3개 세운 경우
            int num = bfs(); // 바이러스를 퍼뜨리고 안전 영역 크기를 계산
            max = Math.max(max, num); // 최대 안전 영역 크기 갱신
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { // 빈 공간인 경우
                    map[i][j] = 1; // 벽을 세우기 위해 1로 변경
                    dfs(depth + 1); // 다음 벽을 세우기 위해 재귀 호출
                    map[i][j] = 0; // 벽을 세우지 않는 경우를 위해 다시 0으로 변경
                }
            }
        }
    }

    // BFS로 바이러스를 퍼뜨리고 안전 영역 크기를 계산하는 함수
    public static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        visit = new boolean[N][M];
        cnt = 0;

        // 초기에 바이러스 위치를 큐에 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) { // 바이러스인 경우
                    queue.add(new Point(i, j)); // 큐에 추가
                }
            }
        }

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            for (int d = 0; d < 4; d++) { // 상하좌우로 바이러스 퍼뜨리기
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr >= 0 && nc >= 0 && nr < N && nc < M && !visit[nr][nc] && map[nr][nc] == 0) {
                    visit[nr][nc] = true;
                    queue.add(new Point(nr, nc)); // 바이러스를 퍼뜨림
                }
            }
        }

        // 안전 영역 크기 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visit[i][j]) {
                    cnt++; // 안전 영역 크기 증가
                }
            }
        }
        return cnt; // 안전 영역 크기 반환
    }
}
