import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S;
	static int[] comb;
	static int count = 0;
	
	public static void arrSum(int idx, int sum) {
		//idx == N일 때 S가 맞는지 확인
		if(idx == N) {
			if(sum == S)
				count++;
			return;
		}
		//arr[idx]를 더한 sum과 더하지 않은 sum으로 다시 호출 
		arrSum(idx + 1, sum + comb[idx]);
		arrSum(idx + 1, sum);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력 값 할당		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		//N개의 요소를 가진 배열 생성
		comb = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			comb[i] = Integer.parseInt(st.nextToken());
		}
		
		//sum == S 찾기
		arrSum(0,0);
				
		System.out.println(count);
	}
}

//예시 입력 결과가 1이 아닌 2가 나옴...
