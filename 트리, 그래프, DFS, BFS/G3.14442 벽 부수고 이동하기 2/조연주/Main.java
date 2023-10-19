import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K,ans;
    static int[][] arr;
    static boolean[][][] visited;

    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = input(st.nextToken());
        M = input(st.nextToken());
        K = input(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M][K+1];

        // 입력 받기
        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int k=0; k<M; k++){
                arr[i][k] = s.charAt(k)-'0';
            }
        }

        // 계산
        BFS();

        if(ans==0) System.out.println(-1);
        else System.out.println(ans);

    }

    static void BFS(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,1,0));
        visited[0][0][0] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            if(node.r == N-1 && node.c == M-1){
                ans = node.dist;
                return;
            }

            for(int d=0; d<4; d++){
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];

                if(nr<0 || nc<0 || nr>=N || nc>=M) continue;

                if(arr[nr][nc]==0 && !visited[nr][nc][node.wall]){
                    visited[nr][nc][node.wall] = true;
                    q.add(new Node(nr,nc,node.dist+1,node.wall));
                }
                else{
                    if(node.wall<K && !visited[nr][nc][node.wall+1]){
                        visited[nr][nc][node.wall+1] = true;
                        q.add(new Node(nr,nc,node.dist+1,node.wall+1));
                    }
                }

            }

        }

    }

    static int input(String s){
        return Integer.parseInt(s);
    }
}

class Node{
    int r;
    int c;
    int dist;
    int wall;
    Node(int r, int c, int dist, int wall){
        this.r = r;
        this.c = c;
        this.dist = dist;
        this.wall = wall;
    }
}
