import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Integer> num = new ArrayList<>();
	static ArrayList<Character> operate = new ArrayList<>();
	static int max = Integer.MIN_VALUE;
	static int N;

	static void dfs(int now, int sum) {
		if (now >= operate.size()) {
			max = Math.max(max, sum);
			return;
		}
		// 이번 순서에 괄호를 치지 않은 경우
		dfs(now + 1, calculate(now, sum, num.get(now + 1)));
		// (연산자 뒤에)괄호를 치는 경우
		// 남은 연산자 개수 확인
		if (now + 1 < operate.size()) {
			// 다음 연산을 먼저하고
			int next = calculate(now + 1, num.get(now + 1), num.get(now + 2));
			// 이번 차례 연산
			dfs(now + 2, calculate(now, sum, next));
		}
	}

	static int calculate(int op, int num1, int num2) {
		switch (operate.get(op)) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		}
		return 0;
	}

	static void solution() {
		dfs(0, num.get(0));
		System.out.println(max);
	}

	static void make() throws IOException {
		N = init();

		String t = br.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				num.add(t.charAt(i) - '0');
			} else {
				operate.add(t.charAt(i));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
