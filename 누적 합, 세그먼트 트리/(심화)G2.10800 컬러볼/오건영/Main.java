import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 공(인덱스, 색깔, 크기)
class Ball implements Comparable<Ball> {
    int idx;
    int color;
    int size;

    public Ball(int idx, int color, int size) {
        this.idx = idx;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return "{" + "idx=" + idx + ", color=" + color + ", size=" + size + '}';
    }

    // 크기별 오름차순 정렬을 위함
    @Override
    public int compareTo(Ball o) {
        return this.size - o.size;
    }
}

public class Main {
    static int N, index, sum;
    static int[] result, color;
    // Ball 정보를 저장할 balls
    static ArrayList<Ball> balls = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        // 공의 갯수 N
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            balls.add(new Ball(i, C, S));
        }

        Collections.sort(balls);    // 크기별 오름차순으로 정렬

        index = 0;              // 현재 공 크기 전까지의 인덱스 정보를 저장
        sum = 0;                // 누적합 저장
        result = new int[N];    // 인덱스 별 합 정보를 저장할 result 배열
        color = new int[N + 1];     // 색깔별 size를 누적해서 저장해 나갈 color배열
                                    // 컬러 C는 1부터 N까지 주어지므로 N+1

        for (int i = 0; i < N; i++) {
            Ball now = balls.get(i);
            while (balls.get(index).size < now.size) {      // 현재 볼 전까지
                sum += balls.get(index).size;               // 크기 값을 저장
                color[balls.get(index).color] += balls.get(index).size; // color별 크기값도 저장
                index++;
            }
            result[now.idx] = sum - color[now.color];           // 현재 저장된 누적합에서 같은 컬러의 사이즈를 빼준다
        }

        sb = new StringBuilder();
        for(int x : result){
            sb.append(x).append("\n");
        }
        System.out.println(sb);
    }
}
