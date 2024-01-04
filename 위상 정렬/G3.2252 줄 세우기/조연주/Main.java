// 메모리: 45076 KB, 시간: 444 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수(정점 수)
        int M = Integer.parseInt(st.nextToken()); // 간선 수

        // 그래프
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        // 진입차수 테이블
        int[] degree = new int[N+1];

        // 간선 정보 입력받기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            degree[B]++;
        }

        // 위상정렬
        Queue<Integer> q = new LinkedList<>();

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
