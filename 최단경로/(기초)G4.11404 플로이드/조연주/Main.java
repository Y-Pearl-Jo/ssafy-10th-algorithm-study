// 43884kb 424ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,M;
    static int[][] dist;
    static int INF = 1000000000;

    // main
    public static void main(String[] args) throws IOException {
        N = input(); // 도시 개수 (정점)
        M = input(); // 버스 개수 (간선)

        // 거리 테이블 초기화
        dist = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            for(int k=1; k<=N; k++){
                dist[i][k] = (i==k) ? 0 : INF;
            }
        }

        // 간선 정보 입력받기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = input(st); // 시작 도시
            int b = input(st); // 도착 도시
            int c = input(st); // 비용

            dist[a][b] = Math.min(dist[a][b],c); // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
        }

        floydWarshall();
        print();
    }

    // 플로이드-워셜
    static void floydWarshall(){
        // 노드 k -> 노드 j 로 가는 최소 비용 계산
        // 노드 i : 경유하는 노드
        for(int i=1; i<=N; i++){
            for(int k=1; k<=N; k++){
                for(int j=1; j<=N; j++){
                    // 노드 k -> 노드 j  vs  노드 k -> 노드 i + 노드 i -> 노드 j
                    dist[k][j] = Math.min(dist[k][j], dist[k][i]+dist[i][j]);
                }
            }
        }
    }

    // 결과 출력
    static void print(){
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++){
            for(int k=1; k<=N; k++){
                if(dist[i][k]==INF){
                    sb.append(0).append(" ");
                }
                else{
                    sb.append(dist[i][k]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    // ---------------------------------------
    static int input() throws IOException{
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }

}
