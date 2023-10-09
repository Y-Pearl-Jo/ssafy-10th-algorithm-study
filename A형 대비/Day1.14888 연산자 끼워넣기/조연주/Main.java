// 14784kb 120ms
import java.util.*;
import java.io.*;

public class Main {
	static int[] num, oper;
	static int N;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 수의 개수
	
		// 수 입력받기
		num = new int[N];
		for(int i=0; i<N; i++) {
			num[i] = sc.nextInt();
		}
		
		// 연산자 개수  + - * /
		oper = new int[4];
		for(int i=0; i<4; i++) {
			oper[i] = sc.nextInt();
		}
		
		find(num[0],1);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	
	static void find(int a, int idx) {
		if(idx==N) {
			max = Math.max(max, a);
			min = Math.min(min, a);
			return;
		}
		
		int b = num[idx];
		
		for(int i=0; i<4; i++) {
			if(oper[i]!=0) {
				oper[i]--;
				switch(i) {
					case 0:
						find(a+b,idx+1);
						break;
					case 1:
						find(a-b,idx+1);
						break;
					case 2:
						find(a*b,idx+1);
						break;
					case 3:
						find(a/b,idx+1);
				}
				oper[i]++;
			}	
		}			
	}
}
