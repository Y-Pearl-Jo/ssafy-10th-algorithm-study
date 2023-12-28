// 15852 KB, 136 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 도시 수

        parent = new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }

        int M = Integer.parseInt(br.readLine()); // 여행 계획

        // 도시 연결정보
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=1; k<=N; k++){
                if(Integer.parseInt(st.nextToken())==1){
                    union(i,k);
                }
            }
        }
        
        // 여행 계획
        int[] plan = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            plan[i] = Integer.parseInt(st.nextToken());
        }

        // 계산
        for(int i=0; i<M-1; i++){
            if(find(plan[i])!=find(plan[i+1])){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
        
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a>b){
            parent[a] = b;
        }
        else{
            parent[b] = a;
        }
    }

    static int find(int n){
        if(parent[n]==n){
            return n;
        }
        return parent[n] = find(parent[n]);

    }
}
