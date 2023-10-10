import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Nation {
    int r, c;

    public Nation(int r, int c) {
        this.r = r;
        this.c = c;
    }

}

public class Main {
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int N, L, R, sum, ans;
    static int[][] map;
    static boolean[][] visit;
    static boolean isPossible;

    static List<Nation> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열 크기
        L = Integer.parseInt(st.nextToken()); // 최소 인구
        R = Integer.parseInt(st.nextToken()); // 최대 인구
        map = new int[N][N]; // 각 나라의 인구 정보를 저장할 배열

        // 나라별 인구 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0; // 결과값 초기화
        while (true) {
            isPossible = false; // 인구 이동 가능 여부 초기화
            visit = new boolean[N][N]; // 방문 여부를 저장할 배열 초기화

            // 모든 나라를 순회하면서 인구 이동을 검사
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        sum = 0;
                        visit[i][j] = true;
                        list.add(new Nation(i, j));
                        dfs(i, j); // 깊이 우선 탐색을 통해 연합 찾기

                        int size = list.size();

                        if (size > 1) {
                            isPossible = true;
                        }

                        int people = sum / size;
                        for (int h = 0; h < size; h++) {
                            map[list.get(h).r][list.get(h).c] = people;
                        }
                        list.clear();
                    }
                }
            }

            if (!isPossible) // 더 이상 인구 이동이 불가능하면 종료
                break;
            ans++;
        }

        System.out.println(ans); // 결과 출력
    }

    public static void dfs(int r, int c) {

        sum += map[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) {
                continue;
            }
            // 주변 나라와의 인구 차이가 L 이상 R 이하인 경우 연합에 포함
            int diff = Math.abs(map[r][c] - map[nr][nc]);
            if (L <= diff && diff <= R) {
                visit[nr][nc] = true;
                list.add(new Nation(nr, nc));
                dfs(nr, nc); // 주변 나라에 대해 재귀 호출
            }
        }
    }
}
