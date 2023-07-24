import java.util.Scanner;

public class Main {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int count = 0;
		for(int i=0; i<N; i++) {
			boolean isPrime = true;
			int num = sc.nextInt();
      // 1일 때는 소수가 아님
			if(num <=1) {
				continue;
			}
      // 2부터 본인이 아닌 수로 나누어지면 소수가 아님
			for(int j=2; j<num; j++) {
				if(num%j==0) {
					isPrime = false;
					break;
				}
			}
      // 소수면 카운트
			if(isPrime) {
				count++;
			}
		}
		
		System.out.println(count);
	}

}
