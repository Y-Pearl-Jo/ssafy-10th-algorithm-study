/*
1. N개에 대해 X일을 확인하면 O(N*X). 최악의 경우 250000 * 250000번의 연산이므로 무조건 시간초과.
2. 이 때 슬라이딩 윈도우를 활용하면 O(N)의 시간 복잡도, 즉 250000번의 연산에 해결 가능
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, X;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		int maxSum = 0;
		int intervalSum = 0;
		for(int start=0; start<N; start++) {
			// 지금확인하는 start번째 인덱스 값 더해주기
			intervalSum += arr[start];
			// X개의 배열을 확인했을 때
			if(start>=X-1) {
				// 최대 방문자 수가 여러개라면 count 증가
				if(maxSum == intervalSum) count++;
				
				// 최대 방문자수를 업데이트 했다면 기간의 수는 1부터 시작
				else if(maxSum<intervalSum) {
					maxSum = intervalSum;
					count= 1;
				}
				// 최대 방문자수 비교가 끝났다면 X개중 가장 앞의 인덱스 빼주기
				intervalSum -= arr[start-(X-1)];
				
			}
		}
		if(maxSum==0) {
			System.out.println("SAD");
		} else {
			System.out.println(maxSum);
			System.out.println(count);
		}
	}
}

