// 296012kb 884ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M,ans;
    static int[][] arr;
    static Integer[][] virus;
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        arr = new int[N][M];
        virus = new Integer[10][2];

        // 지도에 정보 입력받기
        int idx = 0;
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                arr[y][x] = Integer.parseInt(st.nextToken());
                if(arr[y][x]==2){
                    virus[idx][0] = y;
                    virus[idx++][1] = x;
                }
            }
        }

        DFS(0);

        System.out.println(ans);

    }

    // DFS : 벽 세우기
    static void DFS(int wallCnt){
        if(wallCnt==3){
            BFS();
            return;
        }

        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                if(arr[y][x]==0){
                    arr[y][x] = 1;
                    DFS(wallCnt+1);
                    arr[y][x] = 0;
                }
            }
        }

    }

    // BFS : 바이러스 퍼뜨리기
    static void BFS(){
        // 배열 복사하기
        int[][] arrCopy = new int[N][M];
        for(int i=0; i<N; i++){
            arrCopy[i] = Arrays.copyOfRange(arr[i],0,M);
        }
        
        // BFS
        Queue<Integer[]> q = new LinkedList<>();
        for(int i=0; i<10; i++){
            if(virus[i][0]!=null){
                q.offer(virus[i]);
                while(!q.isEmpty()){
                    Integer[] v = q.poll();
                    
                    for(int d=0; d<4; d++){
                        int ny = v[0]+dy[d];
                        int nx = v[1]+dx[d];
                        
                        if(ny>=0 && ny<N && nx>=0 && nx<M && arrCopy[ny][nx]==0){
                            arrCopy[ny][nx] = 1;
                            q.offer(new Integer[]{ny,nx});
                        }
                    }
                }
            }
        }
        
        // 정답 카운트
        int cnt = 0;
        for(int y=0; y<N; y++){
            for(int x=0; x<M; x++){
                if(arrCopy[y][x]==0){
                    cnt++;
                }
            }
        }

        ans = Math.max(ans, cnt);

    }


}
