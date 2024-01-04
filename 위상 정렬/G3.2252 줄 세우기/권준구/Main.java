import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph = new ArrayList<>();
    static StringBuffer sb = new StringBuffer();
    static int N;
    static int[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        count = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            count[B]++;
        }

        //위상 정렬
        topological();

        System.out.println(sb);
    }

    static void topological() {
        Queue<Integer> queue = new LinkedList<>();

        // 인라인이 없으면 큐에 넣어
        for (int i = 1; i < N + 1; i++)
            if (count[i] == 0)
                queue.add(i);

        for (int i = 0; i < N; i++) {
            if (!queue.isEmpty()) {
                int num = queue.poll();
                sb.append(num).append(" ");

                for (int j = 0; j < graph.get(num).size(); j++) {
                    count[graph.get(num).get(j)]--;

                    if (count[graph.get(num).get(j)] == 0)
                        queue.offer(graph.get(num).get(j));
                }
            }
        }
    }
}
