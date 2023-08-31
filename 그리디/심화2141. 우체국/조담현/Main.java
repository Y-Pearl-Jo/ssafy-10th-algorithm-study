/*
 * 사람의 총 인원중 가운데번째 있는 사람의 위치에 우체국을 세우면 된다.
 * 마을 번호 기준으로 오름차 순으로 정렬한 후에
 * 사람수를 처음부터 더하다가 중앙에 있는 사람의 위치가 해당 마을에 포함되게 되면 해당 마을의 위치를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][2];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int villNum = Integer.parseInt(st.nextToken());
			int peopleCnt = Integer.parseInt(st.nextToken());
			arr[i][0] = villNum;
			arr[i][1] = peopleCnt;
			sum += peopleCnt;
		}

		Arrays.sort(arr, (o1, o2)->{
			return o1[0] - o2[0];
		});
		
		long result = 0;
		for(int i=0; i<N; i++) {
			result+=arr[i][1];
			if(result>=(sum+1)/2) { // 사람 수를 더하다가 중간 사람이 포함되면
				System.out.println(arr[i][0]);
				return;
			}
		}

	}

}
