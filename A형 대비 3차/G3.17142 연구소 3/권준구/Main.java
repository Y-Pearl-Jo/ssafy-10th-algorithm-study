import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
//바이러스 M개 활성화 -> 많은 바이러스 중 M개 바이러스 활성화
//좌표를 보고 비활성화 된 바이러스는 전파x

//1. 총 바이러스 : 지도에서 2인 지점의 개수
//2. M개의 바이러스 활성화 -> 고른다 (조합)
//3. 조합의 겨로가로 BFS로 오염처리 ( 사방탐색 0탐색, 1,2탐색x )
//ㄴ 0을 오염시키고, 0이 사라지면 종료

//포인트
//'0이 없을 때'만 최소시간 구해야 한다.
//그래서 처음 map에 입력값 넣을 때 0 개수를 카운팅한다. 그리고 bfs를 진행할 때 0의 개수를 카운팅 한다.
//만약 0의 개수가 서로 다르면 바이러스를 퍼트리지 못한 부분이 있다는 것이니 그건 제외 시킨다.

class Virus {
    int r, c, d;

    public Virus(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

public class Main {
    static int[] dr = { -1, 1, 0, 0 }; // 상하좌우 이동을 위한 델타 배열
    static int[] dc = { 0, 0, -1, 1 };

    static int N, M, min, zero1;
    static int[][] map; // 지도 정보를 저장하는 배열
    static List<Virus> viruses = new ArrayList<>(); // 바이러스의 위치를 저장하는 리스트
    static Virus[] selectedViruses; // M개의 바이러스를 선택하기 위한 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 배열 크기
        M = Integer.parseInt(st.nextToken()); // M개의 바이러스를 선택

        map = new int[N][N]; // 지도 정보 배열 초기화

        zero1 = 0; // 초기 빈 공간 개수 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    viruses.add(new Virus(i, j, 0)); // 바이러스의 위치 저장
                } else if (map[i][j] == 0) {
                    zero1++; // 빈 공간 카운트
                }
            }
        }
        selectedViruses = new Virus[M]; // M개의 바이러스를 선택하기 위한 배열 초기화
        min = Integer.MAX_VALUE - 1; // 정답 초기화
        findMinimumTime(0, 0); // 조합을 통해 M개의 바이러스 선택

        System.out.println(min == Integer.MAX_VALUE - 1 ? -1 : min); // 결과 출력 (-1은 모든 빈 공간에 바이러스를 퍼뜨릴 수 없는 경우)
    }

    // 조합을 구하는 함수
    public static void findMinimumTime(int start, int depth) {
        if (depth == M) {
            spreadVirus(); // 선택된 M개의 바이러스로 바이러스 확산 계산
            return;
        }

        for (int i = start; i < viruses.size(); i++) {
            selectedViruses[depth] = viruses.get(i);
            findMinimumTime(i + 1, depth + 1);
        }
    }

    // BFS를 통해 바이러스가 퍼지는 최소 시간 계산
    public static void spreadVirus() {
        boolean[][] visit = new boolean[N][N];
        Queue<Virus> queue = new LinkedList<>(); // BFS를 위한 큐

        // 선택된 바이러스 기점으로 BFS 시작
        for (int i = 0; i < selectedViruses.length; i++) {
            queue.add(selectedViruses[i]);
            visit[selectedViruses[i].r][selectedViruses[i].c] = true; // 방문 표시
        }

        int zero2 = 0; // 오염된 지역의 개수 (나중에 처음 카운팅한 0의 개수와 비교할 것임)
        while (!queue.isEmpty()) {
            Virus v = queue.poll();

            if (map[v.r][v.c] == 0) {
                zero2++;
            }

            if (zero1 == zero2) {
                min = Math.min(min, v.d);
                return; // 모든 빈 공간에 바이러스가 퍼지면 최소 시간 갱신
            }

            for (int d = 0; d < 4; d++) {
                int nr = v.r + dr[d];
                int nc = v.c + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc] || map[nr][nc] == 1) {
                    continue;
                }
                visit[nr][nc] = true;
                queue.add(new Virus(nr, nc, v.d + 1));
            }
        }
    }
}
