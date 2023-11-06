import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N,M;
    static int[] num, minTree, maxTree;

    static void solve() throws IOException {
        makeMin(1,N,1);
        makeMax(1,N,1);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = input(st);
            int b = input(st);
            bw.write(getMin(1,N,1,a,b)+" ");
            bw.write(getMax(1,N,1,a,b)+"\n");
        }
    }

    static int makeMin(int start, int end, int node){
        if(start == end){
            return minTree[node] = num[start];
        }
        int mid = (start+end)/2;

        return minTree[node] = Math.min(makeMin(start,mid,node*2), makeMin(mid+1,end,(node*2)+1));
    }

    static int makeMax(int start, int end, int node){
        if(start == end){
            return maxTree[node] = num[start];
        }
        int mid = (start+end)/2;

        return maxTree[node] = Math.max(makeMax(start,mid,node*2), makeMax(mid+1,end,(node*2)+1));
    }

    static int getMin(int start, int end, int node, int left, int right){
        if(left > end || right < start) return Integer.MAX_VALUE;

        if(left <= start && right >= end) return minTree[node];

        int mid = (start+end)/2;
        return Math.min(getMin(start,mid,node*2,left,right),getMin(mid+1,end,(node*2)+1,left,right));
    }

    static int getMax(int start, int end, int node, int left, int right){
        if(left > end || right < start) return Integer.MIN_VALUE;

        if(left <= start && right >= end) return maxTree[node];

        int mid = (start+end)/2;
        return Math.max(getMax(start,mid,node*2,left,right),getMax(mid+1,end,(node*2)+1,left,right));
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st);
        M = input(st);
        num = new int[N+1];
        minTree = new int[4*N];
        maxTree = new int[4*N];

        for(int i=1; i<=N; i++){
            num[i] = input();
        }

        solve();

        bw.flush();
        bw.close();
    }

    // -----------

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st) throws IOException {
        return Integer.parseInt(st.nextToken());
    }

}
