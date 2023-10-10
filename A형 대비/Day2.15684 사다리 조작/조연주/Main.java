// 16936kb 1360ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int C,M, R;
    static int ans = 4;
    static int[][] map; // 연결된 위치 배열

    // main
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = input(st.nextToken()); // 세로선 개수 (가로 크기)
        M = input(st.nextToken()); // 그어져있는 가로선 개수
        R = input(st.nextToken()); // 가로선 개수 (세로 크기)

        // 배열
        map = new int[R][C];

        // 가로선 위치 입력받기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = input(st.nextToken())-1;
            int b = input(st.nextToken())-1;
            // 다음 위치 저장
            map[a][b] = 1;
            map[a][b+1] = -1;
        }

        // 계산
        DFS(0);

        // 정답 출력
        if(ans==4){
            System.out.println(-1);
        }
        else{
            System.out.println(ans);
        }
    }
    // ------------------------------

    static void DFS(int cnt){
        if(cnt>=ans) return;

        if(isPossible()){
            ans = Math.min(ans,cnt);
            return;
        }

        if(cnt==3) return;

        for(int r=0; r<R; r++){
            for(int c=0; c<C-1; c++){
                if(map[r][c]==0 && map[r][c+1]==0){
                    map[r][c] = 1;
                    map[r][c+1] = -1;
                    DFS(cnt+1);
                    map[r][c] = 0;
                    map[r][c+1] = 0;
                }
            }
        }

    }

    static boolean isPossible(){
        for(int i=0; i<C; i++){
            Node node = new Node(0,i);
            // 사다리타기
            while(node.r<R){
                if(map[node.r][node.c]==0){
                    node.r++;
                }
                else{
                    node.c += map[node.r][node.c];
                    node.r++;
                }
            }
            if(node.c!=i){
                return false;
            }
        }
        return true;
    }

    static int input(String s){
        return Integer.parseInt(s);
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
