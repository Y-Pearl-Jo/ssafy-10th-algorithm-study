import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // n을 입력받기
		int n = Integer.parseInt(br.readLine());

    // 후위표기식 입력받기
		String str = br.readLine();

    // n번 값들을 배열에 저장
		double[] arr = new double[n];
		for (int i = 0; i < n; i++) {
			double num = Double.parseDouble(br.readLine());
			arr[i] = num;
		}

    // 피연산자를 담을 스택
		Stack<Double> numbers = new Stack<>();
		double answer = 0;
		for (int i = 0; i < str.length(); i++) {
      // 알파벳이면, char - 'char' 를 사용하여 인덱스를 뽑고 그 인덱스에 맞는 값을 배열에서 찾아 스택에 push
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				numbers.push(arr[str.charAt(i) - 'A']);
			} else { // 알파벳이 아니면 연산자이므로 연산. 연산 이후에 결과값을 다시 스택에 push
				if (!numbers.isEmpty()) {
					double a = numbers.pop();
					double b = numbers.pop();
					answer = a * b;
					switch (str.charAt(i)) {
					case '+':
						answer = b + a;
						numbers.push(answer);
						break;
					case '-':
						answer = b - a;
						numbers.push(answer);
						break;
					case '*':
						answer = b * a;
						numbers.push(answer);
						break;
					case '/':
						answer = b / a;
						numbers.push(answer);
						break;
					}
				}
			}

		}
    // 소수점 2번째 자리까지 출력
		System.out.printf("%.2f", answer);
	}

}
