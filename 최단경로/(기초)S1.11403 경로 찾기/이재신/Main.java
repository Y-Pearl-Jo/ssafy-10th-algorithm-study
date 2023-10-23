import java.io.*;
import java.util.*;
// 메모리 13600 KB, 시간 140 ms
public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static int[][] graph;
    
    static void solution() {
        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < N + 1; j++){
                for(int k = 1; k < N + 1; k++){
                    if(graph[j][i] == 1 && graph[i][k] == 1) {
                        graph[j][k] = 1;
                    }
                }
            }
        }
    }
    
    static void make() throws IOException {
        N = init();
        graph = new int[N + 1][N + 1];
        
        for(int i = 1 ; i < N + 1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j < N + 1 ; j++){
                graph[i][j] = init(st);
            }            
        }
    }
    
    static void print() {
        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < N + 1 ; j++){
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    
    public static void main(String[] args) throws IOException {
        make();
        solution();
        print();
    }
    
    static int init() throws IOException {
        return Integer.parseInt(br.readLine());
    }
    
    static int init(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
