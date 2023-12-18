import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static int[] down, up;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 인덱스 오류를 피하기 위해 +2
        down = new int[H + 2];
        up = new int[H + 2];

        for (int i = 0; i < N / 2; i++) {
            down[Integer.parseInt(br.readLine())]++;
            up[Integer.parseInt(br.readLine())]++;
        }

        for (int i = H; i > 0; i--) {
            down[i] += down[i + 1];
            up[i] += up[i + 1];
        }

        // 최솟값
        int min = N;
        // 횟수
        int cnt = 0;

        for (int i = 1; i <= H; i++) {
            // up의 인덱스는 임의로 설정한거라 실제 연산할때는 역순으로 바꿔줘야함
            // i:1 -> up:7, i:2 -> up:6
            int temp = down[i] + up[H - i + 1];
            if (temp < min) {
                min = temp;
                cnt = 1;
            } else if (temp == min) {
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }
}
