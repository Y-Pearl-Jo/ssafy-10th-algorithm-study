
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * new 생성자가 아닌  Array.asList 로 생성한 리스트는 고정되어 있어 원소를 제거 할 수 없다
 */

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<String> list;
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			String order = br.readLine();
			int arrLen = Integer.parseInt(br.readLine());
			String arrStr = br.readLine();

			
			//stream으로 명령문에 있는 D의 개수를 센 다음, 배열의 길이보다 많다면 error
			int dNum = (int)order.chars().filter(c->c=='D').count();
			if(dNum>arrLen) {
				System.out.println("error");
				continue;
			}
			
			//리스트에 저장
			String[] numbers = arrStr.replace("[","").replace("]", "").split(",");
//			list = Arrays.asList(numbers); 오류
			list = new ArrayList<String>(Arrays.asList(numbers));
			
			
			//순서대로 명령 수행
			for(int j=0; j<order.length(); j++) {
				if(order.charAt(j)=='R') {
					Collections.reverse(list);
				} else if (order.charAt(j)=='D'){
					list.remove(0);
				}
			}
			
			//배열로 반환
			String[] res = new String[list.size()];
			list.toArray(res);
			System.out.println(Arrays.toString(res));
			
		}
		

	}

}

/*
for(int j=0; j<order.length(); j++) {
if(order.charAt(j)=='R') {
	Collections.reverse(list);
} else if (order.charAt(j)=='D'){
	list.remove(0);
}
}

이 코드에서  시간 초과가 나는 듯 하다.
order.length도 10만까지, 
list의 길이도 10만가지 가능하기 때문에 10만 x 10만이 될 수 있기 때문.
그렇다면 덱에 수를 저장해서 R이 짝수이면 앞에서 제거하고, R이 홀수이면 뒤에서 제거하면 되지 않을까?
그리고 R이 홀수이면 한번 뒤집기.. 
*/
