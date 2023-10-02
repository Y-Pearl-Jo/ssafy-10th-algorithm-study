import java.util.*;

public class Main{
    static int N;
    static int[][] house;
    static int[] dx = {1,1,0};
    static int[] dy = {0,1,1};
    static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 집 상태 입력
        N = sc.nextInt();
        house = new int[N][N];
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                house[i][k] = sc.nextInt();
            }
        }

        // 경우의 수 탐색
        int[] start = {1,0};
        dfs(start, 0);

        // 출력
        System.out.println(cnt);

    }

    public static void dfs(int[] start, int status){
        int x = start[0];
        int y = start[1];
        if(x==N-1 && y==N-1){
            cnt++;
            return;
        }
        if(status==0){
            s0(x,y);
        }
        else if(status==1){
            s1(x,y);
        }
        else{
            s2(x,y);
        }
    }

    // 가로
    public static void s0(int x, int y){
        for(int d=0; d<=1; d++){
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx>=0 && nx<N && ny>=0 && ny<N && house[ny][nx]!=1){
                if((d==1 && house[ny][nx-1]==1) || (d==1 && house[ny-1][nx]==1)){
                    continue;
                }
                int[] next = {nx,ny};
                dfs(next,d);
            }
        }
    }

    // 대각선
    public static void s1(int x, int y){
        for(int d=0; d<=2; d++){
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx>=0 && nx<N && ny>=0 && ny<N && house[ny][nx]!=1){
                if((d==1 && house[ny][nx-1]==1) || (d==1 && house[ny-1][nx]==1)){
                    continue;
                }
                int[] next = {nx,ny};
                dfs(next,d);
            }
        }
    }

    // 세로
    public static void s2(int x, int y){
        for(int d=1; d<=2; d++){
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(nx>=0 && nx<N && ny>=0 && ny<N && house[ny][nx]!=1){
                if((d==1 && house[ny][nx-1]==1) || (d==1 && house[ny-1][nx]==1)){
                    continue;
                }
                int[] next = {nx,ny};
                dfs(next,d);
            }
        }
    }
    
}
