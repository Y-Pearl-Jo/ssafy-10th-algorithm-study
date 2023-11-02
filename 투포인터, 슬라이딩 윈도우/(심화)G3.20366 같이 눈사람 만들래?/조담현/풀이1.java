/*
1. 정렬 후 엘사가 두개를 고르는 경우에서 안나가 나머지 두개를 고르는 투포인터를 실시한다.

2. 엘사가 두 개를 고른 경우에, (해당 두 개를 제외하고) 안나가 나머지 두 개를 골랐을 때 
2-1. 최솟값보다 작다면 업데이트
2-2. 만약 안나가 엘사보다 크거나 같다면 end--
2-3. 안나가 엘사보다 작다면 start++

3. 시간 복잡도는 O(N^3+NlogN)이다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] snow;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		snow = new int[N];
		for (int i = 0; i < N; i++) {
			snow[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(snow);
		
		
		for(int i=0; i<N-1; i++) {
			for (int j = i + 1; j < N; j++) {
				int elsa = snow[i] + snow[j];
					int start = 0;
					int end = N-1;
					while(start<end) {
						if(start==i || start==j) {
							start++;
							continue;
						}
						
						if(end==i||end==j) {
							end--;
							continue;
						}
						int anna = snow[start] + snow[end];
						minDiff = Math.min(minDiff, Math.abs(elsa - anna));
						if(anna<elsa) {
							start++;
						} else if (anna>elsa) {
							end--;
						} else {
							System.out.println(0);
							System.exit(0);
						}
					}
				}
		}
		
		System.out.println(minDiff);
	}
}
