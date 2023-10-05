import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*  1. 중첩된 괄호는 안되고, 연산자가 반드시 하나만 들어있어야 하므로 인접한 두 항목을 연산하는 경우와 오른쪽에 괄호를 쳐서 연산하는 경우를 나누어 dfs
 */
public class Main {
	static int max;
	static int N;
	static String exp;
	static List<Integer> numbers = new ArrayList<>();
	static List<Character> operators = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		max = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		exp = br.readLine();

		for (int i = 0; i < exp.length(); i++) {
			if (i % 2 == 0)
				numbers.add(exp.charAt(i) - '0');
			else
				operators.add(exp.charAt(i));
		}

		int start = numbers.get(0);
		addBracket(0, start);
		System.out.println(max);
	}

	public static void addBracket(int opIdx, int sum) {
		if (opIdx == operators.size()) {
			max = Math.max(max, sum);
			return;
		}

		// 괄호 추가하지 않는 경우 (정방향)
		addBracket(opIdx + 1, calculate(opIdx, sum, numbers.get(opIdx + 1)));
		// 괄호를 오른쪽에 추가하는 경우 (우측 두개의 피연산자 계산 후 현재 피연산자 계산)
		if(opIdx<operators.size()-1) {
			int value = calculate(opIdx + 1, numbers.get(opIdx + 1), numbers.get(opIdx + 2));
			addBracket(opIdx + 2, calculate(opIdx, sum, value));
		}
	}

	public static int calculate(int opIdx, int n1, int n2) {
		switch (operators.get(opIdx)) {
		case '+':
			return n1 + n2;
		case '-':
			return n1 - n2;
		default:
			return n1 * n2;
		}
	}

}
