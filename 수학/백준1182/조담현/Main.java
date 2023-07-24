// https://we1cometomeanings.tistory.com/266 위 블로그를 참고하여 풀이함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
	public static int n, s;
	public static int[] arr;
	public static int answer= 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		DFS(0,0);
		
		System.out.println(answer);
		
	}
	public static void DFS(int i, int sum) {
		if(i == n) {
			return;
		} else {
			//지금숫자랑 지금까지의 합을 더했을때 S면 +1
			if(arr[i] + sum == s) {
				answer++;
			}
			// 현재숫자를 선택, sum에 추가하고 다음 선택지로
			DFS(i+1, sum + arr[i]);
			// 현재숫자를 sum에 추가하지 않고 다음 선택지로
			DFS(i+1, sum);
		}
		
		
	}

}
