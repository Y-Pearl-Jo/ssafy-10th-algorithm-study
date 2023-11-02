// 92496kb 732ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Mineral{
	int x,y;
	long value;
	public Mineral(int y, int x, long value) {
		this.y = y;
		this.x = x;
		this.value = value;
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N,C,cnt;
	static long sum,ans; // long..
	static int[][] map;
	static List<Mineral>[] X = new ArrayList[100001];
	static PriorityQueue<Mineral> pq;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = input(st);
		C = input(st); // 선택 가능한 최대 개수
		
		for(int i=0; i<X.length; i++) {
			X[i] = new ArrayList<>();
		}
		
		// c, r, v
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = input(st);
			int y = input(st);
			long value = input(st);
			X[x].add(new Mineral(y, x, value));
		}
		
		asdf();
		
		System.out.println(ans);
		
	}
	
	static void asdf() {
		pq = new PriorityQueue<>((o1, o2)->Integer.compare(o2.y, o1.y));

		// x좌표의 모든 value 더하기
		for(int i=0; i<X.length; i++) {
			if(X[i].size()==0) continue;
			
			for(int j=0; j<X[i].size(); j++) {
				Mineral m = X[i].get(j); // x좌표가 i인 광물
				sum += m.value;
				pq.offer(m);
				cnt++;
			}

			// 광물의 개수가 c 이하인 경우
			if(cnt<=C) {
				ans = Math.max(ans,sum);
			}
			// 광물의 개수가 c를 넘는 경우
			else {
				qwer();
			}
		}
	}

	// 빼기
	static void qwer() {
		// cnt가 C이하가 될 때까지 Y.poll해서 temp에서 빼주기
		while(!pq.isEmpty()) {
			Mineral m = pq.poll();
			sum -= m.value;
			cnt--;
			
			if(!pq.isEmpty() && m.y==pq.peek().y) continue;
			
			if(cnt<=C) break;

		}
		
		// cnt가 C이하라면
		if(cnt<=C) {
			ans = Math.max(ans,sum);
		}

	}

	// -----------

	
	static int input() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	
	static int input(StringTokenizer st) throws IOException {
		return Integer.parseInt(st.nextToken());
	}

}
