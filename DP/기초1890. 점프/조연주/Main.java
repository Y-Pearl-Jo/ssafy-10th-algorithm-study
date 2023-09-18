// 13324kb 88ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr; // 게임판 배열
    static long[][] route; // 방문시 누적 경로 개수 저장하는 배열 -> dp배열 long으로 만들기!!

    // main
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        route = new long[N][N];

        // 게임판 입력받기
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<N; k++){
                arr[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        // 정답 출력
        System.out.println(DP(0, 0));

    }

    // DP
    static long DP(int y, int x){
        if(route[y][x]>0){
            return route[y][x];
        }

        // 인덱스 = 도착점 -> 값 = 1 저장 / 리턴
        if(y==N-1 && x==N-1){
            route[y][x] = 1;
            return route[y][x];
        }

        long sum = 0;

        // 값 = 가로 ~ 세로 값의 합
        // 거리 == 값 -> 재귀
        for(int i=1; i<N-x; i++){ // 오른쪽(x)
            if(arr[y][x]==i){
                sum += DP(y,x+i);
            }
        }

        for(int i=1; i<N-y; i++){ // 아래(y)
            if(arr[y][x]==i){
                sum += DP(y+i,x);
            }
        }

        route[y][x] = sum;

        return route[y][x];
    }

}
