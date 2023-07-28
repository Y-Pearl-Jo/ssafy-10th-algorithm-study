import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static Deque<int[]> deque = new ArrayDeque<>();
	static int[] time;
	// 와....
	// new ArrayDeque와 new LinkedList는 메모리 잡아먹는게 다르다!!!!!!!

	public static void balloon() {
		// while의 조건이 1 초과여야 하는 이유!
		// 한번 poll 한 후에 조건문을 돌리기 때문에 ArrayIndexOutOfBoundException이 뜬다!
		// 안에서 한번 더 size를 확인해도 되겠지만 그럼 연산횟수만 늘어날 것 같아서 그냥 1초과로 함!
		while (deque.size() > 1) {
			time = deque.pollFirst();
			sb.append(time[0]).append(" ");
			if (time[1] > 0)		queue();
			else if (time[1] < 0)	reverseQueue();
		}

	}
	
	public static void queue() {
		for (int j = 1; j < time[1]; j++) {
			deque.offerLast(deque.pollFirst());
		}
	}
	
	public static void reverseQueue() {
		for (int j = time[1]; j < 0; j++) {
			deque.offerFirst(deque.pollLast());
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		// num[0] = idx, num[1] = 벡터
		for (int i = 0; i < N; i++) {
			int[] num = { i + 1, Integer.parseInt(st.nextToken()) };
			deque.add(num);
		}

		balloon();
		
		sb.append(deque.poll()[0]);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
}
