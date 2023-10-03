import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 물품의 수
        int K = Integer.parseInt(st.nextToken());  // 버틸 수 있는 무게

        int[] dp = new int[K + 1];  // 배낭의 각 무게에 대한 최대 가치를 저장하는 배열
        int[][] arr = new int[N + 1][2];  // 물건의 무게와 가치를 저장하는 배열

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());  // 물건의 무게
            arr[i][1] = Integer.parseInt(st.nextToken());  // 물건의 가치
        }

//        // dp 배열 초기화
//        for (int i = 0; i <= K; i++) {
//            dp[i] = 0;  // 배낭의 무게가 0일 때 가치는 0
//        }

        // 물건을 하나씩 고려하면서 최대 가치를 계산
        for (int i = 1; i <= N; i++) {
            for (int j = K; j >= arr[i][0]; j--) { //무게가 큰 물건부터 고려
                // 현재 물건을 배낭에 담을 수 있는 경우와 담지 않을 경우 중에서 최대 가치 선택
                dp[j] = Math.max(dp[j], dp[j - arr[i][0]] + arr[i][1]);
            }
        }
        System.out.println(dp[K]);  // 배낭의 무게 제한이 K일 때 얻을 수 있는 최대 가치 출력
    }
}
