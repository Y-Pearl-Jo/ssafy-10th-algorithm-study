import java.io.*;
import java.util.*;
 
public class Main {
    public static class Ball implements Comparable<Ball> {
        int idx;
        int c;
        int s;
		
        public Ball (int idx, int c, int s) {
            this.idx = idx;
            this.c = c;
            this.s = s;
        }
		
        public int compareTo (Ball o) {
            return this.s - o.s;
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
			
            ball.add(new Ball(i, color, size));
        }
		
        Collections.sort(ball);
		
        int[] color = new int[N+1];
        int[] ans = new int[N];
		
        int sum = 0;
        int j = 0;
        for (int i=0; i<N; i++) {
            Ball a = ball.get(i);
            Ball b = ball.get(j);
			
            while (b.s < a.s) {
                sum += b.s;
                color[b.c] += b.s;
				
                b = ball.get(++j);
            }
			
            ans[a.idx] = sum - color[a.c];
        }
		
		
        for (int num : ans) {
            System.out.println(num);
        }
    }
}
