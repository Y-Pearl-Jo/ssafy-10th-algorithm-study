import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M,ans;
    static int[] A,B;

    public static void main(String[] args) throws IOException {
        int T = input();

        // Test Case
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            N = input(st);
            M = input(st);

            // A의 크기
            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                A[i] = input(st);
            }
            Arrays.sort(A);

            // B의 크기
            B = new int[M];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++){
                B[i] = input(st);
            }
            Arrays.sort(B);

            // A > B 인 경우의 수 구하기
            ans = 0;
            tp();

            System.out.println(ans);
        }
    }

    // two pointer
    static void tp(){

        int a = 0; // A의 index
        int b = 0; // B의 index
        int cnt = N;

        while(true){
            if(A[a]>B[b]){
                ans += cnt;
                b++;
            }
            else{
                cnt--;
                a++;
            }

            if(a==N || b==M || cnt==0)
                return;
        }

    }

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }
    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
