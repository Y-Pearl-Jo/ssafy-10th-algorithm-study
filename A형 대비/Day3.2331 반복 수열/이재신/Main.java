import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> list = new ArrayList<>();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		String num = st.nextToken();
		double pow = init(st.nextToken());
		
		while(!list.contains(num)) {
			list.add(num);
			double sum= 0;
			for(int i = 0; i < num.length(); i++) {
				sum += Math.pow(init(num.charAt(i)), pow); 
			}
			num = String.valueOf((int)sum);
		}
		
		int cnt = 0;
		for(int i = 0; !list.get(i).equals(num); i++) {
			list.get(i);
			cnt++;
		}
		
		System.out.println(cnt);
		
		
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
	
	static double init(char c) {
		return c - '0';
	}
}
