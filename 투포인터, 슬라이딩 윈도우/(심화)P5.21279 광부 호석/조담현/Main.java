import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Mineral implements Comparable {
    int x;
    int y;
    int v;

    Mineral(int x, int y, int v) {
    	this.x = x;
        this.y = y;
        this.v = v;
    }

    @Override
    public int compareTo(Object o) {
        Mineral m = (Mineral)o;
        return this.y - m.y;
    }
    
    
}

public class Main {
    static int N, C;
    static long maxSum = 0;
    static long tempSum = 0;
    static int tempCount = 0;
    static List<List<Mineral>> mine = new ArrayList<>();
    static List<List<Mineral>> mineY = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 100001; i++) {
            mine.add(new ArrayList<Mineral>());
            mineY.add(new ArrayList<Mineral>());
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            mine.get(X).add(new Mineral(X, Y, V));
            mineY.get(Y).add(new Mineral(X, Y, V));
        }
        
        for (int i = 0; i < 100001; i++) {
            Collections.sort(mine.get(i));
            Collections.sort(mineY.get(i), (o1, o2)-> {
                return (o1.x - o2.x);
            });
        }
        
        int end = 100000;
//        Y를 최대, X를 0부터 시작.
//        X를 증가시켰을때, X * Y 에 해당하는 개수가 C개를 초과하면 더이상 Y높이에서는 X를 높여도 의미가 없으므로 Y를 --
//        C개를 초과하지 않으면 X*Y가 무조건 더 많으므로 가중치를 모두 더해보고 최댓값이라면 업데이트. 이후 X를 ++

//        그렇다면 X, Y, V를 담은 노드를 리스트로 담아두고?
//        X를 인덱스로 Y, V값들을 리스트에 저장.
        for (int start = 0; start < 100001; start++) {
            // x축에 해당하는 end보다 같거나 작은 y좌표에 있는 광물 가치들을 더해준다.
            for (int i = 0; i < mine.get(start).size(); i++) {
                if (mine.get(start).get(i).y <= end) {
                    tempSum += mine.get(start).get(i).v;
                    tempCount++;
                } else {
                    break;
                }
            }
            // y축에 해당하는 start보다 같거나 작은 x좌표에 있는 광물 가치들을 빼준다.
            while (tempCount > C) {
                for (int i = 0; i <mineY.get(end).size(); i++) {
                    if (mineY.get(end).get(i).x <= start) {
                        tempSum -= mineY.get(end).get(i).v;
                        tempCount--;
                    } else {
                        break;
                    }
                }
                //  X * Y 에 해당하는 개수가 C개를 초과하면 더이상 Y높이에서는 X를 높여도 의미가 없으므로 Y를 --
                end--;
            }
            // C개를 초과하지 않으면 X*Y가 무조건 더 많으므로 가중치를 모두 더해보고 최댓값이라면 업데이트. 이후 X를 ++
            maxSum = Math.max(maxSum, tempSum);
            if(end==0) break;
        }
        
        System.out.println(maxSum);
    }

}


