import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static Stack<Double> stack = new Stack<>();
	static int nAlphabet;
	static double[] num;
	static char[] ch;
	static double result;
	static double temp1, temp2;

	public static void fx() {
		for (int i = 0; i < ch.length; i++) {
			
			if (ch[i] >= 65 && ch[i] <= 91) {
				stack.push(num[ch[i] - 65]);
			} else {
				char operator = ch[i];

				temp1 = stack.pop();
				temp2 = stack.pop();
				
				switch (operator) {
				case '+': result = temp2 + temp1;	break;
				case '*': result = temp2 * temp1;	break;
				case '-': result = temp2 - temp1;	break;
				case '/': result = temp2 / temp1;	break;
				}
				
				stack.push(result);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 피연산자 개수 만큼 크기의 배열 생성
		nAlphabet = Integer.parseInt(br.readLine());
		num = new double[nAlphabet];

		// 후위표기식 문장 문자별 배열
		ch = br.readLine().toCharArray();
		
		//피연산자 별 대응 값 배열
		for (int i = 0; i < nAlphabet; i++) {
			num[i] = Integer.parseInt(br.readLine());
		}

		fx();
		
		//출력 결과물은 소숫점 둘째자리 까지
		System.out.printf("%.2f", stack.pop());

	}
}
