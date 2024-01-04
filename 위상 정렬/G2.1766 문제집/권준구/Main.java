import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parentNum;
    static int[] answer;
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parentNum = new int[N + 1];
        answer = new int[N + 1];
        for (int i = 0; i < N + 1; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            parentNum[B]++;
        }

        topological();

        for (int i = 1; i < N + 1; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }

    static void topological() {
        Queue<Integer> q = new PriorityQueue<>();
        int idx = 1;

        for (int i = 1; i <= N; i++)
            if (parentNum[i] == 0) {
                q.add(i);
            }

        while (!q.isEmpty()) {
            int num = q.poll();

            answer[idx++] = num;

            for (int i = 0; i < graph.get(num).size(); i++) {
                int nextNum = graph.get(num).get(i);

                parentNum[nextNum]--;

                if (parentNum[nextNum] == 0) q.add(nextNum);
            }
        }
    }
}
