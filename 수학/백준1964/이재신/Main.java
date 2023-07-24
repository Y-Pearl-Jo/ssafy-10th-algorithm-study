import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//중간에 제곱수가 있어서 long 선언				
		long num = Integer.parseInt(br.readLine());
		
		/*
		 * A(n+1) = A(n) + 3n + 1
		 * A(n) = A(0) + (k=1 ~ n)시그마(3n+1)
		 * A(n) = A(0) + n + 3 * (k=1 ~ n)시그마n
		 * A(n) = A(0) + n + 3*n(n+1)/2
		 * A(n) = A(0) + n(3n+5)/2
		 * A(n) = 1 + n(3n+5)/2		(A(0) = 1)
		 */
		
		System.out.println((num*(3*num + 5)/2 + 1)%45678);
				
	}
}
