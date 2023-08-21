import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int A;
	static int P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		// 수를 저장할 어레이리스트 생성, 첫번째 수 A 저장
		ArrayList<Integer> list = new ArrayList<>();
		list.add(A);
		int idx = 0;
		int breakpoint = 0;
		
		while(true) {
			// 문자열로 수를 꺼내와서
			String s = String.valueOf(list.get(idx++));
			int next = 0;
			
			// 각 문자의 수를 제곱하여 더한다.
			for(int i=0; i<s.length(); i++) {
				int num = s.charAt(i) - '0';
				next+=(int)Math.pow(num, P);
			}
			
			// 만약 그 수를 list가 갖고있다면 반복이므로  break
			if(list.contains(next)) {
				breakpoint = next;
				break;
			}
			// 갖고있지 않다면 list에 추가
			else {
				list.add(next);
			}
		}
		
		// 반복이 시작되는 수 breakpoint가 나올때까지 count한다.
		int count = 0;
		for(int i=0; i<list.size(); i++) {
			if(list.get(i)==breakpoint) {
				break;
			} else {
				count++;
			}
		}
		
		System.out.println(count);
	}

}
