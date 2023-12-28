import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Vertex implements Comparable<Vertex> {
    int next;
    int value;

    public Vertex(int next, int value) {
        this.next = next;
        this.value = value;
    }

    // 비용 최소화를 위해 value를 기준으로 정렬
    @Override
    public int compareTo(Vertex v) {
        return this.value - v.value;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int V, E;
    static long cost = 0;
    static boolean[] linked;
    static List<Vertex>[] graph;

    static void solution() {
        Queue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(1, 0));

        while (!pq.isEmpty()) {
            Vertex now = pq.poll();

            // 연결 상태 여부 확인
            // 이미 연결 => 더 싼 방법으로 연결
            if (linked[now.next]) continue;

            // 새 연결 추가
            linked[now.next] = true;
            // 사용 비용 증가
            cost += now.value;

            for (Vertex v : graph[now.next]) {
                if (!linked[v.next]) {
                    pq.add(v);
                }
            }
        }
        System.out.print(cost);
    }

    // 입력 값 받는 메서드
    static void make() throws IOException {
        V = init();
        E = init();

        graph = new ArrayList[V + 1];
        linked = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int now = init(st);
            int next = init(st);
            int value = init(st);

            graph[now].add(new Vertex(next, value));
            graph[next].add(new Vertex(now, value));
        }
    }

    public static void main(String[] args) throws IOException {
        make();
        solution();
    }

    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int init(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
