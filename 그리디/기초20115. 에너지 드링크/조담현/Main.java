import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 에너지드링크를 정렬하고, 양이 최댓값 드링크는 유지하면서 나머지 드링크는 1/2로 만들어 더한다.
 * 그리디로 가능한 이유는 가장 큰 값을 1/2로 하면 드링크의 양이 적어지며, 
 * 만약 두개씩 쌍을 지어 더한다면 나중에 합칠 때 가장 작은 드링크는 1/4, 1/8.. 점차 양이 적어진다.
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 에너지 드링크의 수 N
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 에너지드링크를 저장할 배열
		double[] arr = new double[N];
		// 배열에 값을 저장한다.
		for(int i=0; i<N; i++) {
			arr[i] = Double.parseDouble(st.nextToken());
		}
		
		// 에너지드링크를 정렬한다.
		Arrays.sort(arr);
		// 오름차순 정렬했으므로 최댓값은 가장 마지막 인덱스 값
		double max = arr[N-1];
		// 가장 마지막 값을 제외한 나머지 값들을 모두 1/2하여 더해준다.
		for(int i=0; i<N-1; i++) {
			max += arr[i]/2.0;
		}
		
		System.out.println(max);
		
	}
	
}
