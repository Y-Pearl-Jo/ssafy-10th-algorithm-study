// 48920kb 340ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 전역 변수
    static int R,C,T;
    static int[][] room;
    static ArrayList<Integer> ac;
    static ArrayList<Integer> ar;
    static int[] dc1 = {0,1,0,-1};
    static int[] dr1 = {-1,0,1,0};
    static int[] dc2 = {0,1,0,-1};
    static int[] dr2 = {1,0,-1,0};

    // 메인 함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 행(y)
        C = Integer.parseInt(st.nextToken()); // 열(x)
        T = Integer.parseInt(st.nextToken()); // 시간

        // 방 정보 입력받기
        room = new int[R][C];
        ac = new ArrayList<>();
        ar = new ArrayList<>();
        
        for(int r=0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<C; c++){
                room[r][c] = Integer.parseInt(st.nextToken());
                if(room[r][c]==-1){
                    ac.add(c);
                    ar.add(r);
                }
            }
        }

        // T초
        while(T-- > 0){
            second();
        }
        
        // 남은 미세먼지 양 계산
        int ans = 0;
        for(int[] arr : room){
            for(int a : arr){
                if(a==-1) continue;
                ans += a;
            }
        }
        System.out.println(ans);
    }

    // 1초
    static void second(){
        // 배열 복사
        int[][] roomCopy = new int[R][C];
        for(int r=0; r<R; r++){
            roomCopy[r] = Arrays.copyOfRange(room[r],0,C);
        }

        // 1. 미세먼지 확산
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                int dust = room[r][c];
                if(dust>0){
                    for(int d=0; d<4; d++){
                        int nr = r+dr1[d];
                        int nc = c+dc1[d];
                        if(nr>=0 && nr<R && nc>=0 && nc<C && room[nr][nc]!=-1){
                            roomCopy[nr][nc] += dust/5;
                            roomCopy[r][c] -= dust/5;
                        }
                    }
                }
            }
        }

        // 2. 공기청정기 작동
        // (1) 위쪽
        int r = ar.get(0)-1;
        int c = ac.get(0);
        int d = 0;
        while(d<4){
            int nr = r+dr1[d];
            int nc = c+dc1[d];
            // 범위 밖인 경우
            if(nr<0 || nr>ar.get(0) || nc<0 || nc>=C){
                d++;
                continue;
            }
            // 다음 위치가 공기청정기일때
            if(roomCopy[nr][nc]==-1){
                roomCopy[r][c] = 0;
            }
            // 아닐 때
            else{
                roomCopy[r][c] = roomCopy[nr][nc];
            }
            r = nr;
            c = nc;

        }


        // (2) 아래쪽
        r = ar.get(1)+1;
        c = ac.get(1);
        d = 0;
        while(d<4){
            int nr = r+dr2[d];
            int nc = c+dc2[d];
            if(nr<ar.get(1) || nr>=R || nc<0 || nc>=C){
                d++;
                continue;
            }
            // 다음 위치가 공기청정기일때
            if(roomCopy[nr][nc]==-1){
                roomCopy[r][c] = 0;
            }
            // 아닐 때
            else{
                roomCopy[r][c] = roomCopy[nr][nc];
            }
            r = nr;
            c = nc;

        }

        for(int i=0; i<R; i++){
            room[i] = Arrays.copyOfRange(roomCopy[i],0,C);
        }
    }
}
