import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 괄호 문제의 조건
 * 0. 왼쪽 괄호면 push, 오른쪽 괄호면 pop
 * 1. 왼쪽 괄호가 오른쪽 괄호보다 먼저 나와야 한다. pop했을때 empty면 안됨.
 * 2. 왼쪽 괄호의 갯수와 오른쪽 괄호의 갯수가 같아야 한다. 문자열을 다 돌고 empty여야 함.
 * 3. 괄호의 짝이 맞아야 한다. [](){} pop하면서 검사.
 */

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] brackets = br.readLine().split("");
		Stack<String> stack = new Stack<>();
		int answer = 0;
		
		
		for(int i=0; i<brackets.length; i++) {
			String input = brackets[i];
			
			// 0. 왼쪽 괄호면 push
			if( input.equals("(") || input.equals("[")) {
				stack.push(input);
				continue;
			} 
			 
			// 0. 오른쪽 괄호면 pop
			else {
				// 1번 조건 검사
				if(stack.empty()) {
					System.out.println(0);
					return;
				}
				
				// 각 괄호 안에 저장되는 x, y의 값
				int temp = 1;
				
				
				//3번 조건 검사
				while(true) {
					
					String output = stack.pop();
					// pop한게 숫자 였을 때
					if( !(output.equals("[")) && !(output.equals("(") )) {
						
						
						// 다음 pop이 비었다면 1번 조건 위배 << 이 부분을 놓쳐서 오답
						if(stack.empty()) {
							System.out.println(0);
							return;
						}
						
						// 다음pop이 여는 괄호라면 한번 더 진행
						else if(stack.peek().equals("[") || stack.peek().equals("(")) {
							temp = Integer.parseInt(output);
							continue;
						} 
						// 다음pop이 숫자라면 더하기
						else {
							int temp2 = Integer.parseInt(stack.pop());
							int newTemp = Integer.valueOf(output) + temp2;
							stack.push(String.valueOf(newTemp));
							
							continue;
						}
						
					}
					// pop한게 괄호였을 때
					if(input.equals(")") && output.equals("(")) {
						temp*= 2;
						stack.push(String.valueOf(temp));
						break;
					} else if(input.equals("]") && output.equals("[")) {
						temp*=3;
						stack.push(String.valueOf(temp));
						break;
					}  else {
							System.out.println(0);
							return;
					}
					
				}
				

			}
						
		}
		
		// 스택에 숫자만 남아야 한다.
		while(!stack.empty()) {
			String output = stack.pop();
			//1번 조건 검사
			if(output.equals("(")||output.equals("[")) {
				System.out.println(0);
				return;
			} else {
				int num = Integer.parseInt(output);
				answer+=num;
			}
		}
		
		System.out.println(answer);
		
		
		

	}

}
