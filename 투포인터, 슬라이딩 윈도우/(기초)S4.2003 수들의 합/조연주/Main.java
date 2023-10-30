import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M,ans;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st);
        M = input(st);
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = input(st);
        }

        tp(); // 투 포인터
        System.out.println(ans);

    }

    // 투 포인터
    static void tp(){
        int left = 0;
        int right = 0;
        int sum = 0;

        while(true){
            if(sum>=M){
                sum -= arr[left++];
            }
            else if(right==N){
                break;
            }
            else {
                sum += arr[right++];
            }

            if(sum==M){
                ans++;
            }
        }

    }

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }
    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
