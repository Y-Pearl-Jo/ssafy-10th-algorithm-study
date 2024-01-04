import java.io.*;
import java.util.*;

public class Main {
    static int[] buildTime, dp, inDegree;
    static boolean[] visited;
    static int N;
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            buildTime = new int[N+1];
            dp = new int[N+1];
            inDegree = new int[N+1];
            visited = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph.get(s).add(e);
                inDegree[e]++;
            }

            int target = Integer.parseInt(br.readLine());

            for (int i = 1; i <= N; i++) {
                if (inDegree[i] == 0 & !visited[i]) {
                    bfs(i);
                }
            }

            System.out.println(dp[target]);
        }
    }

    static void bfs(int start) {
        Queue<int[]> queue = new ArrayDeque<>();
        visited[start] = true;
        dp[start] = buildTime[start];
        queue.offer(new int[] {start, buildTime[start]});
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int from = temp[0], dist = temp[1];
            for (int to: graph.get(from)) {
                inDegree[to]--;
                dp[to] = Math.max(dp[to], buildTime[to] + dp[from]);
                if (inDegree[to] == 0 && !visited[to]) {
                    queue.offer(new int[] {to, dp[to]});
                    visited[to] = true;
                }
            }
        }
    }

}
