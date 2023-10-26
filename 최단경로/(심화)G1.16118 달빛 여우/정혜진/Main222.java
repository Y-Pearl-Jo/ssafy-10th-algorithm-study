package 연습;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int node;
    int time;
    int speed;

    public Node(int node, int time) {
        super();
        this.node = node;
        this.time = time;
    }

    public Node(int node, int time, int speed) {
        super();
        this.node = node;
        this.time = time;
        this.speed = speed;
    }

    @Override
    public int compareTo(Node o) {
        return this.time - o.time;
    }

}

public class Main {

    static ArrayList<Node>[] graph;
    static int[] fox;
    static int[][] wolf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        fox = new int[N + 1];
        wolf = new int[2][N + 1];
        int INF = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            fox[i] = INF;

            wolf[0][i] = INF;
            wolf[1][i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            // a번 그루터기와 b번 그루터기 사이에 길이가 d인 오솔길(양방향)
            graph[a].add(new Node(b, d));
            graph[b].add(new Node(a, d));
        }

        dijkFox();
        dijkWolf();

        int cnt = 0;
        for (int i = 2; i <= N; i++) {
//            System.out.println(wolf[i][0] + "\t" + wolf[i][1] + "\t" + fox[i]);
            if (fox[i] < Math.min(wolf[0][i], wolf[1][i])) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static void dijkWolf() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 1));
        wolf[0][1] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int cnode = current.node;
            int ctime = current.time;
            
            // 1 (feat. 재신)
            int flag;
            if(current.speed == 4)
            	flag = 1;
            else
            	flag = 0;
            
            if(wolf[flag][cnode] < ctime) {
                continue;
            }
            
            // 2 (feat. 담현)
//            if((current.speed == 1 && wolf[0][cnode] < ctime) || (current.speed == 4 && wolf[1][cnode] < ctime)) {
//				continue;
//			}
            
            for (Node next : graph[cnode]) {
                int nnode = next.node;
                int ntime = next.time;
                if (current.speed == 4) { // 느릴 때
                	int time =  wolf[1][cnode] + ntime * 4;
                    if (wolf[0][nnode] > time) {
                        wolf[0][nnode] = time;
                        pq.add(new Node(nnode, wolf[0][nnode], 1));
                    }
                } else { // 존빠열
                	int time =  wolf[0][cnode] + ntime;
                    if (wolf[1][nnode] > time) {
                        wolf[1][nnode] = time;
                        pq.add(new Node(nnode, wolf[1][nnode], 4));
                    }
                }

            }

        }

    }

    private static void dijkFox() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        fox[1] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if(fox[current.node] < current.time) continue;
            
            for (Node next : graph[current.node]) {

                if (fox[next.node] > fox[current.node] + next.time * 2) {
                    fox[next.node] = fox[current.node] + next.time * 2;    
                    pq.add(new Node(next.node, fox[next.node]));

                }

            }

        }

    }
}
