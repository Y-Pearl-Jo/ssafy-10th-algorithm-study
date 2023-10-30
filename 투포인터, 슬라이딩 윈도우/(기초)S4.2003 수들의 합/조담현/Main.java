/*
1. 배열의 모든 길이의 수열을 확인하면 최악의 경우 O(N*N) = 10000*10000 = 1억번의 연산으로 시간초과.

2. 투포인터를 실행하면 O(N)의 시간복잡도, 10000번의 연산.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		int end = 0;
		int intervalSum = 0;
		for(int start=0; start<N; start++) {
			
			while(end<N&&intervalSum<M) {
				intervalSum += arr[end];
				end++;
			}
			
			if(intervalSum==M) count++;
			
			intervalSum -= arr[start];
		}
		
		System.out.println(count);
	}
}

