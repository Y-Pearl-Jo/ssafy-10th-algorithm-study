import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * d[n] 은 연속적으로 n번째 수를 선택할때까지의 가장 큰 연속합
 * d[n] = max(d[n-1]+arr[n], arr[n]) - 연속해서 더할지, 새로 연속을 시작할 지 결정
 * n번째를 선택하지 않았을 경우는 이미 d[n-1], d[n-2]..에 있으므로 d 배열에서 최댓값을 찾아주면 된다.
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		
		int[] d = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		d[0] = arr[0];
		int ans = arr[0];
		for(int i=1; i<n; i++) {
			d[i] = Math.max(d[i-1]+arr[i], arr[i]);
			ans = Math.max(d[i], ans);
		}
		
		System.out.println(ans);
	}

}
