// 12016kb 88ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,x,y,d,G;
    static int[][] map = new int[101][101];
    static List<Integer> dNum;
    static int[] dy = {0,-1,0,1};
    static int[] dx = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine()); // 드래곤 커브 개수

        // 드래곤 커브 : 시작 좌표, 시작 방향, 세대
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()); // 시작 방향
            G = Integer.parseInt(st.nextToken()); // 세대

            // 0세대 드래곤 커브 만들기
            map[y][x] = 1;
            y += dy[d];
            x += dx[d];
            map[y][x] = 1;

            // 1세대 이상인 경우
            if(G>0){
                dNum = new ArrayList<>();
                dNum.add((d+1)%4);
                make(1);
            }
        }

        // 네 꼭짓점이 모두 드래곤 커브인 경우 구하기
        int ans = 0;
        for(int r=0; r<100; r++){
            for(int c=0; c<100; c++){
                ans += square(r,c);
            }
        }
        System.out.println(ans);

    }

    // 드래곤 커브 만들기
    static void make(int g){
        if(g>G){
            return;
        }

        int maxIdx = dNum.size()-1;

        for(int i=maxIdx; i>=0; i--){
            d = dNum.get(i);
            x += dx[d];
            y += dy[d];

            map[y][x] = 1;
            dNum.add((d+1)%4);
        }

        make(g+1);

    }

    static int square(int r, int c){
        for(int i=0; i<2; i++){
            for(int k=0; k<2; k++){
                if(map[r+i][c+k]==0) return 0;
            }
        }
        return 1;
    }

}
