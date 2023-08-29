import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  가장 빨리 끝나는 시간 순으로 먼저 정렬하고, 끝나는 시간이 같다면 가장 먼저 시작하는 순으로 정렬한다.
 *  빨리 끝나는 시간부터 최적해를 선택한다.
 *  이때, 시작하는 시간이 이전의 끝나는 시간 이후여야 한다.
 */

public class Main {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// 시작시간과 끝나는 시간을 2칸의 배열로 저장한다. 
		int[][] arr = new int[N][2];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 시작 시간
			arr[i][1] = Integer.parseInt(st.nextToken()); // 끝나는 시간
		}
		
		// 가장 빨리 끝나는 시간 순으로 먼저 정렬하고, 끝나는 시간이 같다면 가장 먼저 시작하는 순으로 정렬한다.
		Arrays.sort(arr, ((o1, o2) -> {
			if(o1[1]==o2[1]) {
					return o1[0] - o2[0];
			}
			return o1[1] - o2[1];
		}));
		
		int sum = 0; // 회의실을 배정했을 때 카운트
		int end = 0; // 가장 최근의 회의가 끝난 시간
		
		// 끝나는 시간이 빠른 것 중 시작시간이 이전 끝난 시간 이후인 것
		for (int i = 0; i < N; i++) {
			//시작시간이 이전 끝난 시간 이후라면
			if(arr[i][0]>=end) {
				// 이 회의가 끝나는 시간을 업데이트하고
				end = arr[i][1];
				// 회의실 배정 카운트
				sum++;
			}
		}

		System.out.println(sum);

	} // main

}
