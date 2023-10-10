// 12048kb 84ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int r;
    int c;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Dice{
    int up;
    int down;
    int right;
    int left;
    int front;
    int back;

    public Dice(int u, int d, int r, int l, int f, int b) {
        up = u;
        down = d;
        right = r;
        left = l;
        front = f;
        back = b;
    }
}

// -------------------------------------------

public class Main {
    static int N,M,r,c,K,ans;
    static int[][] map,score;
    static int d = 0;
    static Dice dice = new Dice(1,6,3,4,2,5); // 주사위

    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = input(st.nextToken()); // 세로
        M = input(st.nextToken()); // 가로
        K = input(st.nextToken()); // 명령 수

        // 지도에 쓰여있는 수
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<M; k++) {
                map[i][k] = input(st.nextToken());
            }
        }

        // 이동 명령
        score = new int[N][M];
        while(K-->0) {
            move(d); // 1. 이동
            getScore(); // 2. 점수 획득
            direction();
        }

        System.out.println(ans);

    }
    // -------------------------------------

    // 3. 이동 방향 결정
    static void direction() {
        int A = dice.down;
        int B = map[r][c];

        if(A>B) {
            d = (d+1)%4; // 시계방향
        }
        else if(A<B) {
            d = (d+3)%4; // 반시계방향
        }

    }

    // 2. 점수 획득
    static void getScore() {
        if(score[r][c]!=0) ans += score[r][c];
        else {
            int cnt = BFS();
            score[r][c] = cnt*map[r][c];
            ans += score[r][c];

        }
    }

    static int BFS() {
        // 큐
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(r,c));

        // 방문 배열
        boolean[][] visited = new boolean[N][M];
        visited[r][c] = true;

        // 변수
        int cnt = 1;
        int num = map[r][c];

        // BFS
        while(!q.isEmpty()) {
            Node node = q.poll();

            for(int d=0; d<4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];

                if(nr<0 || nc<0 || nr>=N || nc>=M || visited[nr][nc]) continue;

                if(map[nr][nc]==num) {
                    cnt++;
                    q.add(new Node(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        return cnt;
    }

    // 1. 주사위 이동
    static void move(int dir) {
        switch (dir){
            case 0: // 오른쪽
                if(c+1>=M) {
                    d=2;
                    left();
                }
                else right();
                break;

            case 1: // 뒤
                if(r+1>=N) {
                    d=3;
                    front();
                }
                else back();
                break;

            case 2: // 왼쪽
                if(c-1<0) {
                    d=0;
                    right();
                }
                else left();
                break;

            case 3: // 앞
                if(r-1<0) {
                    d=1;
                    back();
                }
                else front();
        }

    }

    // (1)동쪽
    static void right() {
        c++;
        int temp = dice.right;
        dice.right = dice.up;
        dice.up = dice.left;
        dice.left = dice.down;
        dice.down = temp;
    }

    // (2)서쪽
    static void left() {
        c--;
        int temp = dice.left;
        dice.left = dice.up;
        dice.up = dice.right;
        dice.right = dice.down;
        dice.down = temp;
    }

    // (3)북쪽
    static void front() {
        r--;
        int temp = dice.front;
        dice.front = dice.up;
        dice.up = dice.back;
        dice.back = dice.down;
        dice.down = temp;
    }

    // (4)남쪽
    static void back() {
        r++;
        int temp = dice.back;
        dice.back = dice.up;
        dice.up = dice.front;
        dice.front = dice.down;
        dice.down = temp;
    }


    static int input(String str) {
        return Integer.parseInt(str);
    }

}
