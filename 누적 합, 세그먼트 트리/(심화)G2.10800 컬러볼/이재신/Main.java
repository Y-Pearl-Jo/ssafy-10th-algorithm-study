import java.io.*;
import java.util.*;

class Ball implements Comparable<Ball> {
    int idx, color, size;

    public Ball(int idx, int color, int size) {
        this.idx = idx;
        this.color = color;
        this.size = size;
    }

    @Override
    public int compareTo(Ball o) {
        return this.size - o.size;
    }
}

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Ball[] balls;
    static int N;

    static void solution() {
        int[] result = new int[N];
        int[] colors = new int[N + 1];
        int ballIdx = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            Ball current = balls[i];
            while (balls[ballIdx].size < current.size) {
                sum += balls[ballIdx].size;
                colors[balls[ballIdx].color] += balls[ballIdx].size;
                ballIdx++;
            }
            result[current.idx] = sum - colors[current.color];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }

    static void make() throws IOException {
        N = init();
        balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            balls[i] = new Ball(i, init(st), init(st));
        }
        
        Arrays.sort(balls);
    }

    public static void main(String[] args) throws IOException {
        make();
        solution();
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
