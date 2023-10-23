// 14088kb 132ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[][] con;

    public static void main(String[] args) throws IOException {
        N = input();
        con = new int[N][N];

        // 연결 배열
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<N; k++){
                con[i][k] = input(st);
            }
        }

        floyd();
        print();

    }

    // 플로이드-워셜
    static void floyd(){
        for(int m=0; m<N; m++){
            for(int s=0; s<N; s++){
                for(int e=0; e<N; e++){
                    // 이미 연결되어 있는 경우
                    if(con[s][e]==1) continue;
                    // m을 통해 연결되는 경우
                    if(con[s][m]==1 && con[m][e]==1){
                        con[s][e] = 1;
                    }
                }
            }
        }
    }

    // 출력
    static void print(){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                sb.append(con[i][k]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // -------------------------
    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }

}
