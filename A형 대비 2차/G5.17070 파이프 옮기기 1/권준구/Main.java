import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int res, n;
    static int[][] h;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 입력으로 주어지는 격자의 크기
        h = new int[n][n]; // 격자 정보를 저장하는 배열

        // 격자 정보 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                h[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        h[0][0] = 1;
        h[0][1] = 1;
        int x = 0;
        int y = 1;
        // 현재 위치
        int d = 0;
        // d : 0(가로) 1(세로) 2(대각선)
        moving_pipe(x, y, d);
        System.out.println(res); // 파이프를 끝까지 움직이는 경우의 수 출력
    }

    public static void moving_pipe(int x, int y, int d) {
        switch (d) {
            case 0: // 현재 방향이 가로
                if (y + 1 < n && h[x][y + 1] == 0) { // 오른쪽으로 이동 가능한 경우
                    if (y + 1 == n - 1 && x == n - 1) { // 끝에 도달한 경우
                        res++;
                        return;
                    }
                    moving_pipe(x, y + 1, 0);
                }
                if (x + 1 < n && y + 1 < n) {
                    if (h[x + 1][y] == 0 && h[x][y + 1] == 0 && h[x + 1][y + 1] == 0) {
                        if (x + 1 == n - 1 && y + 1 == n - 1) {
                            res++;
                            return;
                        }
                        moving_pipe(x + 1, y + 1, 2);
                    }
                }
                break;

            case 1: // 현재 방향이 세로
                if (x + 1 < n && h[x + 1][y] == 0) { // 아래로 이동 가능한 경우
                    if (x + 1 == n - 1 && y == n - 1) { // 끝에 도달한 경우
                        res++;
                        return;
                    }
                    moving_pipe(x + 1, y, 1);
                }
                if (x + 1 < n && y + 1 < n) {
                    if (h[x + 1][y] == 0 && h[x][y + 1] == 0 && h[x + 1][y + 1] == 0) {
                        if (x + 1 == n - 1 && y + 1 == n - 1) {
                            res++;
                            return;
                        }
                        moving_pipe(x + 1, y + 1, 2);
                    }
                }
                break;

            case 2: // 현재 방향이 대각선
                if (y + 1 < n && h[x][y + 1] == 0) { // 오른쪽으로 이동 가능한 경우
                    if (y + 1 == n - 1 && x == n - 1) { // 끝에 도달한 경우
                        res++;
                        return;
                    }
                    moving_pipe(x, y + 1, 0);
                }
                if (x + 1 < n && h[x + 1][y] == 0) { // 아래로 이동 가능한 경우
                    if (x + 1 == n - 1 && y == n - 1) { // 끝에 도달한 경우
                        res++;
                        return;
                    }
                    moving_pipe(x + 1, y, 1);
                }
                if (x + 1 < n && y + 1 < n) {
                    if (h[x + 1][y] == 0 && h[x][y + 1] == 0 && h[x + 1][y + 1] == 0) {
                        if (x + 1 == n - 1 && y + 1 == n - 1) {
                            res++;
                            return;
                        }
                        moving_pipe(x + 1, y + 1, 2);
                    }
                }
                break;
        }
    }
}
