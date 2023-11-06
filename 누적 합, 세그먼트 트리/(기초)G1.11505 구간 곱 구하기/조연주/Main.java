// 117008kb 528ms
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N,M,K;
    static int MOD =  1000000007;
    static long[] num, tree;

    static void solve() throws IOException {
        makeTree(1,N,1);

        int cnt = M+K;
        while(cnt-->0){
            st = new StringTokenizer(br.readLine());
            int cmd = input(st);
            int a = input(st);
            int b = input(st);

            switch (cmd){
                case 1:
                    updateTree(1,N,1,a,b);
                    break;
                case 2:
                    sb.append(getAns(1,N,1,a,b)%MOD);
                    sb.append("\n");
            }
        }
    }

    static long makeTree(int start, int end, int node){
        if(start == end){
            return tree[node] = num[start];
        }

        int mid = (start+end)/2;
        return tree[node] = (makeTree(start,mid,node*2) * makeTree(mid+1,end,(node*2)+1))%MOD;
    }

    static long updateTree(int start, int end, int node, int target, int value){
        if(target < start || target > end){
            return tree[node];
        }

        if(start == end){
            return tree[node] = value;
        }

        int mid = (start+end)/2;
        return tree[node] = (updateTree(start,mid,node*2,target,value) * updateTree(mid+1,end,(node*2)+1,target,value))%MOD;
    }

    static long getAns(int start, int end, int node, int left, int right){
        if(left > end || right < start) return 1;

        if(left <= start && right >= end) return tree[node];

        int mid = (start+end)/2;
        return (getAns(start,mid,node*2,left,right) * getAns(mid+1,end,(node*2)+1,left,right))%MOD;
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st);
        M = input(st);
        K = input(st);
        num = new long[N+1];
        tree = new long[4*N];

        for(int i=1; i<=N; i++){
            num[i] = input();
        }

        solve();

        bw.write(sb.toString());
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
