import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 가중치가 작은 순서부터 정렬
class Node implements Comparable<Node> {
    int v;
    int w;

    public Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}


class Main {
    static int sum;                             // 합을 구할 sum
    static ArrayList<ArrayList<Node>> graph;    // 간선 정보를 저장할 그래프
    static boolean[] visit;                     // 방문 확인할 visit

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());   // 정점의 갯수
        int E = Integer.parseInt(st.nextToken());   // 간선의 갯수

        graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {           // 인덱스 혼동을 피하고자 V+1
            graph.add(new ArrayList<>());
        }

        visit = new boolean[V + 1];                 // 위와 마찬가지

        // A가 B와 연결되어 있다면 B와 A도 연결되어 있음 -> 양쪽으로 추가
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }

        // 임의의 정점에서 시작
        prim(new Node(1, 0));
        System.out.println(sum);

    }

    static void prim(Node start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();     // 우선순위 큐 사용 -> 가중치순 정렬
        pq.add(start);
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visit[now.v]) {         // 방문했으면 (= 만약 정점이 저장되어있으면) 넘어가라
                continue;
            }
            visit[now.v] = true;        // 방문처리(= 정점 저장)
            sum += now.w;               // 가중치 저장
            for (Node next : graph.get(now.v)) {
                if (!visit[next.v]) {
                    pq.add(next);
                }
            }
        }
    }
}
