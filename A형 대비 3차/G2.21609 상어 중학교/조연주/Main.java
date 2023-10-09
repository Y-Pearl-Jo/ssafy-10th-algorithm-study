// 18792kb 192ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// -2 : 빈칸  -1 : 검정  0 : 무지개  1~M : 일반

public class Main {
    static int ans,N,M;
    static int[][] map;
    static List<Node> score;
    static boolean isGrouped;
    static boolean[][] visited;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 한변의 크기
        M = Integer.parseInt(st.nextToken()); // 색상의 개수

        // 게임 맵 입력받기
        map = new int[N][N];
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 블록 그룹이 존재하는 동안 반복
        isGrouped = true;
        while(isGrouped){
            isGrouped = false;
            find1(); // 1
            remove(); // 2
            gravity(); // 3
            lotate(); // 4
            gravity(); // 5
        }

        System.out.println(ans);

    }
    // -----------------------------

    // 확인
    static void print(){
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // (1)크기가 가장 큰 블록 그룹 찾기
    static void find1(){
        score = new ArrayList<>();
        visited = new boolean[N][N];
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                // 값이 1이상이고, 방문하지 않은 곳에서 시작
                if(map[r][c]>0 && !visited[r][c]) find2(r,c);
            }
        }

        // 정렬
        score.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.size == o2.size){
                    if(o1.rb == o2.rb){
                        if(o1.r == o2.r) return o2.c - o1.c;
                        return o2.r - o1.r;
                    }
                    return o2.rb - o1.rb;
                }
                return o2.size - o1.size;
            }
        });
    }

    static void find2(int r, int c){
        Queue<Node> q = new LinkedList<>();
        Node node = new Node(r,c);
        q.offer(node);

        visited[r][c] = true;
        int num = map[r][c];

        while(!q.isEmpty()){
            Node nextNode = q.poll();

            for(int d=0; d<4; d++){
                int nr = nextNode.r + dr[d];
                int nc = nextNode.c + dc[d];

                if(nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc] || map[nr][nc]<0) continue;

                // 0 이상인 경우
                if(map[nr][nc]==num || map[nr][nc]==0){
                    isGrouped = true;
                    if(map[nr][nc]==0){
                        node.rb++;
                    }
                    node.size++;
                    node.group.add(new Node(nr,nc));
                    q.offer(new Node(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        score.add(node);

        // 값이 0인 블록의 방문 체크 해제
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                if(map[i][k]==0 && visited[i][k]){
                    visited[i][k] = false;
                }
            }
        }
    }

    // (2)블록 그룹 제거, 점수 획득
    static void remove(){
        // 점수 추가
        if(score.isEmpty()){
            return;
        }
        Node node = score.get(0);
        if(node.size==1){
            return;
        }
        ans += (int) Math.pow(node.size,2);

        // 블록 제거
        map[node.r][node.c] = -2;
        List<Node> li = node.group;
        for(int i=0; i<li.size(); i++){
            map[li.get(i).r][li.get(i).c] = -2;
        }
    }

    // (3,5)중력 -> r:N-2부터 탐색 -> r+1 == -2 일 때 이동
    static void gravity(){
        // 중력 작용
        for(int r=N-2; r>=0; r--){
            for(int c=0; c<N; c++){
                if(map[r][c]<0) continue; // r : -1(검정) or -2(빈칸) 일때
                else if(map[r+1][c]==-2){
                    down(r,c);
                }
            }
        }
    }

    static void down(int r, int c){
        while(true){
            if(r==N-1 || map[r+1][c]!=-2) return;
            map[r+1][c] = map[r][c];
            map[r][c] = -2;
            r++;
        }
    }

    // (4)반시계 방향 회전 : 0,0 -> 0,N-1
    // y = x
    // x = (N-1)-y
    static void lotate(){
        // 복사 배열 생성
        int[][] copyMap = new int[N][N];
        for(int i=0; i<N; i++){
            copyMap[i] = map[i].clone();
        }

        // 회전
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                copyMap[r][c] = map[c][(N-1)-r];
            }
        }

        // 원래 배열에 저장
        for(int i=0; i<N; i++){
            map[i] = copyMap[i].clone();
        }

    }

}

class Node{
    int r;
    int c;
    int size;
    int rb;
    List<Node> group;
    public Node(int r, int c){
        this.r = r;
        this.c = c;
        size = 1;
        rb = 0;
        group = new ArrayList<>();
    }
}
