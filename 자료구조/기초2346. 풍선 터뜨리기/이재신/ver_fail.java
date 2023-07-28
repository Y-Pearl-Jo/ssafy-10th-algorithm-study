import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> deque = new ArrayDeque<>();
		Map<Integer, Integer> map = new HashMap<>();

		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int time = 0;

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			deque.add(num);
			map.put(num, i + 1);
		}

		
		for (int i = 1; i < N; i++) {
			time = deque.poll();
			System.out.print(map.get(time) + " ");
			if (time > 0) {
				
				for (int j = 1; j < time; j++) {
					deque.add(deque.poll());
				}
			} else if (time < 0) {

				for (int j = time; j < 0; j++) {
					deque.addFirst(deque.pollLast());
				}
			}

		}
		System.out.print(map.get(deque.poll()) + " ");

	}
}
