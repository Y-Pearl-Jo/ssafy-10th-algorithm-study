import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main2 {
	static double result = 0;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();

		double[] arr = new double[N];
		Stack<Double> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				double back = stack.pop();
				double front = stack.pop();
				stack.push(calculation(front, back, c));
			} else {
				// 'A' = 65 이기때문에, 인덱스 0(A-65)에 A의 값이 들어간다. 인덱스 1(B-65)에는 B의 값이 들어간다.
				stack.push(arr[c - 'A']);
			}
		}
		System.out.printf("%.2f", stack.pop());
	}

	public static double calculation(double front, double back, char operator) {
		if (operator == '+') {
			return front + back;
		} else if (operator == '-') {
			return front - back;
		} else if (operator == '*') {
			return front * back;
		} else {
			return front / back;
		}
	}
}
