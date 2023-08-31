import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Jewel{
	int M; // 무게
	int V; // 가격
	public Jewel(int m, int v) {
		M = m;
		V = v;
	}
	
}


public class Solution2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		List<Jewel> jewel = new ArrayList<>();
		List<Integer> bag = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 보석의 개수
		int K = Integer.parseInt(st.nextToken()); // 가방 개수

		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken()); // 무게
			int V = Integer.parseInt(st.nextToken()); // 가격
			jewel.add(new Jewel(M,V));	
		}
		
		for(int i=0;i<K;i++) {
			
		}
		
		
	}
}
