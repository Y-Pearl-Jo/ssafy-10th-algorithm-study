// 43404 KB, 1432 ms
import java.io.*;
import java.util.*;

class Num implements Comparable<Num>{
    int n, idx;
    public Num(int n, int idx){
        this.n = n;
        this.idx = idx;
    }

    @Override
    public int compareTo(Num o) {
        return this.n - o.n;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;
    static Num[] arr;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        N = input();
        arr = new Num[N];
        tree = new int[4*N];

        init();
        makeTree(1, 1, N);

        // 계산, 출력
        int idx;
        for(int i=0; i<(N+1)/2; i++){
            // 1 ~
            idx = arr[i].idx;
            System.out.println(getSum(1,1,N,1,idx-1));
            updateTree(1,1,N,idx);

            // 홀수인 경우 종료 조건
            if(2*i == N-1){
                break;
            }

            // N ~
            idx = arr[N-1-i].idx;
            System.out.println(getSum(1,1,N,idx+1,N));
            updateTree(1,1,N,idx);
        }
    }

    static void init() throws IOException {
        for(int i=0; i<N; i++){
            int n = input();
            arr[i] = new Num(n, i+1);
        }
        Arrays.sort(arr);
    }

    static int makeTree(int node, int start, int end){
        if(start == end){
            return tree[node] = 1;
        }

        int mid = (start+end)/2;
        return tree[node] = makeTree(node*2, start, mid) + makeTree(node*2+1, mid+1, end);
    }

    static void updateTree(int node, int start, int end, int target){
        if(target < start || end < target){
            return;
        }

        tree[node] -= 1;

        if(start != end){
            int mid = (start+end)/2;
            updateTree(node*2, start, mid, target);
            updateTree(node*2+1, mid+1, end, target);
        }
    }

    static int getSum(int node, int start, int end, int left, int right){
        if(right < start || end < left){
            return 0;
        }

        if(left <= start && end <= right){
            return tree[node];
        }

        int mid = (start+end)/2;
        return getSum(node*2, start, mid, left, right) + getSum(node*2+1, mid+1, end, left, right);
    }


    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

}
