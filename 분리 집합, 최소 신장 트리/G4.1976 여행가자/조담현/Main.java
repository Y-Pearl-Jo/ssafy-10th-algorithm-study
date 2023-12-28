import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int [] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) parents[i] = i;
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int next = Integer.parseInt(st.nextToken());
                if(next > 0) {
                    setParent(i,j);
                }
            }
        }

        //여행 경로 입력
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int before = getParent(start);
        int cur = 0;
        String answer = "YES";
        while(st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());
            cur = getParent(next);
            //두 목적지의 부모가 다르면
            if (before != cur) {
                answer = "NO";
                break;
            }
            before = cur;
        }
        System.out.print(answer);
    }
    static void setParent(int i, int j) {
        int fst = getParent(i);
        int sec = getParent(j);

        //현재 부모가 다를때만
        if(fst != sec) {
            // 작은 수가 부모
            if (fst < sec) parents[sec] = fst;
            else parents[fst] = sec;
        }
    }
    static int getParent(int child) {
        return child == parents[child] ? child : getParent(parents[child]);
    }
}
