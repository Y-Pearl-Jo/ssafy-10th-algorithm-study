import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test_case = Integer.parseInt(br.readLine());
		int count = 0;
		int num;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		test : for(int i = 1; i <= test_case; i++) {
			num = Integer.parseInt(st.nextToken());
			
			//1은 소수가 아님
			if(num == 1) continue;
					
			//에라토스테네스의 체에 따라 제곱근까지만 보면 됨
			for(int j = 2; j <= Math.sqrt(num); j++) {
				if (num % j == 0) continue test;
			}
			
			count++;
		}
		
		System.out.print(count);
				
	}
}
