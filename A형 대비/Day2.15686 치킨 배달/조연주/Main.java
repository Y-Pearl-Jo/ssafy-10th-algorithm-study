// 12540kb 140ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M,sum;
    static int minSum = Integer.MAX_VALUE;
    static boolean[] picked;
    static List<Node> chicken = new ArrayList<>();
    static List<Node> house = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 고를 치킨집 개수

        // 집, 치킨집 좌표 입력받기
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=1; k<=N; k++){
                int n = Integer.parseInt(st.nextToken());
                if(n==1){
                    house.add(new Node(i,k));
                }
                else if(n==2){
                    chicken.add(new Node(i,k));
                }
            }
        }

        picked = new boolean[chicken.size()];
        choice(0,0);
        System.out.println(minSum);

    }

    // 치킨집 M개 고르기
    static void choice(int cnt, int start){
        if(cnt==M){
            pickHouse();
            minSum = Math.min(minSum, sum);
            return;
        }

        for(int i=start; i<chicken.size(); i++){
            picked[i] = true;
            choice(cnt+1,i+1);
            picked[i] = false;
        }
        
    }

    // 고른 치킨집으로 치킨 거리 계산
    static void pickHouse(){
        sum = 0;
        for (Node node : house) {
            calc(node);
        }
    }

    static void calc(Node house){
        int min = Integer.MAX_VALUE;
        for(int i=0; i<chicken.size(); i++){
            if(picked[i]){
                int r = Math.abs(chicken.get(i).r - house.r);
                int c = Math.abs(chicken.get(i).c - house.c);
                min = Math.min(min,(r+c));
            }
        }
        sum += min;
    }
}

class Node{
    int r;
    int c;
    public Node(int r, int c){
        this.r = r;
        this.c = c;
    }
}
