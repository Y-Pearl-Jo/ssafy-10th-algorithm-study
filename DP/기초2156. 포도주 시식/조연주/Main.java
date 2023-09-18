// 1330kb 108ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    static int[] arr;
    static int[] dp;
    static boolean[] written; // dp배열을 구했는지 여부

    // main ----------
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 포도주 잔 개수

        // 포도주의 양 입력받기
        arr = new int[n+1];
        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 출력
        dp = new int[n+1];
        written = new boolean[n+1];
        System.out.println(DP(n));


    }
    // ---------------

    // DP
    static int DP(int idx) {
        if(idx==0) {
            dp[0] = 0;
            written[0] = true;
            return dp[0];
        }

        if(idx==1) {
            dp[1] = arr[1];
            written[1] = true;
            return dp[1];
        }

        if(idx==2) {
            dp[2] = arr[1]+arr[2];
            written[2] = true;
            return dp[2];
        }

        if(!written[idx]){
            // 포도주를 먹는 경우 : 이전 포도주 먹는 경우 vs 안 먹는 경우
            dp[idx] = Math.max(DP(idx-3)+arr[idx-1]+arr[idx], DP(idx-2)+arr[idx]);

            // 포도주를 먹지 않는 경우 vs 포도주를 먹는 경우
            dp[idx] = Math.max(dp[idx], DP(idx-1));

            written[idx] = true;

        }

        return dp[idx];
    }

}
