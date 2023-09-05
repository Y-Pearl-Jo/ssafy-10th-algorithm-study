import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 지방 수
		
		// 요청 예산 입력받기
		int[] arr = new int[N];
		int sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum+=arr[i];
		}
		
		Arrays.sort(arr);
		
		// 총 예산
		int M = Integer.parseInt(br.readLine());
		
		// 모든 요청이 배정 가능
		if(sum<=M) {
			System.out.println(arr[N-1]);
			return;
		}
		
		// 모든 요청 배정 불가
		int cnt = N;
		int idx = 0;
		while(true) {
			if(M/cnt<=arr[idx]) {
				System.out.println(M/cnt);
				return;
			}
			M-=arr[idx];
			idx++;
			cnt--;
		}	
		
	}

}
