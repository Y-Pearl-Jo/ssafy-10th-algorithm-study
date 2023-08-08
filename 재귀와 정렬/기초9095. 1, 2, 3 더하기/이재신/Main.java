import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int cnt = 0;
	
	public static void oneTwo(int sum) {
		if(sum >= N) {
			if(sum==N) {
				cnt++;
			}
			return;
		}
		
		oneTwo(sum + 1);
		oneTwo(sum + 2);
		oneTwo(sum + 3);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test_case = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= test_case; tc++) {
			cnt = 0;
			N = Integer.parseInt(br.readLine());
			
			oneTwo(0);			
			
			System.out.println(cnt);
		}
		
	}
}
