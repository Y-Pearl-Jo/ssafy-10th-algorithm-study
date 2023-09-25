import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] list;  // 그래프의 인접 리스트를 저장할 배열
    static boolean visit[];
    static int dp[];  // 각 노드를 루트로 하는 서브트리의 노드 수를 저장할 배열

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(stk.nextToken());  // 노드의 개수
        int r = Integer.parseInt(stk.nextToken());  // 루트 노드
        int q = Integer.parseInt(stk.nextToken());  // 쿼리의 개수

        list = new ArrayList[n + 1];
        dp = new int[n + 1];
        visit = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());

            list[u].add(v);  // 양방향 그래프를 만들기 위해 간선 정보 저장
            list[v].add(u);
        }

        dfs(r);  // 루트 노드를 시작으로 DFS를 수행하여 dp 배열을 채움
        StringBuffer sb = new StringBuffer();
        while (q-- > 0) {
            int query = Integer.parseInt(br.readLine());  // 쿼리로 주어진 노드

            sb.append(dp[query]).append("\n");  // 해당 노드의 서브트리 크기 출력
        }
        System.out.println(sb);
    }

    public static int dfs(int now) {

        if (dp[now] != 0) return dp[now];  // 이미 방문한 노드의 경우 dp 값을 그대로 반환
        visit[now] = true;  // 현재 노드를 방문 표시
        int count = 1;  // 현재 노드를 포함한 서브트리 크기 초기화

        for (int node : list[now]) {
            if (visit[node]) continue;  // 이미 방문한 노드는 건너뛰기
            count += dfs(node);  // 현재 노드와 연결된 자식 노드들을 방문하며 서브트리 크기를 누적
        }
        dp[now] = count;  // 현재 노드를 루트로 하는 서브트리의 크기 저장

        return dp[now];
    }
}
