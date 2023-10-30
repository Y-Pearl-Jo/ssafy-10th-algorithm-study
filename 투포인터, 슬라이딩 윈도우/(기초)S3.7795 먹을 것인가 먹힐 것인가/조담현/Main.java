/*
1. 단순히 두 배열의 모든값을 확인하면 O(N*M) 최악의 경우 연산이 20000^2이므로 4억번의 연산, 시간초과.

2. 정렬 후 투포인터를 실행하면 O(NlogN + MlogM + (N+M))의 시간복잡도
2-1. 연산은 NlogN = MlogM = 20000*15 = 300000, N+M = 40000.
2-2. 최악의 연산 횟수는 640000. 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] A;
	static int[] B;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			B = new int[M];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(A);
			Arrays.sort(B);
			int count = 0;
			int end = 0;
			for(int start=0; start<M; start++) {
				while(end<N && A[end]<=B[start]) {
					end++;
				}
				count+=(N-end);
			}
			System.out.println(count);
		}
		
	}
}
