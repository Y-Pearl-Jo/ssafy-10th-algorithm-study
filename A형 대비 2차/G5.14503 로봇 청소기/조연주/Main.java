// 11852kb 84ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Robo{
    int x;
    int y;
    int dir;
    public Robo(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class Main {
    // 전역 변수
    static Robo robo;
    static int N,M;
    static int[][] room;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};

    // 메인 함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 방의 세로
        M = Integer.parseInt(st.nextToken()); // 방의 가로
        room = new int[N][M];

        // 로봇 청소기의 상태 입력받기 -> r c 헷갈려
        st = new StringTokenizer(br.readLine());
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        robo = new Robo(X,Y,dir);

        // 방의 상태 입력받기
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<M; x++){
                room[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 청소하기
        clean();

        // 청소된 칸의 개수 세기
        int ans = 0;
        for(int[] arr : room){
            for(int a : arr){
                if(a==-1){
                    ans++;
                }
            }
        }

        // 정답 출력
        System.out.println(ans);

    }

    static void clean(){
        while(true){
            // 1.현재 칸이 0인 경우
            if(room[robo.y][robo.x]==0){
                room[robo.y][robo.x] = -1; // 청소
            }

            // 2.동서남북에 0이 없는 경우
            else if(!dirty(robo.x, robo.y)){
                // 1) 방향을 유지한 채로 후진 가능
                int nx = robo.x+dx[(robo.dir+2)%4];
                int ny = robo.y+dy[(robo.dir+2)%4];
                if(room[ny][nx]!=1){
                    robo.x = nx;
                    robo.y = ny;
                }
                // 2) 뒤쪽 칸이 벽인 경우
                else{
                    return;
                }
            }

            // 3.동서남북에 0이 있는 경우
            else {
                // 1) 반시계 회전
                robo.dir = (robo.dir+3)%4;

                // 2) 청소되지 않은 빈칸인 경우 한칸 전진
                int nx = robo.x+dx[robo.dir];
                int ny = robo.y+dy[robo.dir];
                if(room[ny][nx]==0){
                    robo.x = nx;
                    robo.y = ny;
                }
            }
        }
    }

    // 사방탐색
    static boolean dirty(int x, int y){
        for(int d=0; d<4; d++){
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(room[ny][nx]==0){
                return true;
            }
        }
        return false;
    }

}
