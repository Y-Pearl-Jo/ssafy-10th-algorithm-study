// 299944kb 1364ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Tree{
    int x;
    int y;
    int age;
    public Tree(int x, int y, int age){
        this.x = x;
        this.y = y;
        this.age = age;
    }
}

public class Main {
    // 변수
    static int N,M,K;
    static int[][] nut,addNut;
    static PriorityQueue<Tree> trees;
    static int[] dx = {1,0,-1,0,1,1,-1,-1};
    static int[] dy = {0,1,0,-1,1,-1,1,-1};

    // 메인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 땅 초기 상태 : 양분 5
        nut = new int[N][N];
        for(int i=0; i<N; i++){
            Arrays.fill(nut[i],5);
        }

        // A배열의 값
        addNut = new int[N][N];
        for(int y=0; y<N; y++){
            st = new StringTokenizer(br.readLine());
            for(int x=0; x<N; x++){
                addNut[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 나무의 정보
        // x y 순서때문에 시간을 날렸다;
        trees = new PriorityQueue<>((o1,o2)->Integer.compare(o1.age,o2.age));
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(x,y,age));
        }

        // K년
        while(K-- > 0){
            ss();
            autumn();
            winter();
        }

        // 살아있는 나무의 수
        System.out.println(trees.size());
    }

    // 봄 여름
    static void ss(){
        ArrayList<Tree> alive = new ArrayList<>();
        ArrayList<Tree> dead = new ArrayList<>();

        while(!trees.isEmpty()){
            Tree t = trees.poll();
            // 양분이 부족할 때
            if(t.age>nut[t.y][t.x]){
                dead.add(t);
            }
            // 양분을 줄 수 있을 때
            else{
                nut[t.y][t.x] -= t.age;
                t.age += 1;
                alive.add(t);
            }
        }

        // 키 큰 나무 다시 넣기
        for(Tree t : alive){
            trees.offer(t);
        }
        // 죽은 나무 -> 양분
        for(Tree t : dead){
            nut[t.y][t.x] += t.age/2;
        }

    }

    // 가을
    static void autumn(){
        // 나무 번식
        ArrayList<Tree> born = new ArrayList<>();
        for(Tree t : trees){
            if(t.age%5==0){
                for(int d=0; d<8; d++){
                    int nx = t.x+dx[d];
                    int ny = t.y+dy[d];
                    if(nx>=0 && nx<N && ny>=0 && ny<N){
                        born.add(new Tree(nx,ny,1));
                    }
                }
            }
        }

        // 새 트리 더하기
        trees.addAll(born);

    }

    // 겨울
    static void winter(){
        // 양분 추가
        for(int y=0; y<N; y++){
            for(int x=0; x<N; x++){
                nut[y][x] += addNut[y][x];
            }
        }
    }
}
