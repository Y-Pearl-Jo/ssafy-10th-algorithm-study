import java.io.*;
import java.util.*;
 
public class Main {
    public static class Ball implements Comparable<Ball> {
        int idx;
        int c; // 공의 색
        int s; // 공의 크기
		
        public Ball (int idx, int c, int s) {
            this.idx = idx;
            this.c = c;
            this.s = s;
        }
		
        public int compareTo (Ball o) {
            return this.s - o.s; // 공의 크기 오름차순 정렬
        }
    }
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine()); // 공의 개수
		
        ArrayList<Ball> ball = new ArrayList<>();
		
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
			
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
			
            ball.add(new Ball(i, color, size)); // 공의 정보 저장
        }
		
        Collections.sort(ball); // 크기가 작은 것부터 오름차순 정렬
		
        int[] color = new int[N+1];
        int[] ans = new int[N];
		
        int sum = 0;
        int j = 0;
        for (int i=0; i<N; i++) {
            Ball a = ball.get(i);
            Ball b = ball.get(j);
			
            while (b.s < a.s) {
                sum += b.s; // 공의 크기 누적합 (자기보다 작은 공 잡기)
                color[b.c] += b.s; // 공의 색별 누적합
				
                b = ball.get(++j);
            }
			
            ans[a.idx] = sum - color[a.c]; // 전체 누적합 - 현재 색에 해당하는 공의 누적합 빼기 (내 색에 해당하는 공 제외하고 잡은 것이므로)
        }
		
		
        for (int num : ans) {
            System.out.println(num);
        }
    }
}
