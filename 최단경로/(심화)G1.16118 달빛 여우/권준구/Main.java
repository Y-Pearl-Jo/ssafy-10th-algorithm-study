import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class baek_16118_달빛여우 {

    /*
     * 
     * 1. 늑대는 가중치를 *0.5, *2를 계속 곱하여 더하기 때문에 처음에 여우와 늑대의 거리를 받을때*2하여 짝수로 받는다. 2.
     * 달빛여우는 노드 1에서 시작하는 정석 다익스트라 3. 달빛늑대는 홀수번째에 0.5의 가중치, 짝수번째에 *2의 가중치로 이동한다. 4.
     * 그렇다면 짝수번째에 가중치가 큰 노드를 방문할 때에는 최단거리가 아닐 수 있다. 4-1. 예시 1)1 3 500, 1 2 1 4-2. 예시
     * 2) 1 2 1 2 3 2 3 1 3 1 4 4 4 5 500 5. 홀수번째 방문할때(홀수개의 사이클)와 짝수번째 방문할 때 거리를 따로
     * 저장하자.
     * 
     * 
     */

    // 닻빛 여우와 달빛 늑대는 1번 나무 그루터기에서 살고 있다.
    // 달빛 여우는 늘 일정한 속도로 달려가는 반면
    // 달빛 늑대는 달빛 여우보다 더 빠르게 달릴 수 있지만 , 체력이 부족해 전략을 사용함
    // 달빛 늑대는 출발할 때 오솔길 하나를 달빛 여우의 두 배의 속도로 달려가고
    // 그 뒤로는 오솔길 하나를 달빛 여우의 절반의 속도로 걸어가며 체력을 회복하고 나서 다음 오솔길을 다시 달빛 여우의 두 배의 속도로 달려가는
    // 것을 반복한다.
    // 달빛 여우와 달빛 늑대는 각자 가장 빠르게 달빛이 비치는 그루터기까지 다다를 수 있는 경로로 이동한다. 따라서 둘의 이동 경로가 서로 다를
    // 수도 있다.
    // 빛 여우가 달빛 늑대보다 먼저 도착할 수 있는 그루터기에 달빛을 비춰 주려고 한다. 이런 그루터기가 몇 개나 있는지 알아보자.

    static class Node {
        int v, weight, s;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public Node(int v, int weight, int s) {
            this.v = v;
            this.weight = weight;
            this.s = s;
        }
    }

    static final int INF = 200000000;
    static int[] foxDist;
    static int[][] wolfDist;
    static boolean[] wolfSpeed;
    static int N, M;
    static int[][] map;
    static List<List<Node>> graph;
    static boolean speed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        foxDist = new int[N + 1];
        wolfDist = new int[N + 1][2];
        Arrays.fill(foxDist, INF);
        Arrays.fill(wolfDist, INF);
        wolfSpeed = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        fox다익(1);
        wolf다익(1);

    }

    private static void wolf다익(int start) {
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.add(new Node(1, 0));
        wolfDist[start][0] = 0; // 0이 홀수일때
        

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visit[now.v]) {
                visit[now.v] = true;

                for (int i = 0; i < graph.get(now.v).size(); i++) {
                    Node next = graph.get(now.v).get(i);

                    if (wolfDist[next.v] > wolfDist[next.v] + next.weight) {
                        wolfDist[next.v] = wolfDist[next.v] + next.weight;
                        pq.add(new Node(next.v, wolfDist[next.v]));
                    }
                }

            }
        }
    }

    private static void fox다익(int start) {
        boolean[] visit = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {

            @Override
            public int compare(Node o1, Node o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.add(new Node(1, 0));
        foxDist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visit[now.v]) {
                visit[now.v] = true;

                for (int i = 0; i < graph.get(now.v).size(); i++) {
                    Node next = graph.get(now.v).get(i);

                    if (foxDist[next.v] > foxDist[next.v] + next.weight) {
                        foxDist[next.v] = foxDist[next.v] + next.weight;
                        pq.add(new Node(next.v, foxDist[next.v]));
                    }
                }

            }
        }
    }

    public static void speed(int start) {
        speed = !speed;
        if (speed) {
            wolfSpeed[start] = speed;
        }
    }

}
