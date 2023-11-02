//261488KB 944ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	int sample;
	int index;

	public Point(int sample, int index) {
		super();
		this.sample = sample;
		this.index = index;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 시퀀스의 길이
		int M = Integer.parseInt(st.nextToken()); // 최대 범위
		int C = Integer.parseInt(st.nextToken()); // 최대 값 차이

		Deque<Point> dqMax = new LinkedList<>();
		Deque<Point> dqMin = new LinkedList<>();

		st = new StringTokenizer(br.readLine());
		int t;
		for (int i = 1; i <= N; i++) {
			// 입력
			t = Integer.parseInt(st.nextToken());

			// 데큐에 들어있는 샘플이 M개가 넘는다면 데큐에서 맨 앞값 빼줌
			if (!dqMax.isEmpty() && dqMax.peekFirst().index <= i - M)
				dqMax.pollFirst();
			if (!dqMin.isEmpty() && dqMin.peekFirst().index <= i - M)
				dqMin.pollFirst();

			// 맨 뒤에 들어있는 값보다 새로 들어온 값이 작거나/크면 맨뒤에 들어있는 값을 빼고 새로운 값을 데큐에 추가
			while (!dqMax.isEmpty() && dqMax.peekLast().sample < t)
				dqMax.pollLast();
			dqMax.addLast(new Point(t, i));

			while (!dqMin.isEmpty() && dqMin.peekLast().sample > t)
				dqMin.pollLast();
			dqMin.addLast(new Point(t, i));

			if (i - M + 1 > 0 && !dqMax.isEmpty() && !dqMin.isEmpty()
					&& dqMax.peekFirst().sample - dqMin.peekFirst().sample <= C) {
				sb.append(i - M + 1).append("\n");
			}

		}

		if (sb.length() == 0) {
			System.out.println("NONE");
		} else {
			System.out.print(sb);
		}
	}
}
