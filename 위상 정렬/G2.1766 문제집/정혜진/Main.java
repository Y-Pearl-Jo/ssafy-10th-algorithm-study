// 51858KB 780ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 간선의 개수

        List<Integer>[] adjList = new ArrayList[V + 1]; // 인접리스트/ 정점의 번호는 1부터 V까지의 정수값
        int[] degree = new int[V + 1]; // 진입 차수를 저장할 배열.

        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>(); // 각 정점의 인접 리스트 초기화
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adjList[x].add(y); // 유향 그래프
            degree[y]++; // 진입 차수 증가

        }

        // ---------------아래부터 위상정렬--------------------

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 진입차수가 0인 정점을 넣을 큐

        // 1. 진입차수가 0인 정점들의 인덱스를 큐에 넣음
        for (int i = 1; i <= V; i++) {
            if (degree[i] == 0) pq.add(i);
        }

        // 2. Queue가 공백상태가 될 때까지 반복 수행
        while (!pq.isEmpty()) {
            int node = pq.poll(); // 하나 꺼내
            System.out.print(node + " ");

            for (int i = 0; i < adjList[node].size(); i++) {
                int next = adjList[node].get(i);
                degree[next]--; // 진입 차수 감소

                // 3.만약 노드의 진입차수가 0이 되었다면
                if (degree[next] == 0) {
                    pq.add(next); // 큐에 넣는다
                }
            }

        }

    }// main
}
