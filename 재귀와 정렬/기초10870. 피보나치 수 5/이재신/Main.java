import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static int fibo(int cnt) {
		if(cnt == 0) return 0;
		if(cnt == 1) return 1;
		
		int result = fibo(cnt - 1) + fibo(cnt - 2);
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(fibo(Integer.parseInt(br.readLine())));
	}
}
