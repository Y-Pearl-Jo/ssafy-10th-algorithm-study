// 13068kb 92ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge{
    int s,e,cost;
    public Edge(int s, int e, int cost){
        this.s = s;
        this.e = e;
        this.cost = cost;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,start,end,M;
    static int INF = -1234567890;
    static List<Edge> graph = new ArrayList<>();
    static long[] dist; // 최댓값 저장 배열
    static int[] money; // 각 도시에서 벌 수 있는 돈
    static boolean[][] con; // BFS에서 사용할 연결 여부 배열

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st);
        start = input(st);
        end = input(st);
        M = input(st);

        // 교통 수단 정보 (간선)
        con = new boolean[N][N];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = input(st);
            int e = input(st);
            int cost = -1 * input(st);

            graph.add(new Edge(s,e,cost));

            con[s][e] = true;
        }

        // 각 도시에서 벌 수 있는 돈
        money = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            money[i] = input(st);
        }

        // 배열 초기화
        dist = new long[N];
        Arrays.fill(dist,INF);
        dist[start] = money[start];

        // 출력
        System.out.println(bf() ? dist[end] : "Gee");
    }
    
    // 벨만 포드
    static boolean bf(){
        // 정점 개수-1만큼 반복
        for(int i=0; i<N-1; i++){
            // 간선 개수만큼 반복
            for(int k=0; k<M; k++){
                Edge edge = graph.get(k);
                if(dist[edge.s]!=INF){
                    // 도착지에서 버는 돈 = 출발지에서 번 돈 + 이동 비용 + 도착지에서 벌 수 있는 돈
                    if(dist[edge.e] < dist[edge.s] + edge.cost + money[edge.e]){
                        dist[edge.e] = dist[edge.s] + edge.cost + money[edge.e];
                    }
                }
            }
        }

        // end에 도착할 수 없는 경우
        if(dist[end]==INF){
            System.out.println("gg");
            System.exit(0);
        }

        // end에 도착할 수 있다면
        // 양수 사이클(?) 확인
        for(int k=0; k<M; k++){
            Edge edge = graph.get(k);

            if(dist[edge.s]!=INF){
                if(dist[edge.e] < dist[edge.s] + edge.cost + money[edge.e]){
                    // 양수 사이클에서 출발해서, end까지 갈 수 있을때만
                    if(BFS(edge.e)) return false;
                }
            }
        }
        return true;
    }

    // 양수 사이클 -> 도착지 연결 여부 확인
    static boolean BFS(int start){
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int num = q.poll();

            if(num==end) return true;

            for(int i=0; i<N; i++){
                if(con[num][i] && !visited[i]){
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
        return false;
    }

    // ----------
    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
