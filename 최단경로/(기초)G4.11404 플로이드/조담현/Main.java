import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] city;
    //INF는 간선 가중치의 최댓값 * 정점 개수 -1 보다 큰 값을 사용하면 된다.
    static final int INF = 10000000;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        city = new int[N+1][N+1];
        
        for(int i=1; i<N+1; i++) {
        	for(int j=1; j<N+1; j++) {
        		if(i==j) city[i][j] = 0;
        		else city[i][j] = INF;
        	}
        }
        
        for(int i=0; i<M; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	city[a][b] = Math.min(city[a][b], c);
        }
        
        for(int k=1; k<N+1; k++) {
        	for(int i=1; i<N+1; i++) {
        		for(int j=1; j<N+1; j++) {
        			city[i][j] = Math.min(city[i][j], city[i][k]+city[k][j]);
        		}
        	}
        }
        
        for(int i=1; i<N+1; i++) {
        	for(int j=1; j<N+1; j++) {
        		if(city[i][j]==INF) city[i][j] = 0;
        		sb.append(city[i][j]).append(' ');
        	}
        	sb.append('\n');
        }
        
        System.out.println(sb.toString());
    }
}
