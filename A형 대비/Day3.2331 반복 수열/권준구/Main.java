import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		
		int A = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		list.add(A);
		while(true) {
			int temp= list.get(list.size()-1);
			int result =0;
			while(temp !=0) {
				result +=(int)Math.pow(temp%10,(double)P);
				temp /= 10;
			}
			if(list.contains(result)) {
				int ans = list.indexOf(result);
				System.out.println(ans);
				break;
			}
			list.add(result);
		}
		
	}
	
}
