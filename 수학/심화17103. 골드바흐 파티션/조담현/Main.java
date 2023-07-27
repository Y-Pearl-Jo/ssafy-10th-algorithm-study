
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			int count = 0;
			boolean[] isPrime = new boolean[n+1]; // true면 소수 아님, false면 소수
			isPrime[0] = true;
			isPrime[1] = true;
			
			//에라토스테네스의 체로 루트n까지의 소수를 구한다.
			for(int j = 2; j<=Math.sqrt(n); j++) {
				for(int k = 2; j*k<=n; k++) {
					if (isPrime[j] == false) {
						isPrime[j*k] = true;
					}
				}
			}
				
				
			// 작은 소수부터 n에서 뺐을 때 그 수가 소수인지 확인하고 소수라면 count를 1 증가시킨다.
			for(int j=2; j<=isPrime.length/2; j++) {
				if(isPrime[j]==false) {
					if(isPrime[n-j] == false) {
						//두 소수의 순서만 다른 것은 같은 파티션이다.
							count++;
						
					}
				}
			}
			System.out.println(count);
		}
	}
}


