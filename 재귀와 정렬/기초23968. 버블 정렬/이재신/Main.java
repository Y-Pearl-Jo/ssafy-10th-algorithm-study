import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//출력 값을 위한 변수 선언
		int small = 0;
		int big = 0;
		
		//K번째 까지 카운트
		int count = 0;
		
		
		
		Loop1 : for(int i = 1; i < N; i++) {
			for(int j = 0; j < N - i; j++) {
				if(arr[j] > arr[j+1]) {
					big = arr[j];
					small = arr[j+1];
					arr[j+1] = big;
					arr[j] = small;
					count++;
					if(count == K) break Loop1;
				}
			}
		}

		if(count < K) System.out.println(-1);
		else System.out.println(small + " " + big);
	}

}
