// 11596kb 80ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static boolean isPossible = true;
    static int N,ans;
    static int[][] dist, copy;

    public static void main(String[] args) throws IOException {
        N = input();

        // 결과 배열
        dist = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<N; k++){
                dist[i][k] = input(st);
            }
        }

        floyd();

        if(isPossible){
            count();
        }
        else{
            System.out.println(-1);
        }
    }

    static void floyd(){
        // 복사 배열
        copy = new int[N][N];
        for(int i=0; i<N; i++){
            copy[i] = dist[i].clone();
        }

        //
        for(int m=0; m<N; m++){
            for(int s=0; s<N; s++){
                for(int e=0; e<N; e++){
                    if(!index(s,m,e)) continue;

                    if(dist[s][e] == dist[s][m] + dist[m][e]){
                        copy[s][e] = 0;
                    }

                    else if(dist[s][e] > dist[s][m] + dist[m][e]){
                        isPossible = false;
                        return;
                    }
                }
            }
        }
    }

    static void count(){
        for(int i=0; i<N; i++){
            for(int k=i; k<N; k++){
                ans += copy[i][k];
            }
        }
        System.out.println(ans);
    }

    static boolean index(int s, int m, int e){
        return s!=m && m!=e && e!=s;
    }

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
