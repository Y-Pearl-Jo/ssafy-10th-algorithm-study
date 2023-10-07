// 294948kb 548ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 1. 국경을 열 수 있는지 여부 체크
// 2. BFS : 국경을 연 노드/방문한 노드 체크 -> 국경을 연 노드에만 인구 이동
// 3. 방문하지 않은 노드 : 2번 -> 반복..

public class Main {
    static int N,L,R,ans;
    static int[][] map;
    static boolean isOpened = true;
    static boolean[][] visited;

    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 땅 크기
        L = Integer.parseInt(st.nextToken()); // L명 이상~
        R = Integer.parseInt(st.nextToken()); // ~R명 이하

        // 인구 입력받기
        map = new int[N][N];
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 계산
        while(isOpened){
            isOpened = false;
            Open();
            if(isOpened) ans++;
        }
        System.out.println(ans);

    }

    // 국경 열기
    static void Open(){
        visited = new boolean[N][N];
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(!visited[r][c]) BFS(r,c);
            }
        }
    }

    // 인구 이동 -> BFS로 인접한 칸만 이동
    static void BFS(int sr, int sc){
        // 변수 생성
        Queue<Node> q = new LinkedList<>();
        List<Node> li = new ArrayList<>();

        // 첫번째 값 넣기
        q.offer(new Node(sr,sc));
        li.add(new Node(sr,sc));
        int sum = map[sr][sc];
        visited[sr][sc] = true;

        // BFS
        while(!q.isEmpty()){
            Node node = q.poll();

            for(int d=0; d<4; d++){
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                if(nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc]) continue;

                int n = Math.abs(map[node.r][node.c]-map[nr][nc]);
                if(n>=L && n<=R){
                    isOpened = true;
                    sum += map[nr][nc];
                    q.offer(new Node(nr,nc));
                    li.add(new Node(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        // 인구 이동시키기
        int num = sum/li.size();
        for(int i=0; i<li.size(); i++){
            map[li.get(i).r][li.get(i).c] = num;
        }
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
