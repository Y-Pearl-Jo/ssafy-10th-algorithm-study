import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, cnt;              // 지도의 크기 N, M 및 청소 횟수 cnt를 저장하는 변수 선언
    static int[][] map;                // 지도 정보를 저장하는 배열
    static boolean[][] visit;          // 청소 여부를 저장하는 배열

    static int[] dr = { -1, 0, 1, 0 }; // 북, 동, 남, 서 방향으로 이동하기 위한 배열
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[N][M];             // 지도 정보 배열 초기화
        visit = new boolean[N][M];       // 청소 여부 배열 초기화

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());  // 지도 정보 입력
            }
        }

        visit[r][c] = true;  // 초기 위치 청소 및 청소 횟수 증가
        cnt = 1;
        clean(r, c, d);      // 로봇 청소기 동작 시작

        System.out.println(cnt); // 최종 청소 횟수 출력
    }

    // 로봇 청소기 동작 함수
    public static void clean(int row, int col, int direction) {
        for (int d = 0; d < 4; d++) {   // 현재 방향을 기준으로 4방향을 탐색
            direction = (direction + 3) % 4;  // 왼쪽 방향으로 회전

            int nr = row + dr[direction];     // 회전 후 위치 계산
            int nc = col + dc[direction];

            if (nr >= 0 && nc >= 0 && nr < N && nc < M && map[nr][nc] == 0 && !visit[nr][nc]) {
                visit[nr][nc] = true;  // 청소 가능한 위치라면 청소하고 청소 횟수 증가
                cnt++;
                clean(nr, nc, direction); // 청소 후 해당 위치로 이동하여 재귀 호출
                return;                  // 재귀 호출 후에는 함수 종료 (이동 완료)
            }
        }

        int back = (direction + 2) % 4; // 현재 방향에서 후진하는 방향 계산
        int br = row + dr[back];        // 후진 후 위치 계산
        int bc = col + dc[back];

        if (br >= 0 && bc >= 0 && br < N && bc < M && map[br][bc] != 1) {
            clean(br, bc, direction);   // 후진 가능하면 후진하고 해당 위치로 이동하여 재귀 호출
        }
    }
}
