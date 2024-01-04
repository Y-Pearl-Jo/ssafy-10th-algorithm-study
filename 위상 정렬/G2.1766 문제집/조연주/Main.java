// 메모리: 46940 KB, 시간: 464 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 작업 수(노드 수)
        int M = Integer.parseInt(st.nextToken()); // 간선 수

        // 그래프
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        // 변수
        int[] degree = new int[N+1];

        // 간선 입력받기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            degree[B]++;
        }

        // 위상정렬
        PriorityQueue<Integer> q = new PriorityQueue<>();

        // 진입차수가 0인 값 큐에 넣기
        for(int i=1; i<degree.length; i++){
            if(degree[i]==0){
                q.offer(i);
            }
        }

        // 큐가 빌때까지
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            int node = q.poll();
            sb.append(node).append(" ");

            // 꺼낸 노드의 인접 노드 찾기
            for(int i=0; i<graph.get(node).size(); i++){
                degree[graph.get(node).get(i)]--;
                if(degree[graph.get(node).get(i)]==0){
                    q.offer(graph.get(node).get(i));
                }
            }
        }

        // 정답 출력
        System.out.println(sb);

    }
}
