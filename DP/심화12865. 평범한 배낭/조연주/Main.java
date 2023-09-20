import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] W;
    static int[] V;
    static Integer[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        W = new int[N];
        V = new int[N];
        dp = new Integer[N][K+1];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(DP(N-1,K));

    }

    static int DP(int n, int k) {
        if(n<0){
            return 0;
        }
        else if(dp[n][k]==null){
            if(k-W[n]>=0){
                dp[n][k] = Math.max(DP(n-1,k), DP(n-1,k-W[n])+V[n]);
            }
            else{
                dp[n][k] = DP(n-1,k);
            }
        }

        return dp[n][k];

    }

}
