// 74204kb 560ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1. 활성화시킬 바이러스 고르기 DFS
// 2. 바이러스 퍼뜨리기 BFS
// 3. 시간을 min값에 저장

public class Main {
    static int minTime = Integer.MAX_VALUE;
    static int N,M;
    static int[][] map;
    static List<Node> virus = new ArrayList<>();
    static boolean[] picked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 연구소 크기
        M = Integer.parseInt(st.nextToken()); // 바이러스 개수

        // 연구소 상태 입력받기
        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<N; k++){
                map[i][k] = Integer.parseInt(st.nextToken());
                if(map[i][k]==2){
                    virus.add(new Node(i,k));
                }
            }
        }

        // 계산
        picked = new boolean[virus.size()];
        pick(0,0);

        // 출력
        if(minTime == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(minTime);
        }

    }

    // 활성화할 바이러스 고르기
    static void pick(int cnt, int idx){
        if(cnt==M){
            spread();
        }

        for(int i=idx; i<virus.size(); i++){
            if(!picked[i]){
                picked[i] = true;
                pick(cnt+1,i);
                picked[i] = false;
            }
        }
    }

    // 바이러스 퍼뜨리기
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static void spread(){
        // 배열 복사하기
        int[][] copyMap = new int[N][N];
        for(int i=0; i<N; i++){
            copyMap[i] = Arrays.copyOfRange(map[i],0,N);
        }

        // 선택한 바이러스 넣기
        Queue<Node> q = new LinkedList<>();
        for(int i=0; i<virus.size(); i++){
            if(picked[i]){
                q.offer(new Node(virus.get(i).r, virus.get(i).c));
            }
        }

        // BFS
        int time = -1;
        while(!q.isEmpty()){
            time++;

            if(isDone(copyMap)){
                break;
            }

            // 1초동안 사용할 좌표 리스트
            List<Node> nodeList = new ArrayList<>();
            while(!q.isEmpty()){
                nodeList.add(q.poll());
            }

            // 활성 바이러스 = 3
            for(Node node : nodeList){
                copyMap[node.r][node.c] = 3;
            }

            for (Node node : nodeList) {
                for (int d = 0; d < 4; d++) {
                    int nr = node.r + dr[d];
                    int nc = node.c + dc[d];

                    if (nr < 0 || nc < 0 || nr >= N || nc >= N || copyMap[nr][nc] == 1 || copyMap[nr][nc]==3) continue;

                    copyMap[nr][nc] = 1;
                    q.offer(new Node(nr, nc));
                }
            }
        }

        // 바이러스를 전부 퍼뜨렸다면(0이 없다면) -> minTime값 갱신
        if(isDone(copyMap)){
            minTime = Math.min(minTime, time);
        }
    }

    // 바이러스 전부 퍼뜨렸는지 검사
    static boolean isDone(int[][] copyMap){
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                if(copyMap[i][k]==0) return false;
            }
        }
        return true;
    }

}

class Node{
    int r;
    int c;
    public Node(int r, int c){
        this.r = r;
        this.c = c;
    }
}
