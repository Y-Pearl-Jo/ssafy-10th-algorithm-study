// 72472kb 812ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node{
    int idx; // 다음 노드의 인덱스
    int cost; // 그 노드로 가는데 필요한 비용

    Node(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }
}

public class Main {
    static int INF = 200000000;
    static int V;
    static ArrayList<ArrayList<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        // 인접리스트 -> 그래프 초기화
        graph = new ArrayList<ArrayList<Node>>();

        // 노드 번호: 1~V까지
        for(int i=0; i<=V; i++){
            graph.add(new ArrayList<Node>());
        }

        // 간선 정보 입력받기 -> 그래프에 저장
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            // 양방향 간선
            graph.get(v1).add(new Node(v2, cost));
            graph.get(v2).add(new Node(v1, cost));
        }

        // 반드시 거쳐야 하는 정점 2개
        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());

        // 1 - V1 - V2 - N
        int res1 = 0;
        res1 += dijstra(1,V1);
        res1 += dijstra(V1,V2);
        res1 += dijstra(V2,V);


        // 1 - V2 - V1 - N
        int res2 = 0;
        res2 += dijstra(1,V2);
        res2 += dijstra(V2,V1);
        res2 += dijstra(V1,V);

        // 최종 비용 출력
        if(res1>=INF && res2>=INF){
            System.out.println(-1);
        }
        else{
            System.out.println(Math.min(res1, res2));
        }
    }

    // 다익스트라 알고리즘
    static int dijstra(int start, int end){

        // 방문 여부 확인 배열
        boolean[] visited = new boolean[V+1];

        // 거리 정보 배열
        int[] dist = new int[V+1];
        for(int i=0; i<=V; i++){
            dist[i] = INF;
        }
        dist[start] = 0; // 출발 지점 비용 = 0

        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));

        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node curNode = pq.poll();

            if(visited[curNode.idx]) continue;
            visited[curNode.idx] = true;

            for(int i=0; i<graph.get(curNode.idx).size(); i++){
                Node nextNode = graph.get(curNode.idx).get(i);
                if(dist[nextNode.idx]>dist[curNode.idx]+nextNode.cost){
                    dist[nextNode.idx] = dist[curNode.idx]+nextNode.cost;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }

        }

        return dist[end];
    }

}
