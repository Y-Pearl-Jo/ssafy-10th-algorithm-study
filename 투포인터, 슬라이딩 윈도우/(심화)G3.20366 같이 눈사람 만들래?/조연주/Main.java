// 24268kb 412ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 모든 눈사람을 만든 뒤에 차이의 최솟값을 구하는 경우 -> 눈사람을 만든 재료의 인덱스를 저장해야 함 (중복이면 안 되기 때문)
// 하나씩 만들어보는 경우 -> 완탐하면 연산횟수 4억번 이상

class snowman implements Comparable<snowman>{
    int n1, n2, h;
    public snowman(int n1, int n2, int h) {
        this.n1 = n1; // 재료 인덱스1
        this.n2 = n2; // 재료 인덱스2
        this.h = h; // 눈사람의 키
    }
    @Override
    public int compareTo(snowman o) {
        return this.h - o.h;
    }
}

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int ans = Integer.MAX_VALUE;
    static int[] arr;
    static ArrayList<snowman> list;

    public static void main(String[] args) throws IOException {
        N = input();
        arr = new int[N];

        // 눈덩이 크기 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = input(st);
        }

        make();
        getMin();

        System.out.println(ans); // 정답 출력

    }

    // 만들 수 있는 눈사람의 크기 구하기
    static void make() {
        list = new ArrayList<>();

        for(int i=0; i<N-1; i++) {
            for(int k=i+1; k<N; k++) {
                int h = arr[i] + arr[k];
                list.add(new snowman(i,k,h));
            }
        }

        Collections.sort(list); // 키 순으로 정렬

    }

    // 키 차이 최솟값 구하기
    static void getMin() {
      
        for(int start=0; start<list.size()-1; start++) {
            int end = start+1; // 오름차순으로 정렬했으므로, 가장 가까운 값과 먼저 비교
            snowman s1 = list.get(start);

            while(end<list.size()) {
                snowman s2 = list.get(end);
                int gap = s2.h - s1.h;

                if(gap>ans) break; // 눈사람의 키 차이가 최솟값보다 큰 경우는 확인할 필요 없음
                
                // 재료인 인덱스가 겹치지 않는 경우
                if(index(s1,s2)) {
                    ans = gap;
                    break;
                }
                // 겹치는 경우
                else {
                    end++;
                }
            }
        }
    }

    // ---------------------------

    // 눈사람을 만든 재료가 같은지 확인
    static boolean index(snowman s1, snowman s2) {
        return s1.n1 != s2.n1 && s1.n1 != s2.n2 && s1.n2 != s2.n1 && s1.n2 != s2.n2;
    }

    static int input() throws IOException {
        return Integer.parseInt(br.readLine());
    }

    static int input(StringTokenizer st) throws IOException {
        return Integer.parseInt(st.nextToken());
    }

}
