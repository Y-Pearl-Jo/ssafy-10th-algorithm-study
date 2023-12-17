// 85740 KB, 1260ms
import java.io.*;
import java.util.*;

class Ball implements Comparable<Ball>{
    int c,s,idx;
    public Ball(int c, int s, int idx){
        this.c = c;
        this.s = s;
        this.idx = idx;
    }

    @Override
    public int compareTo(Ball o) {
        if(o.s == this.s){
            return this.c - o.c;
        }
        return this.s - o.s;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static Ball[] balls;
    static int[] C,ans;
    static int[] S = new int[2001];

    static void prefix() throws IOException {
        int sum = 0;

        for(int i=0; i<N; i++){
            int color = balls[i].c;
            int size = balls[i].s;
            int idx = balls[i].idx;

            // 누적합
            sum += size;
            C[color] += size;
            S[size] += size;

            if(i!=0 && color == balls[i-1].c && size == balls[i-1].s){
                ans[idx] = ans[balls[i-1].idx];
            }
            else{
                ans[idx] = sum - C[color] - S[size] + size;
            }
        }
    }

    static void get() throws IOException {
        N = input(); // 공 개수
        balls = new Ball[N];
        C = new int[N+1];
        ans = new int[N];

        // 입력받기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int c = input(st);
            int s = input(st);
            balls[i] = new Ball(c,s,i);
        }

        // 정렬
        Arrays.sort(balls);
    }

    static void print() throws IOException {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            sb.append(ans[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        get();
        prefix();
        print();
    }


    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

}
