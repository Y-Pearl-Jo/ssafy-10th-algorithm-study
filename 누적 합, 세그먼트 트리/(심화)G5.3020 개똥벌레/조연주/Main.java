// 29176 KB, 260 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,H,cnt;
    static int min = Integer.MAX_VALUE;
    static int[] top, bottom, sum;

    static void solve() throws IOException {
        obst(); // 높이에 따라 장애물 개수 세기
        prefix(); // 누적 합
        getMin();
    }

    static void obst() throws IOException {
        // 해당 높이의 장애물이 몇개 있는지
        for(int i=1; i<=N; i++){
            int h = input();
            switch (i%2){
                // 짝수 -> 바닥
                case 0:
                    bottom[h]++;
                    break;
                // 홀수 -> 천장
                case 1:
                    top[H-h+1]++;
            }
        }
    }

    static void prefix(){
        // 바닥에서 올라오기
        for(int i=1; i<=H; i++){
            bottom[i] += bottom[i-1];
        }
        // 천장에서 내려가기
        for(int i=H-1; i>=1; i--){
            top[i] += top[i+1];
        }
    }

    static void getMin(){
        for(int i=1; i<=H; i++){
            int obst = bottom[H]-bottom[i-1] + top[1]-top[i+1];
            if(obst<min){
                min = obst;
                cnt = 1;
            }
            else if(obst==min){
                cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st);
        H = input(st);
        top = new int[H+2];
        bottom = new int[H+2];
        solve();
        System.out.println(min+" "+cnt);
    }

    // -----------

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st) throws IOException {
        return Integer.parseInt(st.nextToken());
    }

}
