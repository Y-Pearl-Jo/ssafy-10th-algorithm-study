/* 처음 짠 코드. 재귀로 풀었으나 n의 범위때문에 스택오버플로우 발생
import java.io.*;
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(pentagon(n)%45678);
		
		
		
 	}
	
	public static int pentagon(int n) {
		if(n==1) {
			return 5;
		} else {
			return pentagon(n-1) + (n*3+1);
		}
		
	}

}

*/

import java.io.*;
public class Main {
		// 1초 = 연산 1억번이므로 완전탐색
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
    // 배열을 이용하여 이전 단계의 점 개수 저장
		long[] arr = new long [n+1];
    // 1단계일때는 5개
		arr[1]=5L;
    // 점의 개수 늘어나는 규칙
		for(int i=2; i<=n; i++) {
			arr[i] = arr[i-1] + (i*3+1);
		}
		
		System.out.println(arr[n]%45678L);
		
		
 	}
	

}

