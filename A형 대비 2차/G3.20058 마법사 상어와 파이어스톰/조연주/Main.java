import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,Q,width;
    static int[][] map;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};

    // 메인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken()); // 시전 횟수
        width = (int) Math.pow(2,N);

        // 얼음
        map = new int[width][width];
        for(int r=0; r<width; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<width; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 파이어스톰 : (1) ~ (3)
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<Q; i++){
            int L = Integer.parseInt(st.nextToken());
            divide(L);
            melt();
        }
        
        // 결과 계산 : 1 ~ 2
        System.out.println(countIce());
        System.out.println(countBigIce());

    }

    // (1) 각 영역의 시작점 정하기
    static void divide(int L){
        int lv = (int) Math.pow(2,L);
        for(int sr=0; sr<width; sr+=lv){
            for(int sc=0; sc<width; sc+=lv){
                lotate(sr, sc, lv);
            }
        }
    }

    // (2) 회전
    static void lotate(int sr, int sc, int lv){
        int[][] temp = new int[lv][lv]; // 임시 배열
        
        // x = lv -1 - y
        // y = x
        for(int r=0; r<lv; r++){
            for(int c=0; c<lv; c++){
                temp[c][(lv-1-r)] = map[sr+r][sc+c];
            }
        }

        // 원래 배열에 대입
        for(int r=0; r<lv; r++){
            for(int c=0; c<lv; c++){
                map[r+sr][c+sc] = temp[r][c];
            }
        }
    }

    // (3) 얼음 녹이기
    static void melt(){
        int[][] copyMap = new int[width][width]; // 임시 배열
        for(int r=0; r<width; r++){
            copyMap[r] = Arrays.copyOfRange(map[r],0,width);
        }

        for(int r=0; r<width; r++){
            for(int c=0; c<width; c++){
                if(map[r][c]==0) continue;
                int ice = 0;
                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr<0 || nc<0 || nr>=width || nc>=width) continue;

                    if(map[nr][nc]>0) ice++;
                }
                // 얼음이 3개보다 적을때
                if(ice<3){
                    copyMap[r][c]--;
                }
            }
        }
        
        // 원래 배열에 대입
        for(int r=0; r<width; r++){
            map[r] = Arrays.copyOfRange(copyMap[r],0,width);
        }
        
    }

    // 1. 남아있는 얼음의 합
    static int countIce(){
        int ice = 0;
        for(int r=0; r<width; r++){
            for(int c=0; c<width; c++){
                ice += map[r][c];
            }
        }
        return ice;
    }

    // 2. 가장 큰 덩어리가 차지하는 칸의 개수
    static boolean[][] visited;
    static int countBigIce(){
        visited = new boolean[width][width];
        int maxCnt = 0;

        for(int r=0; r<width; r++){
            for(int c=0; c<width; c++){
                if(!visited[r][c] && map[r][c]!=0) {
                    maxCnt = Math.max(maxCnt, BFS(r,c));
                }
            }
        }
        return maxCnt;
    }

    static int BFS(int r, int c){
        int cnt = 1;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(r,c));
        visited[r][c] = true;

        while(!q.isEmpty()){
            Node node = q.poll();

            for(int d=0; d<4; d++){
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                if(nc>=0 && nr>=0 && nc<width && nr<width && !visited[nr][nc] && map[nr][nc]>0){
                    visited[nr][nc] = true;
                    cnt++;
                    q.offer(new Node(nr,nc));
                }
            }
        }
        return cnt;
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
