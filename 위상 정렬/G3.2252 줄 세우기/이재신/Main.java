import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V, E;
    static boolean[] visited;
    static boolean[] finish;
    static boolean cycle;
    static Stack<Integer> result;
    static ArrayList<Integer>[] graph;

    static void DFS(int Vertex) {
        visited[Vertex] = true;

        for (int i = 0; i < graph[Vertex].size(); ++i) {
            int next = graph[Vertex].get(i);
            if (!visited[next])
                DFS(next);
            else if (!finish[next]) {
                cycle = true;
                return;
            }
        }
        finish[Vertex] = true;
        result.push(Vertex);
    }

    static void TopologicalSort() {
        for (int i = 1; i < V + 1; ++i) {
            if (cycle) {
                System.out.println("그래프에 사이클이 존재합니다.");
                return;
            }
            if (!visited[i])
                DFS(i);
        }
    }

    static void make() throws IOException {
        st = new StringTokenizer(br.readLine());
        // 정점의 개수
        V = init(st);
        // 간선의 개수
        E = init(st);
        // 그래프 생성
        graph = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[V + 1];
        finish = new boolean[V + 1];
        result = new Stack<>();
        // 간선 추가
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = init(st);
            int to = init(st);
            graph[from].add(to);
        }
    }

    static void solution() {
        TopologicalSort();
        while (!result.isEmpty())
            System.out.print(result.pop() + " ");
    }

    public static void main(String[] args) throws IOException {
        make();
        solution();
    }

    static int init() throws IOException{
        return Integer.parseInt(br.readLine());
    }
    
    static int init(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
