import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int[] arr;
    public static long[] tree;
    public static int mod = 1000000007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); 

       
        arr = new int[N+1];
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 세그먼트 트리 생성
        tree = new long[N*4];
        makeTree(1, N, 1);

        for(int i = 0; i < M+K; i++){
            st = new StringTokenizer(br.readLine());
            int flag = Integer.parseInt(st.nextToken());
            if(flag == 1){
                // 수를 변경하기
                int target = Integer.parseInt(st.nextToken());
                int from = arr[target];
                int to = Integer.parseInt(st.nextToken());
                update(1, N, target, from , to, 1);
                arr[target] = to;
            } else {
                // 구간의 곱 구하기
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                bw.write(multiply(1, N, left, right, 1) + "\n");
            }
        }
        bw.flush();
    }
    
    // 해당 index를 포함하는 구간곱 노드 중 가장 leaf node 부터 계산 
    // 해당 index를 포함하는 구간곱을 이전 수로 나눈 후 변경하려는 수를 곱해준다.   
    public static long update(int start, int end, int target, int from, int to, int node){
        if(target < start || target > end ) 
            return tree[node];
        if(start == end){
            // 이전 수가 0일 경우 예외 처리
            tree[node] = (from == 0)? to : (tree[node]/from*to) % mod;
            return tree[node];
        }
        int mid = (start+end)/2;
        return tree[node] = (update(start, mid, target, from, to, node*2) * update(mid+1, end, target, from, to, node*2+1)) % mod;
    }
    
    // 특정 구간곱 범위 내에 있는 구간곱 노드를 모두 곱해준다.
    // 범위 밖에 있다면 1을 리턴한다.
    public static long multiply(int start, int end, int left, int right,  int node){
        if(left > end || right < start) 
            return 1;
        if(left <= start && end <= right) 
            return tree[node];
        int mid = (start + end)/2;
        return (multiply(start, mid, left, right, node*2) * multiply(mid+1, end, left, right, node*2+1)) % mod;
    }

    public static long makeTree(int start, int end, int node){
        if(start == end) return tree[node] = arr[start];
        int mid = (start + end)/2;
        return tree[node] = (makeTree(start, mid, node*2) * makeTree(mid+1, end, node*2+1)) % mod;
    }
}
