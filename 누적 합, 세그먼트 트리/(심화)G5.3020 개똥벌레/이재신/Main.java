import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, H;
    static int[] down, up;

    static void solution() {
        int min = N;
        int cnt = 0;
        for (int i = 1; i < H + 1; i++) {
            int diff = (down[H] - down[i - 1]) + (up[1] - up[i + 1]);

            if (diff < min) {
                min = diff;
                cnt = 1;
            } else if (diff == min) cnt++;
        }
        System.out.println(min + " " + cnt);
    }

    static void make() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = init(st);
        H = init(st);

        down = new int[H + 2];
        up = new int[H + 2];
        for (int i = 1; i <= (N) / 2; i++) {
            int a = init();
            int b = H - init() + 1;
            down[a]++;
            up[b]++;
        }
        for (int i = 1; i <= H; i++) {
            down[i] += down[i - 1];
        }

        for (int i = H; i >= 1; i--) {
            up[i] += up[i + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        make();
        solution();
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(StringTokenizer st) throws IOException {
        return Integer.parseInt(st.nextToken());
    }
}
