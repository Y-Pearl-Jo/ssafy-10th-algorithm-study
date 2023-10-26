// 66948kb 860ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int v, status;
    int cost;
    public Node(int v, int cost, int status){
        this.v = v;
        this.cost = cost;
        this.status = status;
    }
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M;
    static int INF = 1234567890;
    static int[] distFox;
    static int[][] distWolf;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st); // 정점(그루터기)
        M = input(st); // 간선(오솔길)

        // 거리 배열 초기화
        distFox = new int[N+1];
        Arrays.fill(distFox, INF);
        distFox[1] = 0;
        
        distWolf = new int[2][N+1];
        for(int i=0; i<2; i++) {
        	Arrays.fill(distWolf[i], INF);
        }
        distWolf[0][1] = 0;
        
        // 그래프 초기화
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        // 간선 정보
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = input(st);
            int b = input(st);
            int d = 2*input(st);

            graph.get(a).add(new Node(b,d,0));
            graph.get(b).add(new Node(a,d,0));
        }

        dijstraFox();
        dijstraWolf();

        int ans = 0;
        for(int i=1; i<N+1; i++){
            if(Math.min(distWolf[0][i],distWolf[1][i]) > distFox[i]){
                ans++;
            }
        }
        System.out.println(ans);

    }

    // 다익스트라
    static void dijstraFox(){
    	
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(distFox[cur.v] < cur.cost) continue;

            for(int i=0; i<graph.get(cur.v).size(); i++){
                Node next = graph.get(cur.v).get(i);
                int time = distFox[cur.v] + next.cost;
                
                if(distFox[next.v] > time) {
                	distFox[next.v] = time;
                    pq.offer(new Node(next.v, time, 0));
                }
            }
        }
    }

    static void dijstraWolf(){
    	// 0 : 걸어서 도착(이제 뛸 수 있음)
    	// 1 : 뛰어서 도착(이제 쉬어야함)

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1,0,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(distWolf[cur.status][cur.v] < cur.cost) continue;

            for(int i=0; i<graph.get(cur.v).size(); i++){
                Node next = graph.get(cur.v).get(i);

                // 뛸 수 있음
                if(cur.status==0){
                	int time = distWolf[0][cur.v] + (next.cost/2);                  
                    
                    if(distWolf[1][next.v] > time){
                    	distWolf[1][next.v] = time;
                        pq.offer(new Node(next.v, time, 1));
                    }
                }
                // 휴식 필요
                else{
                	int time = distWolf[1][cur.v] + (2*next.cost);
                    
                    if(distWolf[0][next.v] > time){
                    	distWolf[0][next.v] = time;
                        pq.offer(new Node(next.v, time, 0));
                    }
                }
            }
        }      
    }

    // ----------
    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
